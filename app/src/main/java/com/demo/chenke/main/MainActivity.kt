package com.demo.chenke.main

import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.demo.chenke.basiclib.mvpbase.baseImpl.UIActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_toolbar.*

@Route(path = "/app/main")
class MainActivity : UIActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mainfragment: Fragment? = null
    private var twofragment: Fragment? = null
    private var fragments: List<Fragment>? = null
    private var lastPosition = 0//记录上一个的position
    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setSupportActionBar(common_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)//显示左边的按钮
        supportActionBar!!.setDisplayShowTitleEnabled(false)//不显示默认title
        tv_toolbar_title.text = "首页"//设置自己的title的（设置了才能在toolBar里显示自定义的title文字）
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, common_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        initFragments()
        initBottomBar()
    }

    private fun initFragments() {
        mainfragment = ARouter.getInstance().build("/componentone/main_page").navigation() as Fragment
        twofragment = ARouter.getInstance().build("/componenttwo/main_page").navigation() as Fragment
        fragments = listOf(mainfragment, twofragment, mainfragment, twofragment).requireNoNulls()
    }


    /**
     * bottomBar的点击事件
     */
    private fun initBottomBar() {
        bottomBar.setOnTabSelectListener {
            when (it) {
                R.id.tab_1 -> {
                    showPage(0)
                    tv_toolbar_title.text = "首页"
                }
                R.id.tab_2 -> {
                    showPage(1)
                    tv_toolbar_title.text = "知识体系"
                }
                R.id.tab_3 -> {
                    showPage(2)
                    tv_toolbar_title.text = "导航"
                }
                R.id.tab_4 -> {
                    showPage(3)
                    tv_toolbar_title.text = "项目"
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_1 -> {
                // Handle the camera action

            }
            R.id.nav_item_2 -> {

            }
            R.id.nav_item_3 -> {

            }
            R.id.nav_item_4 -> {

            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * 装载MainPage的数据(首页)
     */
    private fun showPage(position: Int) {
        if (position >= fragments!!.size) {
            return
        }
        var showFragment = fragments?.get(position)//要显示的fragment
        var hideFragment = fragments?.get(lastPosition)//要隐藏的fragment
        var bt = supportFragmentManager.beginTransaction()//用于操作显示的fragment
        lastPosition = position
        bt.hide(hideFragment)
        if (showFragment != null) {
            if (!showFragment.isAdded) {
                supportFragmentManager.beginTransaction().remove(showFragment).commit()
                bt.add(R.id.fragment_group, showFragment)
            }
            bt.show(showFragment).commitAllowingStateLoss()
        }
    }


    override fun onBackPressedSupport() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedSupport()
        }
    }

}
