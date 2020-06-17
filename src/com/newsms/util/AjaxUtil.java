package com.newsms.util;

import com.newsms.annotation.impl.AccessUrlMapper;
import com.newsms.entity.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 指定哪些ajax请求访问权限管理
 *
 * @author lmz
 */
public class AjaxUtil {
    public static void Go(HttpServletRequest req, HttpServletResponse resp, String[] urls, Router router) throws IOException, ServletException {
        String[] ajaxUrl = urls[1].split("/");
        String oneAction = ajaxUrl[0];
        String twoAction = ajaxUrl[1];
//        System.out.println("一级目录：" + oneAction);
//        System.out.println("二级目录：" + twoAction);
        if (req.getSession().getAttribute("user") != null
                | "newsPage".equals(twoAction)
                | "addNews".equals(twoAction)
                | "newsRead".equals(twoAction)
                | "searchNews".equals(twoAction)
                | "login".equals(twoAction)
                | "sideNewsList".equals(twoAction)
                | "topics".equals(twoAction)
                | "getSession".equals(twoAction)
                | "newscomments".equals(twoAction)
                | "commns".equals(twoAction)) {
            //调用一二级访问路径映射器
            AccessUrlMapper.Go(req, resp, oneAction, twoAction, router.getController());
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write("<script>alert('登陆态失效,请登录后在试！')</script>");
        }
    }
}
