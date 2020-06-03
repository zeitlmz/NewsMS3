package com.newsms.controller;

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
    private Integer currPage = 1;
    private Integer limit = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("actioon");
        if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }else if ("".equals(action)){

        }
    }

    //主页内容，新闻列表：国际，国内，娱乐
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page page = null;

        if (request.getSession().getAttribute("indexNews") == null) {
            page = selectNewsByPage(1, 10);
        }

        request.getSession().setAttribute("gn", selectNewsByTopicId(1));
        request.getSession().setAttribute("gj", selectNewsByTopicId(2));
        request.getSession().setAttribute("yl", selectNewsByTopicId(3));

        request.getSession().setAttribute("topicList", selectTopicList());
        request.getSession().setAttribute("indexNews", page);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void searchNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("searchInfo");
        searchNewsByPage(request, response);
    }

    public void searchNewsByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsTitle = request.getParameter("newsTitle");
        String newsAuthor = request.getParameter("newsAuthor");
        String content = request.getParameter("content");
        String publishDate = request.getParameter("publishDate");
        String topicId = request.getParameter("topicId");

        String currPages = request.getParameter("pageIndex");
        String limit = request.getParameter("limit");

        if (currPages != null) {
            this.currPage = Integer.parseInt(currPages);
        }
        if (limit != null) {
            this.limit = Integer.parseInt(limit);
        } else {
            this.limit = 10;
        }

        System.out.println(newsTitle + newsAuthor + content + publishDate + topicId);
        Map<String, Object> map = null;
        if (request.getSession().getAttribute("searchInfo") == null) {
            map = new HashMap<>();
        } else {
            map = (Map<String, Object>) request.getSession().getAttribute("searchInfo");
        }

        if (ObjectEmpty.isNotEmpty(newsTitle)) {
            map.put("newsTitle", newsTitle);
        }
        if (ObjectEmpty.isNotEmpty(newsAuthor)) {
            map.put("newsAuthor", newsAuthor);
        }
        if (ObjectEmpty.isNotEmpty(content)) {
            map.put("content", content);
        }
        if (ObjectEmpty.isNotEmpty(publishDate)) {
            map.put("publishDate", publishDate);
        }
        if (ObjectEmpty.isNotEmpty(topicId)) {
            map.put("topicId", topicId);
        }

        map.put("limit", this.limit);
        map.put("page", this.currPage);
        request.getSession().setAttribute("searchInfo", map);
        Page pages = newsService.searchNews(map);
        request.getSession().setAttribute("indexNews", pages);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


    public List<Topic> selectTopicList() {
        return topicService.selectTopicList();
    }

    public Page selectNewsByPage(Integer currPage, Integer limit) {
        Gson gson = new Gson();
        Page page = newsService.selectNewsByPage(currPage, limit);
        return page;
    }

    public void newsRead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsId = Integer.parseInt(request.getParameter("newsid"));
        Gson gson = new Gson();
        News news = newsService.selectNewsByNewsId(newsId);
        request.getSession().setAttribute("newsread", news);
        request.getRequestDispatcher("news_read.jsp").forward(request, response);
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
}
