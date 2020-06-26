package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.LoginMessage;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private UserService userService = new UserServiceImpl();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("application/json;charset=utf-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    ObjectMapper mapper = new ObjectMapper();
    LoginMessage msg;

    String check = request.getParameter("check");
    String ans = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String autoLogin = request.getParameter("auto_login");

    assert username != null;
    assert password != null;
    assert check != null;

    if (check.equalsIgnoreCase(ans)) { // 验证码通过
      boolean flag = userService.login(username, password);
      msg =
          flag
              ? new LoginMessage("登录成功", Boolean.TRUE)
              : new LoginMessage("用户名或密码错误", Boolean.FALSE);

      if (flag) {
        Cookie cookie1;
        Cookie cookie2;
        if (autoLogin != null) { // 记住密码
          cookie1 = new Cookie("username", username);
          cookie2 = new Cookie("password", password);
          cookie1.setMaxAge(7 * 24 * 60 * 60);
          cookie2.setMaxAge(7 * 24 * 60 * 60);
          response.addCookie(cookie1);
          response.addCookie(cookie2);
        } else {
          cookie1 = new Cookie("username", "");
          cookie2 = new Cookie("password", "");
          cookie1.setMaxAge(0);
          cookie2.setMaxAge(0);
          response.addCookie(cookie1);
          response.addCookie(cookie2);
        }
          HttpSession session = request.getSession();
          session.setAttribute("username",username);
          session.setAttribute("password",username);

      }
    } else {
      msg = new LoginMessage("验证码错误", Boolean.FALSE);
    }

    out.write(mapper.writeValueAsString(msg));
    out.flush();
    out.close();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}
