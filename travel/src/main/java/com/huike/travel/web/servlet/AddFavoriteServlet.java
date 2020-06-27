package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.*;
import com.huike.travel.service.FavoriteService;
import com.huike.travel.service.RouteService;
import com.huike.travel.service.UserService;
import com.huike.travel.service.impl.FavoriteServiceImpl;
import com.huike.travel.service.impl.RouteServiceImpl;
import com.huike.travel.service.impl.UserServiceImpl;
import com.huike.travel.util.WebUtils;
import jdk.internal.module.ModuleLoaderMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addFavorite")
public class AddFavoriteServlet extends HttpServlet {

    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private RouteService routeService = new RouteServiceImpl();
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RestfulResponse restfulResponse = WebUtils.getRestfulResponse(response);
        int uid =-1;

        String rid =request.getParameter("rid");
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if(userInfo == null) userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        ResultInfo resultInfo = null;


        if(userInfo != null){
            uid = userInfo.getUid();
        }

        if(rid !=null && uid !=-1){
            try{
                 int id = Integer.parseInt(rid);
                 boolean active = favoriteService.insertRecord(id,uid);
                 int count =-1;
                 Route route = routeService.findRouteByRid(id);
                 if(route != null){
                     count = route.getCount();
                     count = active ? count+1 : count;
                     //断言未收藏过
                     if(active){
                         routeService.updateCount(count,id); //更新收藏数
                     }

                     resultInfo =new ResultInfo(count,active);

                 }
            }catch (Exception e){
                e.printStackTrace();
            }

      // 未登录 null 一直显示添加收藏
            restfulResponse.writeOnce(resultInfo);

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
