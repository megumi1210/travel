package com.huike.travel.domain;

public class RouteDetailResult {

   private  Route route;

   private RouteImg firstImg;

   private  int  imgSize;

   private  boolean hasImg;

   private  boolean hasCategory;

   private  boolean hasSeller;

   private  boolean star =false;  //默认没有收藏过

    public RouteDetailResult(Route route) {
        assert  route !=null;
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public RouteImg getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(RouteImg firstImg) {
        this.firstImg = firstImg;
    }

    public int getImgSize() {
        return imgSize;
    }

    public void setImgSize(int imgSize) {
        this.imgSize = imgSize;
    }

    public boolean isHasImg() {
        return hasImg;
    }

    public void setHasImg(boolean hasImg) {
        this.hasImg = hasImg;
    }

    public boolean isHasCategory() {
        return hasCategory;
    }

    public void setHasCategory(boolean hasCategory) {
        this.hasCategory = hasCategory;
    }

    public boolean isHasSeller() {
        return hasSeller;
    }

    public void setHasSeller(boolean hasSeller) {
        this.hasSeller = hasSeller;
    }

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }
}
