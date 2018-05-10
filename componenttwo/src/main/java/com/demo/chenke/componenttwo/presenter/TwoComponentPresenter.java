package com.demo.chenke.componenttwo.presenter;

import com.demo.chenke.basiclib.BaseResponse;
import com.demo.chenke.basiclib.mvpbase.baseImpl.BasePresenterImpl;
import com.demo.chenke.basiclib.retroft.ExceptionHelper;
import com.demo.chenke.componenttwo.bean.KnowledgeHierarchyData;
import com.demo.chenke.componenttwo.contact.TowComponentContact;
import com.demo.chenke.componenttwo.http.TwoComponentApi;
import com.demo.chenke.componenttwo.http.TwoComponentService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by chenke on 2018/5/9.
 */

public class TwoComponentPresenter extends BasePresenterImpl<TowComponentContact.setDataView> implements TowComponentContact.presenter {
    /**
     * 绑定视图层和presenter网络层
     *
     * @param view
     */
    public TwoComponentPresenter(TowComponentContact.setDataView view) {
        super(view);
    }

    @Override
    public void getKnowledgeList() {
        view.showLoading("");
        TwoComponentApi.getInstance().getKnowledgeHierarchyData()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<List<KnowledgeHierarchyData>>>() {
                    @Override
                    public void accept(BaseResponse<List<KnowledgeHierarchyData>> listBaseResponse) throws Exception {
                        if (listBaseResponse.getData().size() > 0) {
                            view.setViewKnowledgeList(listBaseResponse.getData());
                            view.showContent();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(ExceptionHelper.codeException(throwable));
                    }
                });
    }
}
