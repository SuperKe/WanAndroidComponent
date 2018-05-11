package com.demo.chenke.componentthree.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseFragment
import com.demo.chenke.componentthree.R
import com.demo.chenke.componentthree.bean.NavigationListData
import com.demo.chenke.componentthree.contact.ThreeComponentContact
import com.demo.chenke.componentthree.presenter.ThreeComponentPresenter

@Route(path = "/componentthree/main_page")
class ThreeComponentFragment : BaseFragment<ThreeComponentPresenter>(), ThreeComponentContact.setViewData {


    override fun initPresenter(): ThreeComponentPresenter {
        return ThreeComponentPresenter(this)
    }

    override fun getLayout(): Int {
        return R.layout.three_component_fragment
    }

    override fun initView() {
        presenter.getNaviJason()
    }

    override fun setNaviList(guideList: List<NavigationListData>) {

    }

}
