package com.huike.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.Category;
import com.huike.travel.service.CategoryService;
import com.huike.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

  private CategoryService categoryService = new CategoryServiceImpl();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    response.setContentType("application/json;charset=utf-8");
    ObjectMapper mapper = new ObjectMapper();

    List<Category> resultSet = categoryService.findAll();

    out.write(mapper.writeValueAsString(resultSet));
    System.out.println(mapper.writeValueAsString(resultSet));
    out.flush();
    out.close();
  }
}
