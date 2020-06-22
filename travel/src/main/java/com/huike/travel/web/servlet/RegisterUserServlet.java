package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.User;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import java.util.Enumeration;

/** 点击注册页面注册按钮后 会跳转到该资源 */
@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {

  private UserService userService = new UserServiceImpl();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {

    // TODO
    // 1.获取前段页面请求的数据
    // 2.封装到User对象
    // 3.调用service中的方法
    request.setCharacterEncoding("UTF-8");

    String check = request.getParameter("check");
    String ans = (String) request.getSession().getAttribute("CHECKCODE_SERVER");

    response.setContentType("application/json;charset=utf-8");
    response.setCharacterEncoding("UTF-8");

    PrintWriter out = response.getWriter();
    ObjectMapper mapper = new ObjectMapper();
    String msg;
    try {
      if (check.equalsIgnoreCase(ans)) { // 验证码正确
        User user = new User();
        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
          String name = e.nextElement();
          String value = request.getParameter(name);
          if (name.equals("sex")) {
              value = value.equals("male")? "男" :"女";
          }
          System.out.println("name:" + name + " ->   " + "value:" + value);
          BeanUtils.setProperty(user, name, value);
        }

        msg = userService.registerUser(user) ? "true" :"注册失败";
        out.write(mapper.writeValueAsString(msg));
        out.flush();
      } else {
        msg = "验证码错误";
        out.write(mapper.writeValueAsString(msg));
        out.flush();
      }
    } catch (Exception e) {
      e.printStackTrace();
      msg = "注册失败";
      out.write(mapper.writeValueAsString(msg));
      out.flush();
    } finally {
      out.close();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {
    this.doPost(request, response);
  }
}
