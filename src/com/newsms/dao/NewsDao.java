package com.newsms.dao;

import com.newsms.entity.News;

import java.util.List;

/**
 * @author lmz
 */
public interface NewsDao {
    /**
     *根据分页信息进行查询新闻
     * @param page 分页对象
     * @param limit 每页数据数量
     * @return list对象
     */
    List<News> selectNewsByPage(Integer page, Integer limit);

    /**
     * 查询数据总条数
     * @return 返回数据总条数
     */
    int selectCount();

    /**
     *通过newId查询新闻
     * @param newsId 新闻id
     * @return 新闻数据对象list集合
     */
    News selectNewsByNewsId(Integer newsId);

    /**
     *通过newId查询新闻
     * @param topicId 话题id
     * @return 新闻数据对象list集合
     */
    List<News> selectNewsByTopicId(Integer topicId);
}
