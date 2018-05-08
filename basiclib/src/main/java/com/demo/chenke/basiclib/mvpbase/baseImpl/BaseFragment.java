package com.demo.chenke.basiclib.mvpbase.baseImpl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.demo.chenke.basiclib.mvpbase.BasePresenter;


public abstract class BaseFragment<P extends BasePresenter> extends UIFragment {

    protected P presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void dismissLoadingDialog() {
        super.dismissLoadingDialog();
        if (presenter != null) {
            presenter.unDisposable();//在presenter中解绑释放view
        }
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detach();
        }
        super.onDestroyView();
    }

    public abstract P initPresenter();

}
