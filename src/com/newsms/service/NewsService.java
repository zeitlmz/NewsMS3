package com.newsms.service;


import com.newsms.entity.Comments;
import com.newsms.entity.News;
import com.newsms.entity.Page;

import java.sql.SQLException;
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
    Page selectNewsByPage(Integer currPage, Integer limit) throws SQLException;

    /**
     * 通过新闻编号获取新闻数据
     *
     * @param newsId 新闻编号
     * @return 一个新闻对象
     */
    News selectNewsByNewsId(Integer newsId) throws SQLException;

    /**
     * 查询某条新闻下的评论
     *
     * @param newsId 新闻id
     * @return 评论列表
     */
    List<Comments> selectCommByNewsId(Integer newsId) throws SQLException;

    /**
     * 通过话题编号获取新闻数据集合
     *
     * @param topicId 新闻编号
     * @return 新闻对象List集合
     */
    List<News> selectNewsByTopicId(Integer topicId) throws SQLException;

    /**
     * 通过分页信息和作者查询新闻
     *
     * @param page       当前页
     * @param limit      每页数据条数
     * @param newsAuthor 作者
     * @return 新闻列表
     */
    Page selectNewsByRealName(Integer page, Integer limit, String newsAuthor) throws SQLException;

    /**
     * 根据分页条件和条件查询新闻
     *
     * @param map 条件集合
     * @return 分页数据对象
     */
    Page searchNews(Map<String, Object> map) throws SQLException;

    /**
     * 根据新闻id修改内容
     *
     * @param news 包含修改的文章信息和查询条件新闻id
     * @return 修改成功数量
     */
    boolean updateNews(News news) throws SQLException;

    /**
     * 添加新闻
     *
     * @param news 新闻内容
     * @return 是否成功
     */
    boolean addNews(News news) throws SQLException;

    /**
     * 通过新闻编号删除该新闻
     *
     * @param newsId 新闻编号
     * @return 是否删除成功
     */
    boolean delNewsByNewsId(Integer newsId) throws SQLException;

    /**
     * 添加评论
     *
     * @param cname 评论者名称
     * @param nid   新闻id
     * @param comm  评论内容
     * @return 成功数量
     */
    Boolean addComm(Comments comments) throws SQLException;
}
