package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.huike.travel.domain.RouteDetailResult;
import com.huike.travel.domain.User;
import com.huike.travel.domain.UserInfo;
import com.huike.travel.service.FavoriteService;
import com.huike.travel.service.RouteDetailService;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.FavoriteServiceImpl;
import com.huike.travel.service.impl.RouteDetailServiceImpl;
import com.huike.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    String pathVariable = request.getQueryString();
    assert pathVariable != null;

    PrintWriter out = response.getWriter();
    response.setContentType("application/json;charset=utf-8");
    ObjectMapper mapper = new ObjectMapper();
    int uid = -1;
    String username = null;
    String password = null;
    RouteDetailResult result = null;
    int rid = -1;
    int start = pathVariable.indexOf("rid");
    if (start != -1) {
      int end = pathVariable.indexOf("&", start);
      try {
        rid = end == -1 ? Integer.parseInt(pathVariable.substring(start + 4)) : -1;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie c : cookies) {
        if (c.getName().equals("username")) {
          username = c.getValue();
        }

        if (c.getName().equals("password")) {
          password = c.getValue();
        }
      }
    }

    if (username != null && password != null) {
      if (userService.login(username, password)) { // 如果验证登录成功放行
        // 将查询到的信息传递
        User user = userService.findUserInfoBy(username);
        uid = user.getUid();
      }
    }

    if (rid == -1) {
      out.write(mapper.writeValueAsString(null));
    } else {
      result = routeDetailService.findDetailByRid(rid);
      if (uid !=-1) { // 如果用户登录过,查询是否被收藏
        boolean star = favoriteService.alreadyStared(rid, uid);
        System.out.println("isStared -->" + star);
        result.setStar(star);
      }
      out.write(mapper.writeValueAsString(result));
    }

    System.out.println(rid);
    System.out.println(mapper.writeValueAsString(routeDetailService.findDetailByRid(rid)));
    out.flush();
    out.close();
  }
}
