package com.newsms.controller;

import com.google.gson.Gson;
import com.newsms.entity.Author;
import com.newsms.service.AuthorService;
import com.newsms.service.impl.AuthorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author lmz
 */
public class authorServlet extends HttpServlet {
    private AuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token= UUID.randomUUID().toString();
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        try {
            Author author = authorService.login(username, pwd);
            PrintWriter out = response.getWriter();
            if (author != null) {
                Gson gson = new Gson();
                String author2 = gson.toJson(author);
                request.getSession().setAttribute("author",author);
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("<script>alert('请使用post方法登陆！');</script>");
    }
}
