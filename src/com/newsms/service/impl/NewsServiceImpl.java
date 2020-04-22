package com.newsms.service.impl;

import java.util.HashMap;

import com.newsms.dao.NewsDao;
import com.newsms.dao.impl.NewsDaoImpl;
import com.newsms.entity.News;
import com.newsms.entity.Page;
import com.newsms.service.NewsService;

import java.util.List;
import java.util.Map;

/**
 * (News)表服务实现类
 *
 * @author lmz
 * @since 2020-04-10 10:26:33
 */
public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao = new NewsDaoImpl();

    @Override
    public Page selectNewsByPage(Integer currPage, Integer limit) {
        Page pages = new Page();
        pages.setLimit(limit);
        pages.setCount(newsDao.selectCount());
        if (currPage > pages.getCount()) {
            currPage = pages.getCount();
        }
        pages.setPage(currPage);
        List<News> news = newsDao.selectNewsByPage((currPage - 1) * limit, limit);
        pages.setData(news);
        return pages;
    }

    @Override
    public News selectNewsByNewsId(Integer newsId) {
        return newsDao.selectNewsByNewsId(newsId);
    }

    @Override
    public List<News> selectNewsByTopicId(Integer topicId) {
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
    public Page selectNewsByRealName(Integer currPage, Integer limit, String newsAuthor) {
        Page pages = new Page();
        pages.setLimit(limit);
        pages.setCount(newsDao.selectCountByNewsAuthor(newsAuthor));
        if (currPage > pages.getCount()) {
            currPage = pages.getCount();
        }
        pages.setPage(currPage);
        List<News> list = newsDao.selectNewsByRealName((currPage - 1) * limit, limit, newsAuthor);
        pages.setData(list);
        return pages;
    }

    @Override
    public Page searchNews(Map<String, Object> map) {
        System.out.println("前端传入的当前页->" + map.get("page"));
        Page pages = new Page();
        pages.setLimit((Integer) map.get("limit"));
        Map<String, Object> map1 = new HashMap<>();
        for (String key : map.keySet()) {
            map1.put(key, map.get(key));
        }
        map1.remove("page");
        map1.remove("limit");
        Integer count = newsDao.selectCountBySearch(map1);
        System.out.println("查出的数据总数-》" + count);
        pages.setCount(count);
        if ((Integer) map.get("page") > pages.getCount() & pages.getCount() > 0) {
            map.put("page", pages.getCount());
        } else if ((Integer) map.get("page") < 1) {
            map.put("page", 1);
        }
        pages.setPage((Integer) map.get("page"));
        map.put("page", ((Integer) map.get("page") - 1) * (Integer) map.get("limit"));
        System.out.println("业务层计算的-》->" + map.get("page"));
        List<News> list = newsDao.selectNewsBySearch(map);
        pages.setData(list);
        return pages;
    }

    @Override
    public boolean updateNews(News news) {
        return newsDao.updateNews(news) > 0;
    }

    @Override
    public boolean addNews(News news) {
        return newsDao.addNews(news) > 0;
    }

    @Override
    public boolean delNewsByNewsId(Integer newsId) {
        return newsDao.delNewsByNewsId(newsId) > 0;
    }
}