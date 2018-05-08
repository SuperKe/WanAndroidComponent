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


    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setSupportActionBar(common_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        tv_toolbar_title.text = "首页"
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, common_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        loadMainPageData()
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
    private fun loadMainPageData() {
        var mainfragment = ARouter.getInstance().build("/componentone/main_page").navigation()
        if (mainfragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_group, mainfragment as Fragment).commitAllowingStateLoss()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
