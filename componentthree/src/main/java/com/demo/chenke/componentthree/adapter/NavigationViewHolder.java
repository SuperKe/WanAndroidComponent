package com.demo.chenke.componentthree.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.chenke.componentthree.R;

/**
 * Created by chenke on 2018/5/11.
 */

public class NavigationViewHolder extends BaseViewHolder {
    TextView textView;

    public NavigationViewHolder(View view) {
        super(view);
        textView = view.findViewById(R.id.item_navigation_tv);
    }
}
