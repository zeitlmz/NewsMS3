package com.newsms.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.newsms.entity.News;
import com.newsms.entity.Page;
import com.newsms.entity.Topic;
import com.newsms.service.NewsService;
import com.newsms.service.TopicService;
import com.newsms.service.impl.NewsServiceImpl;
import com.newsms.service.impl.TopicServiceImpl;
import com.newsms.util.ObjectEmpty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmz
 */
@WebServlet("/news")
public class NewsServlet extends HttpServlet {
    private NewsService newsService = new NewsServiceImpl();
    private TopicService topicService = new TopicServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("action");
        if ("newsRead".equals(method)) {
            newsRead(req, resp);
        } else if ("sideNewsList".equals(method)) {
            sideNewsList(req, resp);
        } else if ("newsadd".equals(method)) {
            newsAdd(req, resp);
        } else if ("adnewsRead".equals(method)) {
            adnewsRead(req, resp);
        } else if ("searchNews".equals(method)) {
            searchNews(req, resp);
        } else if ("newsPage".equals(method)) {
            newsByPage(req, resp);
        } else if ("delete".equals(method)) {
            delNewsByNewsId(req, resp);
        } else if ("newsmodify".equals(method)) {
            updateNews(req, resp);
        } else {
            req.getRequestDispatcher("/404.html").forward(req, resp);
        }
    }

    //侧边栏新闻列表：国际，国内，娱乐
    public void sideNewsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        Page pages = selectNewsByPage(1, 10);
        Map<String, Object> map = new HashMap<>();
        map.put("gn", selectNewsByTopicId(1));
        map.put("gj", selectNewsByTopicId(2));
        map.put("yl", selectNewsByTopicId(3));
        String data = JSON.toJSONString(map);
        PrintWriter out = response.getWriter();
        out.write(data);
    }


    public void searchNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("searchInfo");
        newsByPage(request, response);
    }

    public void newsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String newsTitle = request.getParameter("newsTitle");
        String newsAuthor = request.getParameter("newsAuthor");
        String content = request.getParameter("content");
        String publishDate = request.getParameter("publishDate");
        String topicId = request.getParameter("topicId");

        String currPages = request.getParameter("currPage");
        if (currPages == null) {
            currPages = "1";
        }
        Integer pageIndex = Integer.parseInt(currPages);
        Integer limit = 10;

        Map<String, Object> map = null;
        if (request.getSession().getAttribute("searchInfo") == null) {
            map = new HashMap<>();
        } else {
            map = (Map<String, Object>) request.getSession().getAttribute("searchInfo");
        }

        if (!ObjectEmpty.isEmpty(newsTitle)) {
            map.put("newsTitle", newsTitle);
        }
        if (!ObjectEmpty.isEmpty(newsAuthor)) {
            map.put("newsAuthor", newsAuthor);
        }
        if (!ObjectEmpty.isEmpty(content)) {
            map.put("content", content);
        }
        if (!ObjectEmpty.isEmpty(publishDate)) {
            map.put("publishDate", publishDate);
        }
        if (!ObjectEmpty.isEmpty(topicId)) {
            map.put("topicId", topicId);
        }

        map.put("limit", limit);
        map.put("page", pageIndex);
        request.getSession().setAttribute("searchInfo", map);
        Page pages = newsService.searchNews(map);
        PrintWriter out = response.getWriter();
        String s = JSON.toJSONString(pages);
        out.write(s);
    }


    public List<Topic> selectTopicList() {
        return topicService.selectTopicList();
    }

    public Page selectNewsByPage(Integer currPage, Integer limit) {
        return newsService.selectNewsByPage(currPage, limit);
    }

    public void newsRead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        int newsId = Integer.parseInt(request.getParameter("newsid"));
        News news = newsService.selectNewsByNewsId(newsId);
        PrintWriter out = response.getWriter();
        String s = JSON.toJSONString(news);
        out.write(s);
    }

    public void adnewsRead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        int newsId = Integer.parseInt(request.getParameter("newsid"));
        System.out.println("读取新闻id：" + newsId);
        News news = newsService.selectNewsByNewsId(newsId);
        if (!ObjectEmpty.isEmpty(news)) {
            PrintWriter out = response.getWriter();
            Map<String, Object> map = new HashMap<>();
            List<Topic> topics = selectTopicList();
            map.put("news", news);
            map.put("topics", topics);
            String s = JSON.toJSONString(map);
            out.write(s);
        }
    }

    public List<News> selectNewsByTopicId(Integer topicId) {
        return newsService.selectNewsByTopicId(topicId);
    }

    public void selectNewsByRealName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currPage = Integer.parseInt(request.getParameter("currPage"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        String newsAuthor = request.getParameter("newsAuthor");
        Page page = newsService.selectNewsByRealName(currPage, limit, newsAuthor);
        Gson gson = new Gson();
        String a = gson.toJson(page);
        Writer out = response.getWriter();
        out.write(a);
    }

    public void updateNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String s1 = JSON.toJSONString(parameterMap);
        String s2 = s1.replace("[", "");
        String s3 = s2.replace("]", "");
        News news = JSON.parseObject(s3, News.class);
        boolean b = newsService.updateNews(news);
        String s = JSON.toJSONString(b);
        out.write(s);
    }

    public void newsAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String s1 = JSON.toJSONString(parameterMap);
        String s2 = s1.replace("[", "");
        String s3 = s2.replace("]", "");
        News news = JSON.parseObject(s3, News.class);
        boolean b = newsService.addNews(news);
        String s = JSON.toJSONString(b);
        out.write(s);
    }

    public void delNewsByNewsId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("newsid");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (str != null) {
            boolean b = newsService.delNewsByNewsId(Integer.parseInt(str));
            String s = JSON.toJSONString(b);
            out.write(s);
        } else {
            out.write("false");
        }
    }
}
