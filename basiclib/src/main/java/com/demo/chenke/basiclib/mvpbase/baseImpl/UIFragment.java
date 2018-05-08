package com.demo.chenke.basiclib.mvpbase.baseImpl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

/**
 * Created by chenke on 2018/5/4.
 * 这个fragment是抽象加载时的各种视图的view(loadingView,errorView)
 * 降低BaseFragment的侵入性
 */
public abstract class UIFragment extends Fragment implements BaseView {
    public LoadingDialog dialog;
    public Context context;
    private View rootView;
    private boolean isViewCreate = false;//view是否创建
    private boolean isViewVisible = false;//view是否可见
    private boolean isFirst = true;//是否第一次加载

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), container, false);
            initView();
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreate = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isViewVisible = isVisibleToUser;
        if (isVisibleToUser && isViewCreate) {
            visibleToUser();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isViewVisible) {
            visibleToUser();
        }
    }

    @Override
    public void onDestroyView() {
        isViewCreate = false;
        super.onDestroyView();
    }

    /**
     * 懒加载
     * 让用户可见
     * 第一次加载
     */
    protected void firstLoad() {

    }

    /**
     * 懒加载
     * 让用户可见
     */
    protected void visibleToUser() {
        if (isFirst) {
            firstLoad();
            isFirst = false;
        }
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
        MultipleStatusView statusView = null;
        LinearLayout.LayoutParams params = null;
        LottieAnimationView mLoadingAnimation = null;
        View loadView = null;
        View errView = null;
        if (rootView != null) {
            statusView = rootView.findViewWithTag("statusView");
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            loadView = LayoutInflater.from(context).inflate(R.layout.loading_view, null);
            mLoadingAnimation = loadView.findViewById(R.id.loading_animation);
            mLoadingAnimation.setAnimation("loading_bus.json");
            mLoadingAnimation.loop(true);
        }
        if (statusView != null) {
            switch (type) {
                case BaseView.PAGE_LOGIN:
                    mLoadingAnimation.playAnimation();
                    statusView.showLoading(loadView, params);
                    break;
                case BaseView.PAGE_ERROR:
                    mLoadingAnimation.cancelAnimation();
                    statusView.showError();
                    break;
                case BaseView.PAGE_NORMAL:
                    mLoadingAnimation.cancelAnimation();
                    statusView.showContent();
                    break;

            }
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

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }
}
