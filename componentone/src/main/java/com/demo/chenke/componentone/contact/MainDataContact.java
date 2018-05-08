package com.demo.chenke.componentone.contact;

import com.demo.chenke.basiclib.mvpbase.BasePresenter;
import com.demo.chenke.basiclib.mvpbase.BaseView;
import com.demo.chenke.componentone.bean.BannerData;
import com.demo.chenke.componentone.bean.FeedArticleData;

import java.util.List;

/**
 * Created by chenke on 2018/5/2.
 */

public interface MainDataContact {
    interface presenter extends BasePresenter {
        void loadData();//第一次加载数据

        void getArticleList(int num);

        void refresh();

        void loadMore();
    }

    interface setDataView extends BaseView {
        //请求成功，回填数据
        void addBanner(List<BannerData> bannerList);

        //加载更多
        void addArticle(List<FeedArticleData> articleList, boolean isRefresh);

        void refreshUI(List<BannerData> bannerList, List<FeedArticleData> articleList);
    }
}
