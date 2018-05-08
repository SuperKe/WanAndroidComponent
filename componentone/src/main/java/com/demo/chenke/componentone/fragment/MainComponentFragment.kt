package com.demo.chenke.componentone.fragment

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.alibaba.android.arouter.facade.annotation.Route
import com.demo.chenke.basiclib.mvpbase.baseImpl.BaseFragment
import com.demo.chenke.componentone.GlideImageLoader
import com.demo.chenke.componentone.R
import com.demo.chenke.componentone.adapter.MainPageAdapter
import com.demo.chenke.componentone.bean.BannerData
import com.demo.chenke.componentone.bean.FeedArticleData
import com.demo.chenke.componentone.contact.MainDataContact
import com.demo.chenke.componentone.presenter.MainFragmentPresenter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer

@Route(path = "/componentone/main_page")
class MainComponentFragment : BaseFragment<MainFragmentPresenter>(), MainDataContact.setDataView {
    private var articleList: MutableList<FeedArticleData>? = null
    private var adapter: MainPageAdapter? = null
    private var mBanner: Banner? = null
    private var recylcer: RecyclerView? = null
    private var smartView: SmartRefreshLayout? = null
    override fun getLayout(): Int {
        return R.layout.fragment_item_list
    }

    override fun initView() {
        initSmartRefresh()
        initAdapter()
        presenter.loadData()//获取数据
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser) {
            presenter.loadData()//获取数据
        }
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onResume() {
        super.onResume()
        if (mBanner != null) {
            mBanner!!.startAutoPlay()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mBanner != null) {
            mBanner!!.stopAutoPlay()
        }
    }

    override fun refreshUI(bannerList: MutableList<BannerData>, articleList: MutableList<FeedArticleData>) {
        this.articleList!!.clear()
        addBanner(bannerList)
        addArticle(articleList, true)
    }

    override fun addBanner(bannerList: MutableList<BannerData>) {
        showBannerData(bannerList)
    }

    override fun addArticle(articleList: MutableList<FeedArticleData>, isRefresh: Boolean) {
        if (isRefresh) {
            this.articleList!!.clear()
        }
        this.articleList!!.addAll(articleList)
        adapter!!.notifyDataSetChanged()
    }

    override fun initPresenter(): MainFragmentPresenter {
        return MainFragmentPresenter(this)
    }

    private fun initAdapter() {
        articleList = ArrayList()
        adapter = MainPageAdapter(articleList)
        recylcer = rootView.findViewById(R.id.recycler_view)
        recylcer!!.layoutManager = LinearLayoutManager(activity)
        var banner = LayoutInflater.from(activity).inflate(R.layout.head_banner, null)
        mBanner = banner.findViewById(R.id.head_banner)
        adapter!!.addHeaderView(banner)
        recylcer!!.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
            if (adapter.data.size <= 0 || adapter.data.size < position) {
                return@setOnItemClickListener
            }
            startActivity("/componentone/article_detail", presenter.getArticleDetailActivityBundle(
                    (adapter.data[position] as FeedArticleData).id,
                    (adapter.data[position] as FeedArticleData).title,
                    (adapter.data[position] as FeedArticleData).link,
                    false, false, false
            ))
        }
        adapter!!.setOnItemChildClickListener { _, view, position ->
            when (view.id) {

            }
        }
    }

    private fun initSmartRefresh() {
        //刷新
        smartView = rootView.findViewById(R.id.smart_view)
        smartView!!.setOnRefreshListener {
            presenter.refresh()
            it.finishRefresh(1000)
        }
        //加载
        smartView!!.setOnLoadMoreListener {
            presenter.loadMore()
            it.finishLoadMore(1000)
        }
    }

    private fun showBannerData(bannerDataList: List<BannerData>) {
        var mBannerTitleList = ArrayList<String>()
        val bannerImageList = ArrayList<String>()
        var mBannerUrlList = ArrayList<String>()
        for (bannerData in bannerDataList) {
            mBannerTitleList.add(bannerData.title)
            bannerImageList.add(bannerData.imagePath)
            mBannerUrlList.add(bannerData.url)
        }
        //设置banner样式
        mBanner!!.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
        //设置图片加载器
        mBanner!!.setImageLoader(GlideImageLoader())
        //设置图片集合
        mBanner!!.setImages(bannerImageList)
        //设置banner动画效果
        mBanner!!.setBannerAnimation(Transformer.DepthPage)
        //设置标题集合（当banner样式有显示title时）
        mBanner!!.setBannerTitles(mBannerTitleList)
        //设置自动轮播，默认为true
        mBanner!!.isAutoPlay(true)
        //设置轮播时间
        mBanner!!.setDelayTime(bannerDataList.size * 400)
        //设置指示器位置（当banner模式中有指示器时）
        mBanner!!.setIndicatorGravity(BannerConfig.CENTER)
        //banner的点击事件
        mBanner!!.setOnBannerListener {

        }
        //banner设置方法全部调用完毕时最后调用
        mBanner!!.start()
    }

}
