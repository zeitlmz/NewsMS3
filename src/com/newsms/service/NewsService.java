package com.newsms.service;


import com.newsms.entity.Page;

/**
 * (News)表服务接口
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public interface NewsService {
    Page selectNewsBypage(Integer currPage,Integer limit);
}