package com.demo.chenke.componentthree.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.demo.chenke.componentthree.R;
import com.demo.chenke.componentthree.bean.NavigationListData;

import java.util.List;

/**
 * O
 * Created by chenke on 2018/5/11.
 * 多布局视图
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationListData, BaseViewHolder> {

    public NavigationAdapter(List<NavigationListData> item) {
        super(item);
        setMultiTypeDelegate(new MultiTypeDelegate<NavigationListData>() {
            @Override
            protected int getItemType(NavigationListData entity) {
                return entity.getType();
            }
        });
        getMultiTypeDelegate()
                .registerItemType(NavigationListData.HEAD_TYPE, R.layout.item_header)
                .registerItemType(NavigationListData.CONTENT_TYPE, R.layout.item_content);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationListData item) {
        switch (helper.getItemViewType()) {
            case NavigationListData.HEAD_TYPE:
                ((TextView) helper.itemView.findViewById(R.id.item_navigation_tv)).setText(item.getName());
                break;
            case NavigationListData.CONTENT_TYPE:
                ((TextView) helper.itemView.findViewById(R.id.content_text)).setText(item.getName());
                break;
        }
    }
}
