package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.*;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;
import com.huike.travel.util.WebUtils;

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

    RestfulResponse restfulResponse = WebUtils.getRestfulResponse(response);
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
              ? new LoginMessage("登录成功", Boolean.TRUE, Status.SUCCESS.getCode())
              : new LoginMessage("用户名或密码错误", Boolean.FALSE,Status.VALID_WRONG.getCode());

      boolean active = true;
      User user = userService.findUserInfoBy(username);
      assert  user != null;
      //拦截未激活用户
      if(flag){
        String status = user.getStatus();

        active = status.equals("Y");
        if(!active){
           msg = new LoginMessage("账号未激活",Boolean.FALSE,Status.NOT_ACTIVE.getCode());
        }
      }


      if (flag && active) {

        UserInfo userInfo = new UserInfo(username,user.getUid());
        HttpSession session = request.getSession();
        session.setAttribute("userInfo",userInfo);

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


      }
    } else {
      msg = new LoginMessage("验证码错误", Boolean.FALSE,Status.ERROR.getCode());
    }

     restfulResponse.writeOnce(msg);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}
