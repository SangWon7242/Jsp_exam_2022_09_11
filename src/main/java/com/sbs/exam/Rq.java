package com.sbs.exam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Rq {

  private HttpServletRequest req;
  private HttpServletResponse resp;
  private boolean isInvalid = false;
  private String controllerName;
  private String actionMethodName;

  public Rq(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;

    try {
      req.setCharacterEncoding("UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset=utf-8");

    String requestUri = req.getRequestURI();
    String[] requestUriBits = requestUri.split("/");

    if ( requestUriBits.length < 4 ) {
      isInvalid = true;
      return;
    }

    this.controllerName = requestUriBits[2];
    this.actionMethodName = requestUriBits[3];
  }

  public HttpServletRequest getReq() {
    return req;
  }

  public boolean isInvalid() {
    return isInvalid;
  }

  public String getControllerName() {
    return controllerName;
  }

  public String getActionMethodName() {
    return actionMethodName;
  }


  public int getIntParam(String paramName, int defaultValue) {
    String value = req.getParameter(paramName);

    if (value == null) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(value);
    }
    catch ( NumberFormatException e ) {
      return defaultValue;
    }
  }

  public void appendBody(String str) {
    try {
      resp.getWriter().append(str);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
