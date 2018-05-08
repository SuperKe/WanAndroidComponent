package com.demo.chenke.componentone.presenter;

import com.demo.chenke.basiclib.mvpbase.baseImpl.BasePresenterImpl;
import com.demo.chenke.componentone.contact.ArticleDetailContact;

/**
 * Created by chenke on 2018/5/7.
 */

public class ArticleDetailPresenter extends BasePresenterImpl<ArticleDetailContact.setDataView> implements ArticleDetailContact.presenter {

    public ArticleDetailPresenter(ArticleDetailContact.setDataView view) {
        super(view);
    }

    @Override
    public boolean getAutoCacheState() {
        return false;
    }

    @Override
    public boolean getNoImageState() {
        return false;
    }

    @Override
    public void addCollectArticle(int id) {

    }

    @Override
    public void cancelCollectArticle(int id) {

    }

    @Override
    public void cancelCollectPageArticle(int id) {

    }
}
