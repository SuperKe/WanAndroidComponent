package com.demo.chenke.basiclib.utils;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.demo.chenke.basiclib.R;


/**
 * Created by chenke on 2018/5/7.
 * 路由接管类
 */
public class RouterUtil {
    /**
     * @param router 路由路径
     * @param bundle Bundle实体
     */
    public static void router(String router, Bundle bundle) {
        go(router, bundle);
    }

    /**
     * @param router 路由路径
     */
    public static void router(String router) {
        go(router, null);
    }

    private static void go(String router, Bundle bundle) {
        if (bundle != null) {
            ARouter.getInstance().build(router).with(bundle).withTransition(R.anim.push_left_in, R.anim.push_left_out).navigation();
        } else {
            ARouter.getInstance().build(router).withTransition(R.anim.push_left_in, R.anim.push_left_out).navigation();
        }
    }
}
