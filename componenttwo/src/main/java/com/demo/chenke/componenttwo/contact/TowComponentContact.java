package com.demo.chenke.componenttwo.contact;

import com.demo.chenke.basiclib.mvpbase.BasePresenter;
import com.demo.chenke.basiclib.mvpbase.BaseView;
import com.demo.chenke.componenttwo.bean.KnowledgeHierarchyData;

import java.util.List;

/**
 * Created by chenke on 2018/5/9.
 */

public interface TowComponentContact {
    interface presenter extends BasePresenter {
        void getKnowledgeList();
    }

    interface setDataView extends BaseView {
        void setViewKnowledgeList(List<KnowledgeHierarchyData> childrenList);
    }
}
