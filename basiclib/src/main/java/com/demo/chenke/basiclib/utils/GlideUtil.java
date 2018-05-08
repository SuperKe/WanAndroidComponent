package com.demo.chenke.basiclib.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by chenke on 2018/5/4.
 */

public class GlideUtil {
    public static void loadImage(Context context, Object o, ImageView imageView) {
        Glide.with(context).load(o).into(imageView);
    }
}
