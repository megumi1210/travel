package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.User;
import com.huike.travel.domain.UserInfo;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet {

  private UserService userService = new UserServiceImpl();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("application/json;charset=utf-8");
    PrintWriter out = response.getWriter();
    ObjectMapper mapper = new ObjectMapper();

    Cookie[] cookie = request.getCookies();
    String username = null;
    String password = null;
    UserInfo userInfo = null;

    if (cookie != null && cookie.length > 0) {

      for (Cookie c : cookie) {
        if (c.getName().equals("username")) {
          username = c.getValue();
        }

        if (c.getName().equals("password")) {
          password = c.getValue();
        }
      }
    }

    if (username != null && password != null) {
      User user = userService.findUserInfoBy(username);
      assert user != null;
      userInfo = new UserInfo(username, user.getUid());
    }

    out.write(mapper.writeValueAsString(userInfo));
    System.out.println(mapper.writeValueAsString(userInfo));
    out.flush();
    out.close();
  }
}
