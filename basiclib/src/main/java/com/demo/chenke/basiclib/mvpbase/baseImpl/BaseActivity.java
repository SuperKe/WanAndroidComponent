package com.demo.chenke.basiclib.mvpbase.baseImpl;

import android.os.Bundle;

import com.demo.chenke.basiclib.mvpbase.BasePresenter;
import com.demo.chenke.basiclib.utils.ActivityManager;

/**
 * Created by chenke on 2018/4/26.
 */

public abstract class BaseActivity<P extends BasePresenter> extends UIActivity {
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getAppInstance().removeActivity(this);//将当前activity移除管理栈
        if (presenter != null) {
            presenter.detach();//在presenter中解绑释放view
            presenter = null;
        }
        super.onDestroy();
    }

    @Override
    public void dismissLoadingDialog() {
        super.dismissLoadingDialog();
        if (presenter != null) {
            presenter.unDisposable();//在presenter中解绑释放view
        }
    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract P initPresenter();
}
