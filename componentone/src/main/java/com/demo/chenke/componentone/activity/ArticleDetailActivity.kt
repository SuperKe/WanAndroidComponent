package com.demo.chenke.componentone.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseActivity
import com.demo.chenke.basiclib.utils.CommonUtils
import com.demo.chenke.commonlib.Constants
import com.demo.chenke.commonlib.utils.StatusBarUtil
import com.demo.chenke.componentone.R
import com.demo.chenke.componentone.contact.ArticleDetailContact
import com.demo.chenke.componentone.presenter.ArticleDetailPresenter
import com.demo.chenke.otherlib.event.CollectEvent
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.one_article_detail_activity.*
import org.greenrobot.eventbus.EventBus

@Route(path = "/componentone/article_detail")
class ArticleDetailActivity : BaseActivity<ArticleDetailPresenter>(), ArticleDetailContact.setDataView {
    private var bundle: Bundle? = null
    private var id = 0
    private var articleTitle = ""
    private var articleLink = ""
    private var isCollect = false
    private var mCollectItem: MenuItem? = null
    private var position = 0

    override fun setLayout(): Int {
        return R.layout.one_article_detail_activity
    }

    override fun initPresenter(): ArticleDetailPresenter {
        return ArticleDetailPresenter(this)
    }

    override fun initView() {
        bundle = intent.extras
        if (bundle != null) {
            id = bundle!!.getInt(Constants.ARTICLE_ID)
            position = bundle!!.getInt(Constants.ARTICLE_POSITION)
            articleTitle = bundle!!.getString(Constants.ARTICLE_TITLE)
            articleLink = bundle!!.getString(Constants.ARTICLE_LINK)
            isCollect = bundle!!.getBoolean(Constants.IS_COLLECT)
        }
        setToolBar(article_detail_toolbar, Html.fromHtml(articleTitle))
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, article_detail_toolbar)
        initWebFragment()
        article_detail_toolbar.setNavigationOnClickListener {
            onBackPressedSupport()
        }
    }

    fun initWebFragment() {
        var webFragment = ARouter.getInstance().build("/componentone/article_detail_web_view").navigation()
        if (webFragment != null) {
            webFragment as Fragment
            var bundle = Bundle()
            bundle.putString("url", articleLink)
            webFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.article_detail_web_view, webFragment).commitAllowingStateLoss()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (id > 0) {
            menuInflater.inflate(R.menu.menu_acticle, menu)
            mCollectItem = menu.findItem(R.id.item_collect)
            showCollect()
        } else {
            menuInflater.inflate(R.menu.menu_article_common, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * 展示收藏
     */
    private fun showCollect() {
        if (isCollect) {
            mCollectItem!!.title = getString(R.string.cancel_collect)
            mCollectItem!!.setIcon(R.mipmap.ic_toolbar_like_p)
        } else {
            mCollectItem!!.title = getString(R.string.collect)
            mCollectItem!!.setIcon(R.mipmap.ic_toolbar_like_n)
        }
    }

    /**
     * 让菜单同时显示图标和文字
     *
     * @param featureId Feature id
     * @param menu Menu
     * @return menu if opened
     */
    override fun onMenuOpened(featureId: Int, menu: Menu?): Boolean {
        if (menu != null) {
            if (Constants.MENU_BUILDER == menu.javaClass.simpleName) {
                try {
                    @SuppressLint("PrivateApi")
                    val method = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", java.lang.Boolean.TYPE)
                    method.isAccessible = true
                    method.invoke(menu, true)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        return super.onMenuOpened(featureId, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_share -> share()
            R.id.item_collect -> collectEvent()
            R.id.item_system_browser -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(articleLink)))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun collectEvent() {
        isCollect = !isCollect
        showCollect()
        EventBus.getDefault().post(CollectEvent(isCollect, position))
    }

    private fun share() {
        RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {
                    if (it) {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_type_url, getString(R.string.app_name), title, articleLink))
                        intent.type = "text/plain"
                        startActivity(intent)
                    } else {
                        CommonUtils.showSnackMessage(this, getString(R.string.write_permission_not_allowed))
                    }
                }
    }
}
