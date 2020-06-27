package com.huike.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginOut")
public class LoginOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getAttribute("userInfo") != null){
            request.removeAttribute("userInfo");
        }

        if(request.getSession().getAttribute("userInfo") !=null){
            request.getSession().removeAttribute("userInfo");
        }

        Cookie cookie1;
        Cookie cookie2;

        cookie1 = new Cookie("username", "");
        cookie2 = new Cookie("password", "");
        cookie1.setMaxAge(0);
        cookie2.setMaxAge(0);
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        response.sendRedirect("login.html");
    }
}
