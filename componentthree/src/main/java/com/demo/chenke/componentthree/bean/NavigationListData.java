package com.demo.chenke.componentthree.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class NavigationListData implements Serializable {
    public static final int HEAD_TYPE = 1;
    public static final int CONTENT_TYPE = 2;
    private int type;

    /**
     * "articles": [],
     * "cid": 272,
     * "name": "常用网站"
     */

    private List<FeedArticleData> articles;
    private int cid;
    private String name;

    public List<FeedArticleData> getArticles() {
        return articles;
    }

    public void setArticles(List<FeedArticleData> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
