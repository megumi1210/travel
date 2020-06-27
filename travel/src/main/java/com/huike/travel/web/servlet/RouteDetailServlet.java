package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.huike.travel.domain.RestfulResponse;
import com.huike.travel.domain.RouteDetailResult;
import com.huike.travel.domain.User;
import com.huike.travel.domain.UserInfo;
import com.huike.travel.service.FavoriteService;
import com.huike.travel.service.RouteDetailService;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.FavoriteServiceImpl;
import com.huike.travel.service.impl.RouteDetailServiceImpl;
import com.huike.travel.service.impl.UserServiceImpl;
import com.huike.travel.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/RouteDetail")
public class RouteDetailServlet extends HttpServlet {

  private RouteDetailService routeDetailService = new RouteDetailServiceImpl();
  private FavoriteService favoriteService = new FavoriteServiceImpl();
  private UserService userService = new UserServiceImpl();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String path = request.getQueryString();


    RestfulResponse restfulResponse = WebUtils.getRestfulResponse(response);
    int uid = -1;
    int rid = -1;
    RouteDetailResult result = null;

    try {
      String value = WebUtils.getPathVariable(path, "rid");
      rid = Integer.parseInt(value);
    } catch (Exception e) {
      e.printStackTrace();
    }


    UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    if(userInfo == null) userInfo = (UserInfo) request.getAttribute("userInfo");

    uid = userInfo.getUid();

    if (rid == -1) {
      restfulResponse.writeOnce(null);
    } else {
      result = routeDetailService.findDetailByRid(rid);
      if (uid != -1) { // 如果用户登录过,查询是否被收藏
        boolean star = favoriteService.alreadyStared(rid, uid);
        result.setStar(star);
      }
      restfulResponse.writeOnce(result);
    }
  }
}
