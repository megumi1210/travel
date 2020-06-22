package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerValid")
public class RegisterValidServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        if(username == null){
            out.write(mapper.writeValueAsString(Boolean.TRUE));
        }else {
            Boolean msg = userService.validUsername(username);
            out.write(mapper.writeValueAsString(msg));
        }
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
