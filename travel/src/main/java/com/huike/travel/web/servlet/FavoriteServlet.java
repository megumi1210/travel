package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.PageInfo;
import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.RestfulResponse;
import com.huike.travel.domain.Route;
import com.huike.travel.service.FavoriteService;
import com.huike.travel.service.impl.FavoriteServiceImpl;
import com.huike.travel.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {

    private FavoriteService favoriteService = new FavoriteServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RestfulResponse restfulResponse = WebUtils.getRestfulResponse(response);

          String uid = request.getParameter("uid");
          String pageNum = request.getParameter("start");
          String pageSize = request.getParameter("end");

         PageInfo<Route> result = null;
          if( uid != null){

              PageParam pageParam = new PageParam(1,12);
              if(pageNum !=null){
                  pageParam.setPageNum(Integer.parseInt(pageNum));
              }
              if(pageSize!=null){
                  pageParam.setPageSize(Integer.parseInt(pageSize));
              }



              try{
                   int  id = Integer.parseInt(uid);
                   result = favoriteService.findFavoriteRoutesByPage(id,pageParam);

              }catch (Exception e){
                 e.printStackTrace();
              }
          }
          restfulResponse.writeOnce(result);
    }
}
