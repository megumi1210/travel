package com.huike.travel.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huike.travel.domain.RestfulResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebUtils {

  // 获取请求地址中的参数 ?action = value
  // 前端必须 encodeUrl 否则获取中文可能会乱码
  public static String getPathVariable(String path, String name) {

    if (name == null || name.equals("")) {
      return null;
    }
    Pattern pattern = Pattern.compile("(^|&)" + name + "=([^&]*)(&|$)");

    String value = null;
    if (path != null && !path.equals("")) {
      Matcher m = pattern.matcher(path);
      if (m.find()) {
        try {
          value = URLDecoder.decode(m.group(2), "UTF-8");
        } catch (Exception e) {
          System.err.println("解析URL参数失败...");
        }
      }
    }
    return value;
  }


  //获取 response代理类
  public static RestfulResponse getRestfulResponse(HttpServletResponse response) throws IOException {
    return new RestfulResponse(response);
  }


}
