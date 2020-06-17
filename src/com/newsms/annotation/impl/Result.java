package com.newsms.annotation.impl;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lmz
 */
public class Result {
    public static void respJson(HttpServletResponse resp, Object o) {
        try {
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            String s = JSON.toJSONStringWithDateFormat(o, "yyyy-MM-dd");
            out.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void respText_Html(HttpServletResponse resp, Object o) {
        try {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write((String) o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void respPage(HttpServletResponse resp, HttpServletRequest req, Object result) throws ServletException, IOException {
        String url = (String) result;
        try {
            resp.sendRedirect(url);
        } catch (IOException e) {
            req.getRequestDispatcher("/404.html").forward(req, resp);
            e.printStackTrace();
        }
    }
}
