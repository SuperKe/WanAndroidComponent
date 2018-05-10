package com.demo.chenke.componenttwo.presenter;

import android.os.Bundle;

import com.demo.chenke.basiclib.BaseResponse;
import com.demo.chenke.basiclib.mvpbase.baseImpl.BasePresenterImpl;
import com.demo.chenke.basiclib.retroft.ExceptionHelper;
import com.demo.chenke.commonlib.Constants;
import com.demo.chenke.componenttwo.bean.FeedArticleListData;
import com.demo.chenke.componenttwo.contact.KonwledgeContact;
import com.demo.chenke.componenttwo.http.TwoComponentApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenke on 2018/5/10.
 */

public class TwoComponentKonwledgePresenter extends BasePresenterImpl<KonwledgeContact.setDataView> implements KonwledgeContact.presenter {

    /**
     * 绑定视图层和presenter网络层
     *
     * @param view
     */
    public TwoComponentKonwledgePresenter(KonwledgeContact.setDataView view) {
        super(view);
    }

    @Override
    public void getKonwledgeList(int page, int cid) {
        TwoComponentApi.getInstance().getKnowledgeHierarchyDetailData(page, cid)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<FeedArticleListData>>() {
                    @Override
                    public void accept(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) throws Exception {
                        if (feedArticleListDataBaseResponse.getData().getCurPage() > 0) {
                            view.setKonwledgeList(feedArticleListDataBaseResponse.getData().getDatas());
                            view.showContent();
                        } else {
                            if (feedArticleListDataBaseResponse.getData().getDatas().size() == 0) {
                                //showEmpty
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(ExceptionHelper.codeException(throwable));
                    }
                });
    }

    public Bundle goToWebView(String title, int position, String alink, boolean isCollection) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARTICLE_TITLE, title);
        bundle.putInt(Constants.ARTICLE_POSITION, position);
        bundle.putString(Constants.ARTICLE_LINK, alink);
        bundle.putBoolean(Constants.IS_COLLECT, isCollection);
        return bundle;
    }
}
