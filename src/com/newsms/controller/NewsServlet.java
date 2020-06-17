package com.newsms.controller;

import com.newsms.annotation.*;
import com.newsms.entity.Comments;
import com.newsms.entity.News;
import com.newsms.entity.Page;
import com.newsms.entity.Topic;
import com.newsms.service.NewsService;
import com.newsms.service.TopicService;
import com.newsms.service.impl.NewsServiceImpl;
import com.newsms.service.impl.TopicServiceImpl;
import com.newsms.util.ObjectEmpty;
import com.newsms.util.TransactionManger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmz
 */
@RequestMapping("/news")
public class NewsServlet {
    private NewsService newsService = (NewsService) TransactionManger.getInstance(new NewsServiceImpl());
    private TopicService topicService = (TopicService) TransactionManger.getInstance(new TopicServiceImpl());

    /**
     * 侧边栏新闻列表：国际，国内，娱乐
     *
     * @return 侧边栏新闻列表
     */
    @GetMapping("/sideNewsList")
    public Map<String, Object> sideNewsList() throws SQLException {
        Page pages = selectNewsByPage(1, 10);
        Map<String, Object> map = new HashMap<>();
        map.put("gn", selectNewsByTopicId(1));
        map.put("gj", selectNewsByTopicId(2));
        map.put("yl", selectNewsByTopicId(3));
        return map;
    }

    /**
     * 搜索新闻
     *
     * @param news     搜索条件
     * @param currPage 当前页
     * @return 分页对象，包含新闻
     */
    @GetMapping("/searchNews")
    public Page searchNews(@Params(classType = "Entity") News news, @Params("currPage") Integer currPage, HttpServletRequest request) throws ServletException, IOException, SQLException {
        request.getSession().removeAttribute("searchInfo");
        return newsPage(news, currPage, request);
    }

    /**
     * @param news     查询条件
     * @param currPage 当前页
     * @return 分页对象，包含了news对象
     */
    @GetMapping("/newsPage")
    public Page newsPage(@Params(classType = "Entity") News news, @Params("currPage") Integer currPage, HttpServletRequest request) throws SQLException {
        if (currPage == null) {
            currPage = 1;
        }
        Integer limit = 10;
        Map<String, Object> map = null;
        if (request.getSession().getAttribute("searchInfo") == null) {
            map = new HashMap<>();
        } else {
            map = (Map<String, Object>) request.getSession().getAttribute("searchInfo");
        }
        String newsTitle = news.getNewstitle();
        String newsAuthor = news.getNewsauthor();
        String content = news.getContent();
        Date publishDate = news.getPublishdate();
        Integer topicId = news.getTopicId();

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
        map.put("page", currPage);
        request.getSession().setAttribute("searchInfo", map);
        return newsService.searchNews(map);
    }

    /**
     * @return 话题列表
     */
    public List<Topic> selectTopicList() throws SQLException {
        return topicService.selectTopicList();
    }

    /**
     * 分页查询所有的新闻
     */
    public Page selectNewsByPage(Integer currPage, Integer limit) throws SQLException {
        return newsService.selectNewsByPage(currPage, limit);
    }

    /**
     * @return News对象
     */
    @GetMapping("/newsRead")
    public News newsRead(@Params("newsid") Integer newsid) throws SQLException {
        return newsService.selectNewsByNewsId(newsid);
    }

    /**
     * @return 评论列表
     */
    @GetMapping("/newscomments")
    public List<Comments> newscomments(@Params("newsid") Integer newsid) throws SQLException {
        return newsService.selectCommByNewsId(newsid);
    }

    /**
     * @return 添加评论是否成功
     */
    @PostMapping("/commns")
    public Boolean commns(@Params(classType = "Entity") Comments comments) throws ServletException, IOException, SQLException {
        return newsService.addComm(comments);
    }

    /**
     * @return 读取新闻和评论列表
     */
    @GetMapping("/adnewsRead")
    public Map<String, Object> adnewsRead(@Params("newsid") Integer newsid) throws SQLException {
        News news = newsService.selectNewsByNewsId(newsid);
        if (!ObjectEmpty.isEmpty(news)) {
            Map<String, Object> map = new HashMap<>();
            List<Topic> topics = selectTopicList();
            map.put("news", news);
            map.put("topics", topics);
            return map;
        }
        return null;
    }

    public List<News> selectNewsByTopicId(Integer topicId) throws SQLException {
        return newsService.selectNewsByTopicId(topicId);
    }

    /**
     * @return 添加修改新闻是否成功
     */
    @PostMapping("/newsmodify")
    public boolean newsmodify(@Params(classType = "Entity") News news) throws SQLException {
        return newsService.updateNews(news);
    }

    /**
     * @return 添加新闻是否成功
     */
    @PostMapping("/newsadd")
    public boolean newsadd(@Params(classType = "Entity") News news) throws SQLException {
        return newsService.addNews(news);
    }

    /**
     * @return 删除新闻是否成功
     */
    @GetMapping("/delete")
    public boolean delete(@Params("newsid") Integer newsid) throws SQLException {
        if (newsid != null) {
            return newsService.delNewsByNewsId(newsid);
        }
        return false;
    }
}
