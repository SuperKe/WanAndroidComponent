package com.demo.chenke.componentone.presenter;

import android.os.Bundle;

import com.demo.chenke.basiclib.BaseResponse;
import com.demo.chenke.basiclib.mvpbase.BaseView;
import com.demo.chenke.basiclib.mvpbase.baseImpl.BasePresenterImpl;
import com.demo.chenke.basiclib.retroft.ExceptionHelper;
import com.demo.chenke.basiclib.utils.RouterUtil;
import com.demo.chenke.commonlib.Constants;
import com.demo.chenke.componentone.bean.BannerData;
import com.demo.chenke.componentone.bean.FeedArticleData;
import com.demo.chenke.componentone.bean.FeedArticleListData;
import com.demo.chenke.componentone.contact.MainDataContact;
import com.demo.chenke.componentone.http.MainComponentApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenke on 2018/5/2.
 */
public class MainFragmentPresenter extends BasePresenterImpl<MainDataContact.setDataView> implements MainDataContact.presenter {
    private int pageIndex;
    private boolean isRefresh;
    private MainDataContact.setDataView view;
    private List<BannerData> bannerList = new ArrayList<>();
    private List<FeedArticleData> articleList = new ArrayList<>();

    public MainFragmentPresenter(MainDataContact.setDataView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void loadData() {
        view.showLoading("");
        Observable<BaseResponse<List<BannerData>>> obBanner = MainComponentApi.getInstance().getBannerData();
        Observable<BaseResponse<FeedArticleListData>> obArticleList = MainComponentApi.getInstance().getFeedArticleList(0);
        Observable.zip(obBanner, obArticleList, new BiFunction<BaseResponse<List<BannerData>>, BaseResponse<FeedArticleListData>, Boolean>() {
            @Override
            public Boolean apply(BaseResponse<List<BannerData>> listBaseResponse, BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) throws Exception {
                if (listBaseResponse.getData() != null && feedArticleListDataBaseResponse.getData() != null) {
                    bannerList.addAll(listBaseResponse.getData());
                    articleList.addAll(feedArticleListDataBaseResponse.getData().getDatas());
                    return true;
                }
                return false;
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                addDisposable(disposable);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            view.refreshUI(bannerList, articleList);
                            view.showContent();
                        } else {
                            view.showError(BaseView.PAGE_ERROR);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(ExceptionHelper.codeException(throwable));
                    }
                });
    }


    @Override
    public void refresh() {
        pageIndex = 0;
        isRefresh = true;
        getBanner();
        getArticleList(pageIndex);
    }

    @Override
    public void loadMore() {
        pageIndex++;
        isRefresh = false;
        getArticleList(pageIndex);
    }

    private void getBanner() {
        MainComponentApi.getInstance()
                .getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                }).subscribe(new Consumer<BaseResponse<List<BannerData>>>() {
            @Override
            public void accept(BaseResponse<List<BannerData>> listBaseResponse) throws Exception {
                view.addBanner(listBaseResponse.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ExceptionHelper.handleException(throwable);
            }
        });
    }

    @Override
    public void getArticleList(int num) {
        MainComponentApi.getInstance().getFeedArticleList(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribe(new Consumer<BaseResponse<FeedArticleListData>>() {
                    @Override
                    public void accept(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) throws Exception {
                        view.addArticle(feedArticleListDataBaseResponse.getData().getDatas(), isRefresh);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ExceptionHelper.handleException(throwable);
                    }
                });
    }

    public Bundle getArticleDetailActivityBundle(int id, String articleTitle, String articleLink, boolean isCollect, boolean isCollectPage, boolean isCommonSite) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ARTICLE_ID, id);
        bundle.putString(Constants.ARTICLE_TITLE, articleTitle);
        bundle.putString(Constants.ARTICLE_LINK, articleLink);
        bundle.putBoolean(Constants.IS_COLLECT, isCollect);
        bundle.putBoolean(Constants.IS_COLLECT_PAGE, isCollectPage);
        bundle.putBoolean(Constants.IS_COMMON_SITE, isCommonSite);
        return bundle;
    }
}
