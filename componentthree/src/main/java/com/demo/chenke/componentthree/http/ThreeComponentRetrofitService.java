package com.demo.chenke.componentthree.http;

import com.demo.chenke.basiclib.BaseResponse;
import com.demo.chenke.componentthree.bean.NavigationListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by chenke on 2018/5/10.
 */

public interface ThreeComponentRetrofitService {
    String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 导航
     * http://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();
}
