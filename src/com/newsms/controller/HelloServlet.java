package com.newsms.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmz
 */
@WebServlet(name = "HelloServlet")
public class HelloServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("初始化servlet对象");
        String myXml=getInitParameter("myXml");
        System.out.println("读取"+myXml);
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理请求......."+req.getServerName());
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("释放servlet资源。。。");
        super.destroy();
    }
}
