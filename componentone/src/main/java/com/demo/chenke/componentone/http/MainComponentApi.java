package com.demo.chenke.componentone.http;

import com.demo.chenke.basiclib.retroft.BaseApiImpl;

/**
 * Created by chenke on 2018/5/2.
 */

public class MainComponentApi extends BaseApiImpl {
    public static MainComponentApi api = new MainComponentApi(MainComponentRetrofitService.BASE_URL);

    public MainComponentApi(String baseUrl) {
        super(baseUrl);
    }

    public static MainComponentRetrofitService getInstance() {
        return api.getRetrofit().create(MainComponentRetrofitService.class);
    }
}
