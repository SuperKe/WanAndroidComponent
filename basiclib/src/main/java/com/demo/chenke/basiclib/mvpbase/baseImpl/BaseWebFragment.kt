package com.demo.chenke.basiclib.mvpbase.baseImpl

import android.webkit.WebSettings
import com.demo.chenke.basiclib.R
import com.demo.chenke.basiclib.utils.CommonUtils
import com.just.agentweb.AgentWeb


abstract class BaseWebFragment : UIFragment() {

    private var preAgentWeb: AgentWeb.PreAgentWeb? = null
    private var agentWeb: AgentWeb? = null

    override fun getLayout(): Int {
        return R.layout.fragment_base_web
    }

    override fun initView() {
        if (agentWeb == null) {
            createAgent()
        } else {
            preAgentWeb!!.go(setUrl())
        }
    }

    /**
     * 创建AgentWebView
     */
    private fun createAgent() {
        preAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(rootView.findViewById(R.id.root_layout), params)
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.error_view, -1)
                .createAgentWeb()
                .ready()
        agentWeb = preAgentWeb!!.go(setUrl())
        val mWebView = agentWeb!!.webCreator.webView
        val mSettings = mWebView.settings
        mSettings.blockNetworkImage = true//是否加载图片
        mSettings.setAppCacheEnabled(true)
        mSettings.domStorageEnabled = true
        mSettings.databaseEnabled = true
        if (CommonUtils.isNetworkConnected()) {
            mSettings.cacheMode = WebSettings.LOAD_DEFAULT
        } else {
            mSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        mSettings.javaScriptEnabled = true
        mSettings.setSupportZoom(true)
        mSettings.builtInZoomControls = true
        //不显示缩放按钮
        mSettings.displayZoomControls = false
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.useWideViewPort = true
        //缩放至屏幕的大小
        mSettings.loadWithOverviewMode = true
        //自适应屏幕
        mSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
    }

    /**
     * 拦截activity的返回事件，交由fragment来处理
     */
    override fun onBackPressedSupport(): Boolean {
        if (!agentWeb!!.back()) {
            activity.finish()
        } else {
            agentWeb!!.webCreator.webView.goBack()
        }
        return true
    }

    override fun onPause() {
        agentWeb!!.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        agentWeb!!.webLifeCycle.onDestroy()
        super.onDestroyView()
    }

    abstract fun setUrl(): String
}
