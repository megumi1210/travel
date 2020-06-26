package com.huike.travel.service.impl;

import com.huike.travel.domain.*;
import com.huike.travel.service.*;

import java.util.ArrayList;
import java.util.List;

public class RouteDetailServiceImpl implements RouteDetailService {

  private SellerService sellerService = new SellerServiceImpl();
  private RouteService routeService = new RouteServiceImpl();
  private RouteImgService routeImgService = new RouteImgServiceImpl();
  private CategoryService categoryService = new CategoryServiceImpl();

  @Override
  public RouteDetailResult findDetailByRid(int rid) {
    Route route = routeService.findRouteByRid(rid);
    if (route != null) {
      List<RouteImg> routeImgList = routeImgService.findRouteImgByRid(rid);
      Seller seller = sellerService.findSellerBySid(route.getSid());
      Category category = categoryService.findCategoryByCid(route.getCid());

      RouteDetailResult result = new RouteDetailResult(route);

      if (seller == null) {
        result.setHasSeller(false);
        result.getRoute().setSeller(null);
      } else {
        result.setHasSeller(true);
        result.getRoute().setSeller(seller);
      }

      if (category == null) {
        result.setHasCategory(false);
        result.getRoute().setSeller(null);
      } else {
        result.setHasCategory(true);
        result.getRoute().setCategory(category);
      }

      if (routeImgList != null && routeImgList.size() > 0) {
        result.setHasImg(true);
        int size = routeImgList.size();
        result.setImgSize(size);

        RouteImg first = routeImgList.get(0);

        result.setFirstImg(first);
        result.getRoute().setRouteImgList(routeImgList);

      } else {
        result.setHasImg(false);
        result.getRoute().setRouteImgList(null);
      }

      return result;

    } else {
      return null;
    }
  }
}
