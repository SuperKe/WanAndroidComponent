package com.demo.chenke.componentthree.fragment

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseFragment
import com.demo.chenke.componentthree.R
import com.demo.chenke.componentthree.adapter.NavigationAdapter
import com.demo.chenke.componentthree.bean.NavigationListData
import com.demo.chenke.componentthree.contact.ThreeComponentContact
import com.demo.chenke.componentthree.presenter.ThreeComponentPresenter
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView

@Route(path = "/componentthree/main_page")
class ThreeComponentFragment : BaseFragment<ThreeComponentPresenter>(), ThreeComponentContact.setViewData {
    var dataList: ArrayList<NavigationListData>? = null
    var left_tab: VerticalTabLayout? = null
    var right_recycle: RecyclerView? = null
    var adapter: NavigationAdapter? = null

    override fun initPresenter(): ThreeComponentPresenter {
        return ThreeComponentPresenter(this)
    }

    override fun getLayout(): Int {
        return R.layout.three_component_fragment
    }

    override fun initView() {
        presenter.getNaviJason()
        dataList = ArrayList()
        left_tab = rootView.findViewById(R.id.left_tab)
        right_recycle = rootView.findViewById(R.id.right_recycle)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = NavigationAdapter(dataList)
        right_recycle!!.layoutManager = LinearLayoutManager(_mActivity)
        right_recycle!!.adapter = adapter
        initAdapter()
    }

    override fun setNaviList(guideList: List<NavigationListData>?) {
        this.dataList!!.clear()
        this.dataList!!.addAll(guideList!!)
        adapter!!.notifyDataSetChanged()
        initTabLayout()
    }

    private fun initTabLayout() {
        if (dataList != null) {
            left_tab!!.setTabAdapter(object : TabAdapter {
                override fun getIcon(position: Int): ITabView.TabIcon? {
                    return null
                }

                override fun getBadge(position: Int): ITabView.TabBadge? {
                    return null
                }

                override fun getBackground(position: Int): Int {
                    return -1
                }

                override fun getTitle(position: Int): ITabView.TabTitle {
                    return ITabView.TabTitle.Builder()
                            .setContent(dataList!![position].name)
                            .setTextColor(ContextCompat.getColor(_mActivity, R.color.shallow_green),
                                    ContextCompat.getColor(_mActivity, R.color.shallow_grey))
                            .build()
                }

                override fun getCount(): Int {
                    return dataList?.size ?: 0
                }
            })
        }
    }
}
