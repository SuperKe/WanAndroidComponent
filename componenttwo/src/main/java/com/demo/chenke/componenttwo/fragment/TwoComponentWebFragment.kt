package com.demo.chenke.componenttwo.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseWebFragment

@Route(path = "/componenttwo/web_fragment")
class TwoComponentWebFragment : BaseWebFragment() {
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
}