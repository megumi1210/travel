package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.RestfulResponse;
import com.huike.travel.domain.User;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.UserServiceImpl;
import com.huike.travel.util.MailUtils;
import com.huike.travel.util.UuidUtil;
import com.huike.travel.util.WebUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet("/active")
public class ActiveUserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              String path  =request.getQueryString();
              String username = null;
              String email = null;
              int uid =-1;

              username= WebUtils.getPathVariable(path,"username");
              RestfulResponse restfulResponse = WebUtils.getRestfulResponse(response);

              if(username != null){
                  User user = userService.findUserInfoBy(username);
                  if(user != null){
                     email = user.getEmail();
                     uid = user.getUid();
                  }
              }
              if(email != null && uid !=-1){
                  String code = UuidUtil.getUuid();
                  String url = "http://localhost:9090/travel/updateUser?code="+code+"&uid="+uid;
                  String text = "前往激活<a href=\""+url+ "\">"+ url +"</a>";
                  String finalEmail = email;
                  threadPool.submit(()->{
                      MailUtils.sendMail(finalEmail,text,"激活账号");
                  });
              }

            restfulResponse.writeOnce("邮件发送成功");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
    }
}
