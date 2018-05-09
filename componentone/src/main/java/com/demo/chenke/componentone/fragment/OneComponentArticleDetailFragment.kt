package com.demo.chenke.componentone.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseWebFragment

@Route(path = "/componentone/article_detail_web_view")
class OneComponentArticleDetailFragment : BaseWebFragment() {
    // TODO: Rename and change types of parameters
    private var url: String? = null
    private val ARG_URL = "url"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            url = arguments!!.getString(ARG_URL)
        }
    }

    override fun setUrl(): String {
        return url!!
    }
}// Required empty public constructor
