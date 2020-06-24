package com.huike.travel.web.servlet;

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
    if (rname == null) location.append("");
    else  location.append(rname);
    String pathParameter = request.getQueryString();
    String cid = "";
    if (pathParameter != null) {
      int start = pathParameter.indexOf("cid");
      int end = pathParameter.indexOf("&", start);
      if (start != -1) {
        cid = end != -1 ? pathParameter.substring(start, end) : pathParameter.substring(start);
      }
    }
    if(!cid.equals("")){
        location.append("&").append(cid);
    }

    response.sendRedirect(location.toString());
  }
}
