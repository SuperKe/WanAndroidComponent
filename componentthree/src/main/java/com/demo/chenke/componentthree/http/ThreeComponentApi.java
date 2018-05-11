package com.demo.chenke.componentthree.http;

import com.demo.chenke.basiclib.retroft.BaseApiImpl;

/**
 * Created by chenke on 2018/5/10.
 */

public class ThreeComponentApi extends BaseApiImpl {

    public static ThreeComponentApi api = new ThreeComponentApi(ThreeComponentRetrofitService.BASE_URL);

    public ThreeComponentApi(String baseUrl) {
        super(baseUrl);
    }

    public static ThreeComponentRetrofitService getInstance() {
        return api.getRetrofit().create(ThreeComponentRetrofitService.class);
    }
}
