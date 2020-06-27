package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.RestfulResponse;
import com.huike.travel.domain.User;
import com.huike.travel.domain.UserInfo;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;
import com.huike.travel.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//实现自动登录
@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {

  private UserService userService = new UserServiceImpl();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    RestfulResponse restfulResponse = WebUtils.getRestfulResponse(response);


    UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
    if(userInfo == null){
      //从cookie 中读取信息
      Cookie[] cookies = request.getCookies();
      String username = null;
      String password = null;

      if (cookies != null && cookies.length > 0){
        //获取 cookie值
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
          //将查询到的信息传递
          User user = userService.findUserInfoBy(username);
          userInfo = new UserInfo(username,user.getUid());
          request.setAttribute("userInfo",userInfo);
          request.getSession().setAttribute("userInfo",userInfo);
        }
      }

    }



     restfulResponse.writeOnce(userInfo);
  }
}
