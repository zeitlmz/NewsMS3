package com.newsms.service.impl;

import com.newsms.dao.NewsDao;
import com.newsms.dao.impl.NewsDaoImpl;
import com.newsms.entity.News;
import com.newsms.entity.Page;
import com.newsms.service.NewsService;

import java.util.List;

/**
 * (News)表服务实现类
 *
 * @author lmz
 * @since 2020-04-10 10:26:33
 */
public class NewsServiceImpl implements NewsService {
    private NewsDao newDao = new NewsDaoImpl();
    @Override
    public Page selectNewsByPage(Integer currPage, Integer limit) {
        Page pages = new Page();
        pages.setLimit(limit);
        pages.setCount(newDao.selectCount());
        if (currPage>pages.getCount()){
            currPage=pages.getCount();
        }
        pages.setPage(currPage);
        List<News> news = newDao.selectNewsByPage(currPage,limit);
        pages.setData(news);
        System.out.println("总页数："+pages.getCount());
        return pages;
    }

    @Override
    public News selectNewsByNewsId(Integer newsId) {
        return newDao.selectNewsByNewsId(newsId);
    }

    @Override
    public List<News> selectNewsByTopicId(Integer topicId) {
        return newDao.selectNewsByTopicId(topicId);
    }
}