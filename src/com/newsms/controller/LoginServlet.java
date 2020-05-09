package com.newsms.controller;

import com.google.gson.Gson;
import com.newsms.entity.Author;
import com.newsms.service.AuthorService;
import com.newsms.service.impl.AuthorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author lmz
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private AuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println("username:"+username);
        System.out.println("pwd:"+pwd);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/applicationContext");
        try {
            Author author = authorService.login(username, pwd);
            PrintWriter out = response.getWriter();
            if (author != null) {
                Gson gson = new Gson();
                String author2 = gson.toJson(author);
                request.getSession().setAttribute("author",author2);
                response.sendRedirect("index.jsp");
            }else {
                out.write("登陆失败，请检查账号和密码！<br><h2><a href='index.jsp'>点击返回主页</a></h2>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
