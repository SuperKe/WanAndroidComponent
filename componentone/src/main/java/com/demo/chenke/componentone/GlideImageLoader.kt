package com.demo.chenke.componentone

import android.content.Context
import android.widget.ImageView
import com.demo.chenke.basiclib.utils.GlideUtil
import com.youth.banner.loader.ImageLoader

/**
 * Created by chenke on 2018/5/4.
 */
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        GlideUtil.loadImage(context, path, imageView)
    }
}