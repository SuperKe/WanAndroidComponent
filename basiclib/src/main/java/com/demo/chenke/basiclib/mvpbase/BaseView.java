package com.demo.chenke.basiclib.mvpbase;

public interface BaseView {
    /**
     * 这里只处理网络异常和网络异常之外的异常
     */
    int PAGE_LOGIN=0;//加载视图
    int PAGE_NORMAL= 1;//正常视图
    int NET_ERROR = 2;//网络异常
    int PAGE_ERROR = 3;//其他异常

    void showLoadingDialog(String msg);

    void dismissLoadingDialog();

    void showLoading(String msg);

    void showContent();

    void showError(int type);
}
