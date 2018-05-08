package com.demo.chenke.componentone.adapter;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.chenke.componentone.R;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class KnowledgeHierarchyListViewHolder extends BaseViewHolder {

    CardView mItemSearchPagerGroup;
    ImageView mItemSearchPagerLikeIv;
    TextView mItemSearchPagerTitle;
    TextView mItemSearchPagerAuthor;
    TextView mTagGreenTv;
    TextView mTagRedTv;
    TextView mItemSearchPagerChapterName;
    TextView mItemSearchPagerNiceDate;

    public KnowledgeHierarchyListViewHolder(View view) {
        super(view);
        mItemSearchPagerGroup = view.findViewById(R.id.item_search_pager_group);
        mItemSearchPagerLikeIv = view.findViewById(R.id.item_search_pager_like_iv);
        mItemSearchPagerTitle = view.findViewById(R.id.item_search_pager_title);
        mItemSearchPagerAuthor = view.findViewById(R.id.item_search_pager_author);
        mTagGreenTv = view.findViewById(R.id.item_search_pager_tag_green_tv);
        mTagRedTv = view.findViewById(R.id.item_search_pager_tag_red_tv);
        mItemSearchPagerChapterName = view.findViewById(R.id.item_search_pager_chapterName);
        mItemSearchPagerNiceDate = view.findViewById(R.id.item_search_pager_niceDate);
    }
}
