package com.demo.chenke.componenttwo.contact;

import com.demo.chenke.basiclib.mvpbase.BasePresenter;
import com.demo.chenke.basiclib.mvpbase.BaseView;
import com.demo.chenke.componenttwo.bean.FeedArticleData;
import com.demo.chenke.componenttwo.bean.FeedArticleListData;

import java.util.List;


/**
 * Created by chenke on 2018/5/10.
 */

public interface KonwledgeContact {
    interface presenter extends BasePresenter {
        void getKonwledgeList(int page, int cid);
    }

    interface setDataView extends BaseView {
        void setKonwledgeList(List<FeedArticleData> dataList);
    }
}
