package com.huike.travel.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用于封装后端返回前端数据对象
 */
public class ResultInfo implements Serializable {

    private int count; //更新后的收藏数

    private boolean active;  //是否已收藏

    public ResultInfo(int count, boolean active) {
        this.count = count;
        this.active = active;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "count=" + count +
                ", active=" + active +
                '}';
    }
}
