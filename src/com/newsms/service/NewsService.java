package com.newsms.service;


import com.newsms.entity.News;
import com.newsms.entity.Page;

import java.util.List;

/**
 * (News)表服务接口
 *
 * @author lmz
 * @since 2020-04-10 10:26:33
 */
public interface NewsService {
    /**
     *通过分页信息查询新闻数据
     * @param currPage 当前页面
     * @param limit 每页的数据数量
     * @return 分页对象
     */
    Page selectNewsByPage(Integer currPage,Integer limit);

    /**
     *通过新闻编号获取新闻数据
     * @param newsId 新闻编号
     * @return 一个新闻对象
     */
    News selectNewsByNewsId(Integer newsId);

    /**
     *通过话题编号获取新闻数据集合
     * @param topicId 新闻编号
     * @return 新闻对象List集合
     */
    List<News> selectNewsByTopicId(Integer topicId);
}