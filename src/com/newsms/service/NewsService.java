package com.newsms.service;


import com.newsms.entity.News;
import com.newsms.entity.Page;

import java.util.List;
import java.util.Map;

/**
 * (News)表服务接口
 *
 * @author lmz
 * @since 2020-04-10 10:26:33
 */
public interface NewsService {
    /**
     * 通过分页信息查询新闻数据
     *
     * @param currPage 当前页面
     * @param limit    每页的数据数量
     * @return 分页对象
     */
    Page selectNewsByPage(Integer currPage, Integer limit);

    /**
     * 通过新闻编号获取新闻数据
     *
     * @param newsId 新闻编号
     * @return 一个新闻对象
     */
    News selectNewsByNewsId(Integer newsId);

    /**
     * 通过话题编号获取新闻数据集合
     *
     * @param topicId 新闻编号
     * @return 新闻对象List集合
     */
    List<News> selectNewsByTopicId(Integer topicId);

    /**
     * 通过分页信息和作者查询新闻
     *
     * @param page       当前页
     * @param limit      每页数据条数
     * @param newsAuthor 作者
     * @return 新闻列表
     */
    Page selectNewsByRealName(Integer page, Integer limit, String newsAuthor);

    /**
     * 根据分页条件和条件查询新闻
     * @param map 条件集合
     * @return 分页数据对象
     */
    Page searchNews(Map<String, Object> map);

    boolean updateNews(News news);
}