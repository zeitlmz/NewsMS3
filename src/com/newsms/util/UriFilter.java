package com.newsms.util;

import com.newsms.entity.Router;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = "/*")
public class UriFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将请求对转换HTTP
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取当前请求的ur1路径
        String url = req.getRequestURI();
        System.out.println(url);
        //定义正则匹配过滤指定包含.html和.jsp的url
        Pattern pattern = Pattern.compile("\\w.html|.html|\\w.jsp|.jsp");
        Matcher m = pattern.matcher(url);
        if (m.find()) {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write("<script>alert(\"非法请求！\");</script>");
        } else {
            String[] urls = url.split(req.getContextPath() + "/");
            String action = null;
            if (urls.length > 1) {
                action = urls[1];
            } else {
                action = "index";
            }
            Router router = ReadXmlAndJsonFileToObj.selectXMLNode("classpath:Router.xml", action, Router.class);
        }
    }
}
