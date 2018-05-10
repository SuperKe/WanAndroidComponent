package com.demo.chenke.componenttwo.fragment

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseFragment
import com.demo.chenke.basiclib.utils.CommonUtils
import com.demo.chenke.commonlib.Constants
import com.demo.chenke.componenttwo.R
import com.demo.chenke.componenttwo.activity.TwoComponentStepActivity
import com.demo.chenke.componenttwo.adapter.KnowledgeHierarchyAdapter
import com.demo.chenke.componenttwo.bean.KnowledgeHierarchyData
import com.demo.chenke.componenttwo.contact.TowComponentContact
import com.demo.chenke.componenttwo.presenter.TwoComponentPresenter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import java.io.Serializable

@Route(path = "/componenttwo/main_page")
class TwoComponentFragment : BaseFragment<TowComponentContact.presenter>(), TowComponentContact.setDataView {
    private var adapter: KnowledgeHierarchyAdapter? = null
    private var dataList: ArrayList<KnowledgeHierarchyData>? = null
    private var recycler: RecyclerView? = null
    private var smartView: SmartRefreshLayout? = null
    private var isLoadMore = false
    private var isRefresh = false
    override fun getLayout(): Int {
        return R.layout.two_component_fragment
    }

    override fun initPresenter(): TowComponentContact.presenter {
        return TwoComponentPresenter(this)
    }

    override fun initView() {
        dataList = ArrayList()
        initSmartRefresh()
        initAdapter()
        presenter.getKnowledgeList()
    }

    override fun showLoading(msg: String?) {
        if (!isRefresh && !isLoadMore) {
            super.showLoading(msg)
        }
    }

    override fun reload() {
        presenter.getKnowledgeList()
    }

    override fun setViewKnowledgeList(childrenList: MutableList<KnowledgeHierarchyData>) {
        if (!isLoadMore) {
            dataList!!.clear()
            dataList!!.addAll(childrenList)
            adapter!!.notifyDataSetChanged()
        } else {
            CommonUtils.showMessage(_mActivity, getString(R.string.load_more_no_data))
        }
    }

    private fun initSmartRefresh() {
        smartView = rootView!!.findViewById(R.id.smart_view)
        smartView!!.setOnRefreshListener {
            isRefresh = true
            isLoadMore = false
            presenter.getKnowledgeList()
            it.finishRefresh(1000)
        }
        smartView!!.setOnLoadMoreListener {
            isRefresh = false
            isLoadMore = true
            presenter.getKnowledgeList()
            it.finishLoadMore(1000)
        }
    }

    private fun initAdapter() {
        recycler = rootView!!.findViewById(R.id.recycler_view)
        adapter = KnowledgeHierarchyAdapter(dataList)
        recycler!!.layoutManager = LinearLayoutManager(_mActivity)
        recycler!!.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
            if (adapter.data.size <= 0 || adapter.data.size <= position) {
                return@setOnItemClickListener
            }
            val options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view))
            val intent = Intent(_mActivity, TwoComponentStepActivity::class.java)
            intent.putExtra(Constants.ARG_PARAM1, adapter.data[position] as Serializable)
            if (!Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }
        }
    }
}
