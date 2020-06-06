package com.newsms.controller;

import com.alibaba.fastjson.JSON;
import com.newsms.entity.Author;
import com.newsms.service.AuthorService;
import com.newsms.service.impl.AuthorServiceImpl;
import com.newsms.util.ObjectEmpty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmz
 */
@WebServlet("/user")
public class AuthorServlet extends HttpServlet {
    private AuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("action");
        if ("login".equals(method)) {
            login(request, response);
        } else if ("getSession".equals(method)) {
            getSession(request, response);
        } else if ("logout".equals(method)) {
            logout(request, response);
        } else {
            request.getRequestDispatcher("/404.html").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("<script>alert('请使用post方法登陆！');</script>");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        try {
            Author author = authorService.login(uname, pwd);
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<>();
            if (!ObjectEmpty.isEmpty(author)) {
                map.put("code", 0);
                map.put("msg", "登陆成功！");
                map.put("data", author);
                request.getSession().setMaxInactiveInterval(60 * 30);
                request.getSession().setAttribute("user", author);
            } else {
                map.put("code", 1);
                map.put("msg", "账号或者密码错误！");
            }
            String s = JSON.toJSONString(map);
            out.write(s);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void getSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String user = JSON.toJSONString(request.getSession().getAttribute("user"));
        out.write(user);
    }
}
