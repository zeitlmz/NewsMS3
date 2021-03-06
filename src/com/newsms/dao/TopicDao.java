package com.newsms.dao;

import com.newsms.entity.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * (Topic)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public interface TopicDao {

    /**
     * 获取所有的话题
     * @return 返回话题对象list集合
     */
    List<Topic> selectTopicList() throws SQLException;

    /**
     * 添加话题id和话题名称
     * @param topicName 话题名称
     * @return 数据库受影响行数
     */
    int addTopic(String topicName) throws SQLException;

    /**
     * 根据话题id删除话题以及该话题的全部新闻
     * @param topicId 话题id
     * @return 受影响行数
     */
    int delTopic(Integer topicId) throws SQLException;

    /**
     * 根据话题id修改该话题的名称
     * @param topic    话题id
     * @param newTopic 新的话题名称
     * @return 受影响行数
     */
    int updateTopic(Integer topic, String newTopic) throws SQLException;

    /**
     * 通过id查找主题
     * @param topicId 主题id
     * @return 主题信息
     */
    Topic selectTopicByid(Integer topicId) throws SQLException;
}
