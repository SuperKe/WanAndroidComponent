package com.demo.chenke.componenttwo.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseFragment
import com.demo.chenke.basiclib.utils.CommonUtils
import com.demo.chenke.commonlib.Constants
import com.demo.chenke.componenttwo.R
import com.demo.chenke.componenttwo.adapter.KnowledgePageAdapter
import com.demo.chenke.componenttwo.bean.FeedArticleData
import com.demo.chenke.componenttwo.contact.KonwledgeContact
import com.demo.chenke.componenttwo.presenter.TwoComponentKonwledgePresenter
import com.scwang.smartrefresh.layout.SmartRefreshLayout

@Route(path = "/componenttwo/konwledge")
class KonwledgeHierarchyFragment : BaseFragment<TwoComponentKonwledgePresenter>(), KonwledgeContact.setDataView {
    var dataList: ArrayList<FeedArticleData>? = null
    var recyclerView: RecyclerView? = null
    var smartView: SmartRefreshLayout? = null
    var mCurrentPage = 0
    var cid = 0
    var isRefresh = false
    var isLoadMore = false
    var mAdapter: KnowledgePageAdapter? = null
    override fun getLayout(): Int {
        return R.layout.fragment_konwledge_hierarchy
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBundle()
    }

    override fun initPresenter(): TwoComponentKonwledgePresenter {
        return TwoComponentKonwledgePresenter(this)
    }

    override fun initView() {
        dataList = ArrayList()
        showLoading("")
        presenter.getKonwledgeList(mCurrentPage, cid)
        initSmartView()
        initAdapter()
    }

    private fun initBundle() {
        var bundle = arguments
        cid = bundle!!.getInt(Constants.ARG_PARAM1, 0)
        if (cid == 0) {
            return
        }
        mCurrentPage = 0
    }

    override fun showLoading(msg: String?) {
        super.showLoading(msg)
    }

    private fun initSmartView() {
        smartView = rootView.findViewById(R.id.smart_view)
        smartView!!.setOnRefreshListener {
            isRefresh = true
            isLoadMore = false
            mCurrentPage = 0
            presenter.getKonwledgeList(mCurrentPage, cid)
            it.finishRefresh(1000)
        }
        smartView!!.setOnLoadMoreListener {
            isRefresh = false
            isLoadMore = true
            mCurrentPage++
            presenter.getKonwledgeList(mCurrentPage, cid)
            it.finishLoadMore(1000)
        }
    }

    private fun initAdapter() {
        recyclerView = rootView.findViewById(R.id.recycler_view)
        mAdapter = KnowledgePageAdapter(dataList)
        recyclerView!!.layoutManager = LinearLayoutManager(_mActivity)
        recyclerView!!.adapter = mAdapter
        mAdapter!!.setOnItemClickListener { adapter, view, position ->
            startActivity("/componenttwo/web_activity", presenter.goToWebView(
                    (adapter.data[position] as FeedArticleData).title
                    , position
                    , (adapter.data[position] as FeedArticleData).link
                    , (adapter.data[position] as FeedArticleData).isCollect
            ))
        }
    }

    override fun setKonwledgeList(dataList: List<FeedArticleData>) {
        if (isRefresh) {
            this.dataList!!.clear()
            this.dataList!!.addAll(dataList)
        }
        if (isLoadMore) {
            mCurrentPage -= mCurrentPage//同步页数
            dataList.isEmpty()
            CommonUtils.showMessage(_mActivity, getString(R.string.load_more_no_data))
        }
        this.dataList!!.addAll(dataList)
        mAdapter!!.notifyDataSetChanged()
    }

    override fun reload() {
        showLoading("")
        presenter.getKonwledgeList(mCurrentPage, cid)
    }
}
