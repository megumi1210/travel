package com.huike.travel.web.servlet;

import com.huike.travel.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/toRouteList")
public class ToRouteServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String rname = request.getParameter("rname");

    StringBuilder location = new StringBuilder("route_list.html?rname=");

    if(rname!=null) location.append(rname);




    String path = request.getQueryString();
    String cid = WebUtils.getPathVariable(path,"cid");
    if(cid != null && !cid.equals("")){
        location.append("&cid=").append(cid);
    }

    response.sendRedirect(location.toString());
  }
}
