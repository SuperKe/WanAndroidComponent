package com.demo.chenke.componentone.activity

import android.content.DialogInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseActivity
import com.demo.chenke.componentone.R
import com.demo.chenke.componentone.contact.ArticleDetailContact
import com.demo.chenke.componentone.presenter.ArticleDetailPresenter

@Route(path = "/componentone/article_detail")
class ArticleDetailActivity : BaseActivity<ArticleDetailPresenter>(), ArticleDetailContact.setDataView {
    override fun setLayout(): Int {
        return R.layout.one_article_detail_activity
    }

    override fun initPresenter(): ArticleDetailPresenter {
        return ArticleDetailPresenter(this)
    }

    override fun initView() {

    }

    override fun showCollectArticleResult(msg: String?) {

    }

    override fun showCancelCollectArticleResult(msg: String?) {
    }
}
