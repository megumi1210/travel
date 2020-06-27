package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.Category;
import com.huike.travel.domain.RestfulResponse;
import com.huike.travel.service.CategoryService;
import com.huike.travel.service.impl.CategoryServiceImpl;
import com.huike.travel.util.JedisUtil;
import com.huike.travel.util.WebUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

  private Jedis jedis = JedisUtil.getJedis();

  private CategoryService categoryService = new CategoryServiceImpl();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    RestfulResponse restfulResponse = WebUtils.getRestfulResponse(response);
    //使用redis 缓存目录数据
    String value = jedis.get("category");
    if(value != null){
      restfulResponse.writeStringOnce(value);
    }else {
      List<Category> resultSet = categoryService.findAll();
      jedis.set("category",restfulResponse.getMapper().writeValueAsString(resultSet));
      restfulResponse.writeOnce(resultSet);
    }



  }
}
