package com.demo.chenke.componentthree.bean;

import java.util.List;

/**
 * Created by chenke on 2018/5/11.
 * 多布局情况
 */
public class EntityBean {
    public static final int HEAD_TYPE = 1;
    public static final int CONTENT_TYPE = 2;
    private int type;
    private String head;
    private List<FeedArticleData> content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<FeedArticleData> getContent() {
        return content;
    }

    public void setContent(List<FeedArticleData> content) {
        this.content = content;
    }
}
