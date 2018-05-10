package com.demo.chenke.componenttwo.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.chenke.componenttwo.R;


/**
 * @author quchao
 * @date 2018/2/23
 */

public class KnowledgeHierarchyViewHolder extends BaseViewHolder {

    TextView mTitle;
    TextView mContent;

    public KnowledgeHierarchyViewHolder(View view) {
        super(view);
        mTitle = view.findViewById(R.id.item_knowledge_hierarchy_title);
        mContent = view.findViewById(R.id.item_knowledge_hierarchy_content);
    }
}
