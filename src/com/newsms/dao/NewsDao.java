package com.newsms.dao;

import com.newsms.entity.News;

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
     */
    List<News> selectNewsByPage(Integer page, Integer limit);

    /**
     * 查询数据总条数
     *
     * @return 返回数据总条数
     */
    int selectCount();

    /**
     * 通过newId查询新闻
     *
     * @param newsId 新闻id
     * @return 新闻数据对象list集合
     */
    News selectNewsByNewsId(Integer newsId);

    /**
     * 通过newId查询新闻
     *
     * @param topicId 话题id
     * @return 新闻数据对象list集合
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
    List<News> selectNewsByRealName(Integer page, Integer limit, String newsAuthor);

    /**
     * 通过作者查询作者的发布的全部新闻总数
     *
     * @param newsAuthor 作者
     * @return 该作者的新闻总数
     */
    int selectCountByNewsAuthor(String newsAuthor);

    /**
     * 通过查询条件得到数据总条数
     *
     * @param map1 查询条件集合
     * @return 数据总条数
     */
    Integer selectCountBySearch(Map<String, Object> map1);

    /**
     * 通过分页和查询条件得到数据总条数
     *
     * @param map 查询条件集合
     * @return 新闻数据集合
     */
    List<News> selectNewsBySearch(Map<String, Object> map);

    /**
     * 根据新闻id修改内容
     *
     * @param news 包含修改的文章信息和查询条件新闻id
     * @return 修改成功数量
     */
    Integer updateNews(News news);

    /**
     * 添加新闻
     *
     * @param news 新闻内容
     * @return 添加成功数量
     */
    int addNews(News news);

    /**
     * 通过新闻编号删除该新闻
     * @param newsId 新闻编号
     * @return 删除成功的数量
     */
    int delNewsByNewsId(Integer newsId);
}
