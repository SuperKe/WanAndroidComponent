package com.demo.chenke.componentone.contact;

import com.demo.chenke.basiclib.mvpbase.BasePresenter;
import com.demo.chenke.basiclib.mvpbase.BaseView;

/**
 * Created by chenke on 2018/5/7.
 */

public interface ArticleDetailContact {
    /**
     * 获取数据和article页面的相关网络操作
     */
    interface presenter extends BasePresenter {
        /**
         * Get auto cache state
         *
         * @return if auto cache state
         */
        boolean getAutoCacheState();

        /**
         * Get no image state
         *
         * @return if has image state
         */
        boolean getNoImageState();

        /**
         * Add collect article
         *
         * @param id article id
         */
        void addCollectArticle(int id);

        /**
         * Cancel collect article
         *
         * @param id article id
         */
        void cancelCollectArticle(int id);

        /**
         * Cancel collect article
         *
         * @param id article id
         */
        void cancelCollectPageArticle(int id);

        /**
         * verify share permission
         *
         * @param rxPermissions RxPermissions
         */
//        void shareEventPermissionVerify(RxPermissions rxPermissions);
    }

    interface setDataView extends BaseView {
        /**
         * 收藏结果处理
         *
         * @param msg 收藏结果
         */
        void showCollectArticleResult(String msg);

        /**
         * 取消搜藏结果处理
         *
         * @param msg 取消收藏结果
         */
        void showCancelCollectArticleResult(String msg);
    }
}
