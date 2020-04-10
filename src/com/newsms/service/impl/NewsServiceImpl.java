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

    @Override
    public Page selectNewsBypage(Integer currPage, Integer limit) {
        NewsDao newdao = new NewsDaoImpl();
        Page pages = new Page();
        pages.setCount(newdao.selectCount());
        if (currPage>pages.getCount()){
            currPage=pages.getCount();
        }
        pages.setPage(currPage);
        List<News> news = newdao.selectNewsBypage(currPage, limit);
        pages.setData(news);
        return pages;
    }
}