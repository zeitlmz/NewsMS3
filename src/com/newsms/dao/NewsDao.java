package com.newsms.dao;

import com.newsms.entity.Comments;
import com.newsms.entity.News;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author lmz
 */
public interface NewsDao {

    /**
     * 根据分页信息进行查询新闻
     *
     * @param page  分页对象
     * @param limit 每页数据数量
     * @return list对象
     * @throws SQLException sql异常
     */
    List<News> selectNewsByPage(Integer page, Integer limit) throws SQLException;

    /**
     * 查询数据总条数
     *
     * @return 返回数据总条数
     * @throws SQLException sql异常
     */
    int selectCount() throws SQLException;

    /**
     * 通过newId查询新闻
     *
     * @param newsId 新闻id
     * @return 新闻数据对象list集合
     * @throws SQLException sql异常
     */
    News selectNewsByNewsId(Integer newsId) throws SQLException;

    /**
     * 查询某条新闻下的所有评论
     * @param newsId 新闻id
     * @return 评论列表
     */
    List<Comments> selectNewsCommByNewsId(Integer newsId) throws SQLException;
    /**
     * 通过newId查询新闻
     *
     * @param topicId 话题id
     * @return 新闻数据对象list集合
     * @throws SQLException sql异常
     */
    List<News> selectNewsByTopicId(Integer topicId) throws SQLException;

    /**
     * 通过分页信息和作者查询新闻
     *
     * @param page       当前页
     * @param limit      每页数据条数
     * @param newsAuthor 作者
     * @return 新闻列表
     * @throws SQLException sql异常
     */
    List<News> selectNewsByRealName(Integer page, Integer limit, String newsAuthor) throws SQLException;

    /**
     * 通过作者查询作者的发布的全部新闻总数
     *
     * @param newsAuthor 作者
     * @return 该作者的新闻总数
     * @throws SQLException sql异常
     */
    int selectCountByNewsAuthor(String newsAuthor) throws SQLException;

    /**
     * 通过查询条件得到数据总条数
     *
     * @param map1 查询条件集合
     * @return 数据总条数
     * @throws SQLException sql异常
     */
    Integer selectCountBySearch(Map<String, Object> map1) throws SQLException;

    /**
     * 通过分页和查询条件得到数据总条数
     *
     * @param map 查询条件集合
     * @return 新闻数据集合
     * @throws SQLException sql异常
     */
    List<News> selectNewsBySearch(Map<String, Object> map) throws SQLException;

    /**
     * 根据新闻id修改内容
     *
     * @param news 包含修改的文章信息和查询条件新闻id
     * @return 修改成功数量
     * @throws SQLException sql异常
     */
    Integer updateNews(News news) throws SQLException;

    /**
     * 添加新闻
     *
     * @param news 新闻内容
     * @return 添加成功数量
     * @throws SQLException sql异常
     */
    Integer addNews(News news) throws SQLException;

    /**
     * 通过新闻编号删除该新闻
     *
     * @param newsId 新闻编号
     * @return 删除成功的数量
     * @throws SQLException sql异常
     */
    Integer delNewsByNewsId(Integer newsId) throws SQLException;

    /**
     * 添加评论
     * @param  comments 评论信息对象
     * @return 成功数量
     */
    Integer addComm(Comments comments) throws SQLException;
}
