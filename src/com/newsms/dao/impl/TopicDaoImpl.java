package com.newsms.dao.impl;

import com.newsms.dao.TopicDao;
import com.newsms.dao.baseDao;
import com.newsms.entity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmz
 */
public class TopicDaoImpl extends baseDao implements TopicDao {

    @Override
    public List<Topic> selectTopicList() {
        String sql = "select * from topic";
        ResultSet rs = executeQuery(sql);
        List<Topic> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setTopicId(rs.getInt("topicId"));
                topic.setTopicName(rs.getString("topicName"));
                list.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int addTopic(String topicName) {
        String sql = "insert into topic(topicName) values(?)";
        return executeUpdate(sql, topicName);
    }

    @Override
    public int delTopic(Integer topicId) {
        String sql = "delete form topic where topicId=?";
        return executeUpdate(sql, topicId);
    }

    @Override
    public int updateTopic(Integer topic, String newTopic) {
        String sql = "update topic set topicName=?";
        return executeUpdate(sql, newTopic);
    }
}
