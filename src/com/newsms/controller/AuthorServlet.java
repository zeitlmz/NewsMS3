package com.newsms.controller;

import com.newsms.annotation.GetMapping;
import com.newsms.annotation.Params;
import com.newsms.annotation.PostMapping;
import com.newsms.annotation.RequestMapping;
import com.newsms.annotation.impl.MethodType;
import com.newsms.entity.Author;
import com.newsms.entity.News;
import com.newsms.service.AuthorService;
import com.newsms.service.impl.AuthorServiceImpl;
import com.newsms.util.ObjectEmpty;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmz
 */
@RequestMapping("/user")
public class AuthorServlet{
    private AuthorService authorService = new AuthorServiceImpl();

    @PostMapping("/addNews")
    public List<News> addNews(@Params(cls = News.class) List<News> list) {
        return list;
    }

    /**
     * 注销登陆
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    /**
     * 不加注解默认是返回JSON格式数据
     */
    @PostMapping("/login")
    public Map<String, Object> login(@Params("uname") String uname, @Params("pwd") String pwd, HttpServletRequest hsr) throws SQLException {
        Author author = authorService.login(uname, pwd);
        Map<String, Object> map = new HashMap<>();
        if (!ObjectEmpty.isEmpty(author)) {
            map.put("code", 0);
            map.put("msg", "登陆成功！");
            hsr.getSession().setMaxInactiveInterval(60 * 30);
            hsr.getSession().setAttribute("user", author);
        } else {
            map.put("code", 1);
            map.put("msg", "账号或者密码错误！");
        }
        return map;
    }

    /**
     * 从session中获取用户信息
     *
     * @return 用户信息对象
     */
    @PostMapping("/getSession")
    public Author getSession(HttpServletRequest request) {
        return (Author) request.getSession().getAttribute("user");
    }
}
