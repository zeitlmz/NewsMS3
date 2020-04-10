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
    List<News> selectNewsBypage(Integer page, Integer limit);

    /**
     * 查询数据总条数
     * @return 返回数据总条数
     */
    int selectCount();
}
