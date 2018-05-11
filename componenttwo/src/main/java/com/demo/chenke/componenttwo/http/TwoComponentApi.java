package com.demo.chenke.componenttwo.http;

import com.demo.chenke.basiclib.retroft.BaseApiImpl;


/**
 * Created by chenke on 2018/5/9.
 */

public class TwoComponentApi extends BaseApiImpl {
    public static TwoComponentApi api = new TwoComponentApi(TwoComponentService.BASE_URL);

    public TwoComponentApi(String baseUrl) {
        super(baseUrl);
    }

    public static TwoComponentService getInstance() {
        return api.getRetrofit().create(TwoComponentService.class);
    }
}
