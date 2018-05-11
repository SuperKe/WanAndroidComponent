package com.demo.chenke.componentthree.contact;

import com.demo.chenke.basiclib.mvpbase.BasePresenter;
import com.demo.chenke.basiclib.mvpbase.BaseView;
import com.demo.chenke.componentthree.bean.NavigationListData;

import java.util.List;

/**
 * Created by chenke on 2018/5/10.
 */

public class ThreeComponentContact {
    public interface presenter extends BasePresenter {
        void getNaviJason();
    }

    public interface setViewData extends BaseView {
        void setNaviList(List<NavigationListData> guideList);
    }
}
