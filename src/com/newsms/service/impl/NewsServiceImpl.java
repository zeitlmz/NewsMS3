package com.newsms.service.impl;

import com.newsms.dao.NewsDao;
import com.newsms.dao.impl.NewsDaoImpl;
import com.newsms.entity.Comments;
import com.newsms.entity.News;
import com.newsms.entity.Page;
import com.newsms.service.NewsService;
import com.newsms.util.TM;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (News)表服务实现类
 *
 * @author lmz
 * @since 2020-04-10 10:26:33
 */
public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao;

    @Override
    public Page selectNewsByPage(Integer currPage, Integer limit) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        Page pages = new Page();
        pages.setLimit(limit);
        pages.setCount(getNewsCount());
        if (currPage > pages.getCount()) {
            currPage = pages.getCount();
        }
        pages.setPage(currPage);
        List<News> news = newsDao.selectNewsByPage((currPage - 1) * limit, limit);
        pages.setData(news);
        return pages;
    }

    private Integer getNewsCount() throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        return newsDao.selectCount();
    }

    @Override
    public News selectNewsByNewsId(Integer newsId) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        return newsDao.selectNewsByNewsId(newsId);
    }

    @Override
    public List<Comments> selectCommByNewsId(Integer newsId) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        return newsDao.selectNewsCommByNewsId(newsId);
    }

    @Override
    public List<News> selectNewsByTopicId(Integer topicId) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        return newsDao.selectNewsByTopicId(topicId);
    }

    /**
     * 通过分页信息和作者查询新闻
     *
     * @param currPage   当前页
     * @param limit      每页数据条数
     * @param newsAuthor 作者
     * @return 新闻列表
     */
    @Override
    public Page selectNewsByRealName(Integer currPage, Integer limit, String newsAuthor) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        Page pages = new Page();
        pages.setLimit(limit);
        pages.setCount(newsDao.selectCountByNewsAuthor(newsAuthor));
        if (currPage > pages.getCount()) {
            currPage = pages.getCount();
        }
        pages.setPage(currPage);
        List<News> list = null;
        list = newsDao.selectNewsByRealName((currPage - 1) * limit, limit, newsAuthor);
        pages.setData(list);
        return pages;
    }

    @Override
    public Page searchNews(Map<String, Object> map) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        Page pages = new Page();
        pages.setLimit((Integer) map.get("limit"));
        Map<String, Object> map1 = new HashMap<>();
        for (String key : map.keySet()) {
            map1.put(key, map.get(key));
        }
        map1.remove("page");
        map1.remove("limit");
        Integer count = null;
        count = newsDao.selectCountBySearch(map1);
        pages.setCount(count);
        if ((Integer) map.get("page") > pages.getCount() & pages.getCount() > 0) {
            map.put("page", pages.getCount());
        } else if ((Integer) map.get("page") < 1) {
            map.put("page", 1);
        }
        pages.setPage((Integer) map.get("page"));
        map.put("page", ((Integer) map.get("page") - 1) * (Integer) map.get("limit"));
        List<News> list = null;
        list = newsDao.selectNewsBySearch(map);
        pages.setData(list);
        return pages;
    }

    @Override
    public boolean updateNews(News news) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        return newsDao.updateNews(news) != 0;
    }

    @Override
    public boolean addNews(News news) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        return newsDao.addNews(news) > 0;
    }

    @Override
    public Boolean addComm(Comments comments) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        return newsDao.addComm(comments) > 0;
    }

    @Override
    public boolean delNewsByNewsId(Integer newsId) throws SQLException {
        newsDao = new NewsDaoImpl(TM.getConn());
        return newsDao.delNewsByNewsId(newsId) > 0;
    }
}
