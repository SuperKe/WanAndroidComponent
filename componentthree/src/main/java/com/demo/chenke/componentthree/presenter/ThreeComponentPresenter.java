package com.demo.chenke.componentthree.presenter;

import com.demo.chenke.basiclib.BaseResponse;
import com.demo.chenke.basiclib.mvpbase.baseImpl.BasePresenterImpl;
import com.demo.chenke.basiclib.retroft.ExceptionHelper;
import com.demo.chenke.componentthree.bean.NavigationListData;
import com.demo.chenke.componentthree.contact.ThreeComponentContact;
import com.demo.chenke.componentthree.http.ThreeComponentApi;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenke on 2018/5/10.
 */

public class ThreeComponentPresenter extends BasePresenterImpl<ThreeComponentContact.setViewData> implements ThreeComponentContact.presenter {
    /**
     * 绑定视图层和presenter网络层
     *
     * @param view
     */
    public ThreeComponentPresenter(ThreeComponentContact.setViewData view) {
        super(view);
    }

    @Override
    public void getNaviJason() {
        view.showLoading("");
        ThreeComponentApi.getInstance().getNavigationListData()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<List<NavigationListData>>>() {
                    @Override
                    public void accept(BaseResponse<List<NavigationListData>> listBaseResponse) throws Exception {
                        if (listBaseResponse.getData().size() > 0) {
                            view.setNaviList(listBaseResponse.getData());
                        }
                        view.showContent();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(ExceptionHelper.codeException(throwable));
                    }
                });
    }
}
