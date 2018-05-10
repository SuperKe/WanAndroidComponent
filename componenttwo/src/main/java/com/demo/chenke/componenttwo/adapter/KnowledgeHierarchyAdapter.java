package com.demo.chenke.componenttwo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.demo.chenke.basiclib.utils.CommonUtils;
import com.demo.chenke.componenttwo.R;
import com.demo.chenke.componenttwo.bean.KnowledgeHierarchyData;

import java.util.List;


/**
 * @author quchao
 * @date 2018/2/23
 */

public class KnowledgeHierarchyAdapter extends BaseQuickAdapter<KnowledgeHierarchyData, KnowledgeHierarchyViewHolder> {

    public KnowledgeHierarchyAdapter(@Nullable List<KnowledgeHierarchyData> data) {
        super(R.layout.item_knowledge_hierarchy, data);
    }

    @Override
    protected void convert(KnowledgeHierarchyViewHolder helper, KnowledgeHierarchyData item) {
        if (item.getName() == null) {
            return;
        }
        helper.setText(R.id.item_knowledge_hierarchy_title, item.getName());
        helper.setTextColor(R.id.item_knowledge_hierarchy_title, CommonUtils.randomColor());
        if (item.getChildren() == null) {
            return;
        }
        StringBuilder content = new StringBuilder();
        for (KnowledgeHierarchyData data : item.getChildren()) {
            content.append(data.getName()).append("   ");
        }
        helper.setText(R.id.item_knowledge_hierarchy_content, content.toString());
    }
}
