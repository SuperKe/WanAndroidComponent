package com.demo.chenke.componenttwo.http;

import com.demo.chenke.basiclib.BaseResponse;
import com.demo.chenke.componenttwo.bean.FeedArticleListData;
import com.demo.chenke.componenttwo.bean.KnowledgeHierarchyData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chenke on 2018/5/9.
 */

public interface TwoComponentService {
    String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     *
     * @return 广告栏数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid  second page id
     * @return 知识体系feed文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(@Path("page") int page, @Query("cid") int cid);
}
