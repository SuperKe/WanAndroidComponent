package com.demo.chenke.otherlib.event;

/**
 * 收藏页的event
 */
public class CollectEvent {

    private boolean isCollect;
    private int position;

    public CollectEvent(boolean isCollect, int position) {
        this.isCollect = isCollect;
        this.position = position;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
