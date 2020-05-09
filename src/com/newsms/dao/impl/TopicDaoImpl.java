package com.newsms.dao.impl;

import com.newsms.dao.TopicDao;
import com.newsms.entity.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lmz
 */
public class TopicDaoImpl implements TopicDao {
    private Connection conn = null;

    public TopicDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Topic> selectTopicList() throws SQLException {
        String sql = "select * from topic";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Topic> list = new ArrayList<>();
        while (rs.next()) {
            Topic topic = new Topic();
            topic.setTopicId(rs.getInt("topicId"));
            topic.setTopicName(rs.getString("topicName"));
            list.add(topic);
        }
        return list;
    }

    @Override
    public int addTopic(String topicName) throws SQLException {
        String sql = "insert into topic(topicName) values(?)";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, topicName);
        int count=pstm.executeUpdate();
        System.out.println("成功添加主题：:"+count+"个");
        return count;
    }

    @Override
    public int delTopic(Integer topicId) throws SQLException {
        String sql = "delete from topic where topicId=?";
//        String sql = "delete from topic where topicId=?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setInt(1, topicId);
        int count=pstm.executeUpdate();
        System.out.println("成功删除主题：:"+count+"个");
        return count;
    }

    @Override
    public int updateTopic(Integer topicId, String newTopic) throws SQLException {
        String sql = "update topic set topicName=? where topicId=?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, newTopic);
        pstm.setInt(2, topicId);
        int count=pstm.executeUpdate();
        System.out.println("成功修改主题：:"+count+"个");
        return count;
    }

    @Override
    public Topic selectTopicByid(Integer topicId) throws SQLException {
        String sql = "select * from topic where topicId=?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setInt(1, topicId);
        ResultSet rs = pstm.executeQuery();
        Topic topic = null;
        rs.next();
        topic = new Topic();
        topic.setTopicId(rs.getInt("topicId"));
        topic.setTopicName(rs.getString("topicName"));
        return topic;
    }
}
