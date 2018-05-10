package com.demo.chenke.componenttwo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.demo.chenke.basiclib.mvpbase.baseImpl.UIActivity
import com.demo.chenke.commonlib.Constants
import com.demo.chenke.commonlib.utils.StatusBarUtil
import com.demo.chenke.componenttwo.R
import kotlinx.android.synthetic.main.konwledge_detail_activity.*

@Route(path = "/componenttwo/web_activity")
class KonwledgeDetailActivity : UIActivity() {
    private var title = ""
    private var url = ""
    private var isCollection = false
    private var position = 0
    override fun setLayout(): Int {
        return R.layout.konwledge_detail_activity
    }

    override fun initView() {
        initBundle()
        initToolBar()
    }

    private fun initBundle() {
        var bundle = intent.extras
        if (bundle != null) {
            title = bundle.getString(Constants.ARTICLE_TITLE)
            url = bundle.getString(Constants.ARTICLE_LINK)
            position = bundle.getInt(Constants.ARTICLE_POSITION)
            isCollection = bundle.getBoolean(Constants.IS_COLLECT)
        }
    }

    private fun initToolBar() {
        setToolBar(toolbar, Html.fromHtml(title))
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressedSupport()
        }
        initWebView()
    }

    private fun initWebView() {
        var bundle = Bundle()
        bundle.putString("url", url)
        var webFragment = ARouter.getInstance().build("/componenttwo/web_fragment").with(bundle).navigation() as Fragment
        supportFragmentManager.beginTransaction().replace(R.id.web_view, webFragment!!).commitAllowingStateLoss()
    }
}
