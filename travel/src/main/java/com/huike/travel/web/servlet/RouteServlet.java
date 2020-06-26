package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.*;
import com.huike.travel.service.RouteService;
import com.huike.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.regex.Pattern;

@WebServlet(value = {"/search"})
public class RouteServlet extends HttpServlet {

  private RouteService routeService = new RouteServiceImpl();

  //0或者非0开头的数字或者 非负浮点数
  private static final String[] pattern = {"^(0|[1-9][0-9]*)$","^\\d+(\\.\\d+)?$"};

  private  boolean  validNum(String input){
     return  Pattern.matches(pattern[0],input) || Pattern.matches(pattern[1],input);
  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json;charset=utf-8");
    ObjectMapper mapper = new ObjectMapper();
    PrintWriter out = response.getWriter();

    String cid =request.getParameter("cid");
    String rname = request.getParameter("rname");
    String pageNum = request.getParameter("pageNum");
    String pageSize = request.getParameter("pageSize");
    String start = request.getParameter("start");
    String end = request.getParameter("end");

    System.out.println("rname --->"+rname);
    System.out.println("pageNum --->"+pageNum);
    System.out.println("pageSize --->"+pageSize);
    System.out.println("start --->"+start);
    System.out.println("end --->" +end);

    Route route = new Route();
    if(rname != null){
      rname = URLDecoder.decode(rname, "UTF-8");
    }else {
      rname="";
    }
    route.setRname(rname);

    if(cid != null && !cid.equals("")){
       route.setCid(Integer.parseInt(cid));
    }
    PriceParam priceParam = new PriceParam();

    if (start != null && validNum(start)) {
      priceParam.setStart(Double.parseDouble(start));
    }
    if (end != null && validNum(end)) {
      priceParam.setEnd(Double.parseDouble(end));
    }
    System.out.println(priceParam);
    PageParam pageParam = new PageParam();
    if(pageNum !=null){
      pageParam.setPageNum(Integer.parseInt(pageNum));
    }
    if(pageSize!=null){
      pageParam.setPageSize(Integer.parseInt(pageSize));
    }
    pageParam.setUseOrderBy(true);
    pageParam.setOrderByName("count");
    pageParam.setOrder(Order.DESC);
    PageInfo<Route> pageInfo = routeService.findRoutByPage(route, priceParam, pageParam);
    System.out.println(pageInfo);
    System.err.println(pageInfo.getList());

    out.write(mapper.writeValueAsString(pageInfo));
    out.flush();
    out.close();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}
