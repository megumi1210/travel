package com.huike.travel.domain;

import java.util.Objects;

public class PageParam {



    //当前页
    private int PageNum;

    //当前页的数量
    private int pageSize;

    //数据库查询起始点
    private  int start ;

    //是否开启排序功能
    private boolean useOrderBy =false;

    //排序的名称
    private String orderByName = null;

    private Order  order = Order.ASC; //默认升序排列



    public PageParam(){
        this(1,8);
    }

    public PageParam(int pageNum, int pageSize) {
        this.PageNum = pageNum > 0 ? pageNum :1;
        this.pageSize = pageSize;
        resetStart();
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int pageNum) {
        PageNum = pageNum > 0 ? pageNum :1;
        resetStart();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize > 0 ? pageSize : 8;
        resetStart();
    }


    private void resetStart(){
        this.start = (this.PageNum -1)* pageSize;
    }

    public int getStart() {
        return start;
    }


    public void setStart(int start) {
        this.start = start;
    }

    public boolean isUseOrderBy() {
        return useOrderBy;
    }

    public void setUseOrderBy(boolean useOrderBy) {
        this.useOrderBy = useOrderBy;
    }

    public String getOrderByName() {
        return orderByName;
    }

    public void setOrderByName(String orderByName) {
        this.orderByName = orderByName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageParam pageParam = (PageParam) o;
        return PageNum == pageParam.PageNum &&
                pageSize == pageParam.pageSize &&
                start == pageParam.start;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PageNum, pageSize, start);
    }
}
