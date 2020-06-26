package com.huike.travel.web.servlet;

import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;
import com.huike.travel.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

  private UserService userService = new UserServiceImpl();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String code = WebUtils.getPathVariable(request.getQueryString(), "code");
    String value = WebUtils.getPathVariable(request.getQueryString(), "uid");
    int uid = -1;
    try {
      uid = Integer.parseInt(value);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (code != null && uid != -1) {
      userService.activeUser(uid, code);
    }

    response.sendRedirect("index.html");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}
