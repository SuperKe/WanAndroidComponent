package com.demo.chenke.basiclib.mvpbase.baseImpl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.classic.common.MultipleStatusView;
import com.demo.chenke.basiclib.R;
import com.demo.chenke.basiclib.mvpbase.BaseView;
import com.demo.chenke.basiclib.utils.ActivityManager;
import com.demo.chenke.otherlib.customview.LoadingDialog;

/**
 * 视图基类activity的抽象
 * 处理状态视图和dialog
 */
public abstract class UIActivity extends AppCompatActivity implements BaseView {
    public LoadingDialog dialog;
    private LinearLayout.LayoutParams params;
    private View contentView, statusView;
    private LottieAnimationView mLoadingAnimation;
    private View loadView, errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = getLayoutInflater().inflate(setLayout(), null);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(contentView);
        initStatusView();
        ActivityManager.getAppInstance().addActivity(this);//将当前activity添加进入管理栈
        initView();
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    /**
     * 这里处理dialog是因为dialog在消失的时候回解绑rxjava
     * 因为用户可能在retrofit未返回结果的情况下dismiss
     *
     * @param msg
     */
    @Override
    public void showLoadingDialog(String msg) {
        if (dialog == null) {
            dialog = new LoadingDialog(this);
        }
        dialog.setMessage(msg);
        dialog.show();
    }

    /**
     * 按钮操作
     */
    @Override
    public void dismissLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 如果是网络加载，则展示过度视图
     *
     * @param msg
     */
    @Override
    public void showLoading(String msg) {
        showStatus(BaseView.PAGE_LOGIN);
    }

    /**
     * 加载错误视图
     *
     * @param type
     */
    @Override
    public void showError(int type) {
        showStatus(type);
    }

    /**
     * 展示网络加成成功后的content
     */
    @Override
    public void showContent() {
        showStatus(BaseView.PAGE_NORMAL);
    }

    /**
     * 做初始化view的操作
     */
    public abstract void initView();

    /**
     * 初始化layout
     *
     * @return
     */
    public abstract int setLayout();

    /**
     * 这里每个初始化的activity都会获取statusView
     */
    private void initStatusView() {
        if (statusView == null) {
            statusView = contentView.findViewWithTag("statusView");
        }
    }

    /**
     * 状态视图
     *
     * @param type
     */
    private void showStatus(int type) {
        if (statusView != null && statusView instanceof MultipleStatusView) {
            createLoadingView();
            createErrorView();
            if (statusView != null) {
                switch (type) {
                    case BaseView.PAGE_LOGIN:
                        mLoadingAnimation.playAnimation();
                        ((MultipleStatusView) statusView).showLoading(loadView, params);
                        break;
                    case BaseView.PAGE_ERROR:
                        mLoadingAnimation.cancelAnimation();
                        ((MultipleStatusView) statusView).showError(errorView, params);
                        break;
                    case BaseView.PAGE_NORMAL:
                        mLoadingAnimation.cancelAnimation();
                        ((MultipleStatusView) statusView).showContent();
                        break;
                }
            }
        }
    }

    private void createLoadingView() {
        if (loadView == null) {
            loadView = getLayoutInflater().inflate(R.layout.loading_view, null);
            mLoadingAnimation = loadView.findViewById(R.id.loading_animation);
            mLoadingAnimation.setAnimation("loading_bus.json");
            mLoadingAnimation.loop(true);
        }
    }

    private void createErrorView() {
        if (errorView == null) {
            errorView = getLayoutInflater().inflate(R.layout.error_view, null);
        }
    }
}
