package com.newsms.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author lmz
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/applicationContext");
        //获取方法名
        String name = req.getParameter("method");
        //其中method可以是可以任意取得，如getParameter("service")等
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("method parameter does not exist");
        }
        //获得当前类的Class对象
        Class c = this.getClass();
        Method method = null;
        try {
            //获得Method对象
            method = c.getMethod(name, HttpServletRequest.class, HttpServletResponse.class);
        } catch (Exception e) {
        }
        try {
            //反射调用方法
            method.invoke(this, req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}