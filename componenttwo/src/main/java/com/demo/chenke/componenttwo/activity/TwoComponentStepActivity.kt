package com.demo.chenke.componenttwo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.demo.chenke.basiclib.mvpbase.baseImpl.UIActivity
import com.demo.chenke.commonlib.Constants
import com.demo.chenke.commonlib.utils.StatusBarUtil
import com.demo.chenke.componenttwo.R
import com.demo.chenke.componenttwo.bean.KnowledgeHierarchyData
import kotlinx.android.synthetic.main.two_component_step_activity.*
import java.util.ArrayList

class TwoComponentStepActivity : UIActivity() {
    private var knowledgeHierarchyDataList: List<KnowledgeHierarchyData>? = null
    private val mFragments = ArrayList<Fragment>()
    override fun setLayout(): Int {
        return R.layout.two_component_step_activity
    }

    override fun initView() {
        setToolBar(common_toolbar, "开发环境")
        supportActionBar!!.setDisplayShowCustomEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)//不显示默认title
        StatusBarUtil.immersive(this)
        StatusBarUtil.setPaddingSmart(this, common_toolbar)
        common_toolbar!!.setNavigationOnClickListener {
            onBackPressedSupport()
        }
        initData()
        initViewPager()
    }

    private fun initData() {
        val knowledgeHierarchyData = intent.getSerializableExtra(Constants.ARG_PARAM1) as KnowledgeHierarchyData
        assert(knowledgeHierarchyData != null && knowledgeHierarchyData!!.name != null)
        tv_toolbar_title.text = knowledgeHierarchyData!!.name.trim()
        knowledgeHierarchyDataList = knowledgeHierarchyData!!.children
        if (knowledgeHierarchyDataList == null) {
            return
        }
        knowledgeHierarchyDataList!!.mapTo(mFragments) {
            var bundle = Bundle()
            bundle.putInt(Constants.ARG_PARAM1, it.id)
            ARouter.getInstance().build("/componenttwo/konwledge").with(bundle).navigation() as Fragment
        }
    }

    private fun initViewPager() {
        pager_content.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mFragments[position]
            }

            override fun getCount(): Int {
                return mFragments?.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return knowledgeHierarchyDataList!![position].name
            }

            override fun getItemPosition(`object`: Any): Int {
                return PagerAdapter.POSITION_NONE
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

            }
        }
        tab_layout.setViewPager(pager_content)
    }
}
