package com.demo.chenke.basiclib.mvpbase.baseImpl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.classic.common.MultipleStatusView;
import com.demo.chenke.basiclib.R;
import com.demo.chenke.basiclib.mvpbase.BaseView;
import com.demo.chenke.basiclib.utils.RouterUtil;
import com.demo.chenke.otherlib.customview.LoadingDialog;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by chenke on 2018/5/4.
 * 这个fragment是抽象加载时的各种视图的view(loadingView,errorView)
 * 降低BaseFragment的侵入性
 */
public abstract class UIFragment extends SupportFragment implements BaseView {
    public LoadingDialog dialog;
    public Context context;
    public LinearLayout.LayoutParams params;
    private MultipleStatusView statusView;
    private LottieAnimationView mLoadingAnimation;
    private View loadView = null;
    private View errView = null;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), container, false);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
    }

    @Override
    public void showLoadingDialog(String msg) {
        if (dialog == null) {
            dialog = new LoadingDialog(context);
        }
        dialog.setMessage(msg);
        dialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void showLoading(String msg) {
        showStatus(BaseView.PAGE_LOGIN);
    }


    @Override
    public void showError(int type) {
        showStatus(type);
    }

    @Override
    public void showContent() {
        showStatus(PAGE_NORMAL);
    }

    private void showStatus(int type) {
        if (rootView != null) {
            if (statusView == null) {
                statusView = rootView.findViewWithTag("statusView");
            }
            createLoadingView();
            createErrorView();
        }
        if (statusView != null) {
            switch (type) {
                case BaseView.PAGE_LOGIN:
                    mLoadingAnimation.playAnimation();
                    statusView.showLoading(loadView, params);
                    break;
                case BaseView.PAGE_ERROR:
                    mLoadingAnimation.cancelAnimation();
                    statusView.showError(errView, params);
                    break;
                case BaseView.PAGE_NORMAL:
                    mLoadingAnimation.cancelAnimation();
                    statusView.showContent();
                    break;

            }
        }
    }

    /**
     * 创建加载视图
     */
    private void createLoadingView() {
        if (loadView == null) {
            loadView = LayoutInflater.from(context).inflate(R.layout.loading_view, null);
            mLoadingAnimation = loadView.findViewById(R.id.loading_animation);
            mLoadingAnimation.setAnimation("loading_bus.json");
            mLoadingAnimation.loop(true);
        }
    }

    /**
     * 创建错误视图
     */
    private void createErrorView() {
        if (errView == null) {
            errView = LayoutInflater.from(context).inflate(R.layout.error_view, null);
            (errView.findViewById(R.id.error_reload_tv)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reload();
                }
            });
        }
    }

    /**
     * @param router 路由路径
     * @param bundle 参数实体
     */
    public void startActivity(String router, Bundle bundle) {
        RouterUtil.router(router, bundle);
    }

    /**
     * @param router 路由路径
     */
    public void startActivity(String router) {
        RouterUtil.router(router);
    }

    public abstract int getLayout();

    public abstract void initView();

    /**
     * 错误重连
     */
    protected void reload() {

    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }
}
