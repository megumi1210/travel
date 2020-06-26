package com.huike.travel.web.filter;

import com.huike.travel.domain.User;
import com.huike.travel.domain.UserInfo;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

  private UserService userService = new UserServiceImpl();

  private static final String[] intercept_path = new String[] { "/toMyFavor","/addFavorite"};

  public void destroy() {}

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;

    String path = request.getRequestURI();

    boolean release = true;

    for (String s : intercept_path) { // 判断是否有拦截的路径
      if (path.contains(s)) {
        release = false;
        break;
      }
    }


    if (release) {
        chain.doFilter(request, response);
        return;
    }
      System.out.println("开始拦截登录...");

    Cookie[] cookies = request.getCookies();
    String username = null;
    String password = null;

    if (cookies == null || cookies.length == 0){
        response.sendRedirect("toLogin");
        return;
    }


    //获取 cookie值
    for (Cookie c : cookies) {
      if (c.getName().equals("username")) {
        username = c.getValue();
      }

      if (c.getName().equals("password")) {
        password = c.getValue();
      }
    }

    if (username != null && password != null) {
      if (userService.login(username, password)) { // 如果验证登录成功放行
        //将查询到的信息传递
        User user = userService.findUserInfoBy(username);
        UserInfo userInfo = new UserInfo(username,user.getUid());
        request.setAttribute("userInfo",userInfo);

        chain.doFilter(request, response);
      } else {
        response.sendRedirect("toLogin");
      }
    } else { // 登录校验失败
      response.sendRedirect("toLogin");
    }

  }

  public void init(FilterConfig config) throws ServletException {
    System.out.println("登录拦截初始化...");
  }
}
