package com.huike.travel.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//代理 response
public class RestfulResponse {

    private static final String CONTENT_TYPE = "application/json;charset=utf-8";
    private ObjectMapper mapper = null;
    private PrintWriter out = null;

    public RestfulResponse(HttpServletResponse response) throws IOException {
        mapper = new ObjectMapper();
        response.setContentType(CONTENT_TYPE);
        out = response.getWriter();
    }

    public void write(Object object) throws IOException {
        out.write(mapper.writeValueAsString(object));
    }

    public void flushAndClose() {
        out.flush();
        out.close();
    }

    public  void writeOnce(Object object) throws IOException{
        out.write(mapper.writeValueAsString(object));
        out.flush();
        out.close();
    }
}
