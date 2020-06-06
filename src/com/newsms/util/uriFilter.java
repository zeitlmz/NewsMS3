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
public class uriFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //转换为http请求
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取当前请求的ur1路径
        String url = req.getRequestURI();
        //定义正则匹配过滤指定包含.html和.jsp的url
        Pattern pattern = Pattern.compile("\\w.html|.html|\\w.jsp|.jsp");
        Matcher m = pattern.matcher(url);
        //如果匹配成功，说明是非法请求，直接转发到404页面
        if (m.find()) {
            req.getRequestDispatcher("/404.html").forward(req, resp);
        } else {
            //否则就分割字符串
            String[] urls = url.split("/");
            String name = null;
            //这里只截取项目名后一位的字符
            if (urls.length > 2) {
                name = urls[2];
            } else {
                // 如果数什么情况度不是大于1就表示是第一次打开主页，只有项目名后面没有其他的，就将name赋值index
                name = "index";
            }
            //使用name做为查询条件去查询名name的路由
            Router router = ReadXmlAndJsonFileToObj.selectXMLNode("classpath:Router.xml", name, Router.class);
            //判断路由信息对象是否为空，如果为空
            if (ObjectEmpty.isEmpty(router)) {
                req.getRequestDispatcher("/404.html").forward(req, resp);
                //如果请求类型为空就是页面跳转请求
            } else if (router.getReqType() == null) {
                if (urls.length > 3 | req.getParameter("action") != null) {
                    req.getRequestDispatcher("/404.html").forward(req, resp);
                } else {
                    if (router.getHasFilter()) {
                        //需要经过权限验证
                        if (req.getSession().getAttribute("user") != null) {
                            req.getRequestDispatcher(router.getPagePath()).forward(req, resp);
                        } else {
                            resp.sendRedirect(req.getContextPath());
                        }
                    } else {
                        //不需要经过权限验证
                        req.getRequestDispatcher(router.getPagePath()).forward(req, resp);
                    }
                }
                //reqType为ajax是ajax请求
            } else if ("ajax".equals(router.getReqType())) {
                if (req.getSession().getAttribute("user") != null
                        | req.getParameter("action").equals("newsPage")
                        | req.getParameter("action").equals("login")
                        | req.getParameter("action").equals("newsRead")
                        | req.getParameter("action").equals("searchNews")
                        | req.getParameter("action").equals("sideNewsList")
                        | req.getParameter("action").equals("newsPage")
                        | req.getParameter("action").equals("topics")
                        | req.getParameter("action").equals("getSession")) {
                    if (urls.length > 3) {
                        req.getRequestDispatcher("/404.html").forward(req, resp);
                    } else {
//                        System.out.println("已放行ajax请求->servlet名：" + name + "\t请求执行方法名：" + req.getParameter("action"));
                        filterChain.doFilter(req, resp);
                    }
                } else {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("<script>alert('登陆态失效,请登录后在试！')</script>");
                }
                //reqType为static是静态资源请求
            } else if ("static".equals(router.getReqType())) {
                filterChain.doFilter(req, resp);
            }
        }
    }
}
