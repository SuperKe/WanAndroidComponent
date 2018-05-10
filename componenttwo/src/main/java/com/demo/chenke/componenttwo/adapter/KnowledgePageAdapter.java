package com.demo.chenke.componenttwo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.demo.chenke.componenttwo.R;
import com.demo.chenke.componenttwo.bean.FeedArticleData;

import java.util.List;


/**
 * Created by chenke on 2018/5/3.
 */

public class KnowledgePageAdapter extends BaseQuickAdapter<FeedArticleData, KnowledgeViewHolder> {

    private boolean isSearchPage;
    private boolean isNightMode;

    public KnowledgePageAdapter(@Nullable List<FeedArticleData> data) {
        super(R.layout.fragment_item, data);
    }

    @Override
    protected void convert(KnowledgeViewHolder helper, FeedArticleData article) {
        if (!TextUtils.isEmpty(article.getTitle())) {
            helper.mItemSearchPagerTitle.setText(Html.fromHtml(article.getTitle()));
        }
        if (article.isCollect()) {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like);
        } else {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_not_selected);
        }
        if (!TextUtils.isEmpty(article.getAuthor())) {
            helper.mItemSearchPagerAuthor.setText(article.getAuthor());
        }
        setTag(helper, article);
        if (!TextUtils.isEmpty(article.getChapterName())) {
            String classifyName = article.getSuperChapterName() + " / " + article.getChapterName();
            if (article.isCollect()) {
                helper.mItemSearchPagerChapterName.setText(article.getChapterName());
            } else {
                helper.mItemSearchPagerChapterName.setText(classifyName);
            }
        }
        if (!TextUtils.isEmpty(article.getNiceDate())) {
            helper.mItemSearchPagerNiceDate.setText(article.getNiceDate());
        }
        if (isSearchPage) {
            CardView cardView = helper.getView(R.id.item_search_pager_group);
            cardView.setForeground(null);
            if (isNightMode) {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.color.card_color));
            } else {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_search_item_bac));
            }
        }

        helper.addOnClickListener(R.id.item_search_pager_chapterName);
        helper.addOnClickListener(R.id.item_search_pager_like_iv);
        helper.addOnClickListener(R.id.item_search_pager_tag_red_tv);
    }

    private void setTag(KnowledgeViewHolder helper, FeedArticleData article) {
        helper.mTagGreenTv.setVisibility(View.GONE);
        helper.mTagRedTv.setVisibility(View.GONE);
        if (article.isCollect()) {
            return;
        }
        if (article.getSuperChapterName().contains(mContext.getString(R.string.open_project))) {
            helper.mTagRedTv.setVisibility(View.VISIBLE);
            helper.mTagRedTv.setText(R.string.project);
            helper.mTagRedTv.setTextColor(ContextCompat.getColor(mContext, R.color.light_deep_red));
            helper.mTagRedTv.setBackgroundResource(R.drawable.selector_tag_red_background);
        }

        if (article.getSuperChapterName().contains(mContext.getString(R.string.navigation))) {
            helper.getView(R.id.item_search_pager_tag_red_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_search_pager_tag_red_tv, R.string.navigation);
            helper.setTextColor(R.id.item_search_pager_tag_red_tv, ContextCompat.getColor(mContext, R.color.light_deep_red));
            helper.setBackgroundRes(R.id.item_search_pager_tag_red_tv, R.drawable.selector_tag_red_background);
        }

        if (article.getNiceDate().contains(mContext.getString(R.string.minute))
                || article.getNiceDate().contains(mContext.getString(R.string.hour))
                || article.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.getView(R.id.item_search_pager_tag_green_tv).setVisibility(View.VISIBLE);
            helper.setText(R.id.item_search_pager_tag_green_tv, R.string.text_new);
            helper.setTextColor(R.id.item_search_pager_tag_green_tv, ContextCompat.getColor(mContext, R.color.light_green));
            helper.setBackgroundRes(R.id.item_search_pager_tag_green_tv, R.drawable.shape_tag_green_background);
        }
    }

    public boolean isSearchPage() {
        return isSearchPage;
    }

    public void setSearchPage(boolean searchPage) {
        isSearchPage = searchPage;
    }

    public boolean isNightMode() {
        return isNightMode;
    }

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }
}
