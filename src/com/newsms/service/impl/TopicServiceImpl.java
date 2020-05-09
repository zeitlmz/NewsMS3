package com.newsms.service.impl;

import com.newsms.dao.TopicDao;
import com.newsms.dao.impl.TopicDaoImpl;
import com.newsms.entity.News;
import com.newsms.entity.Topic;
import com.newsms.service.TopicService;
import com.newsms.util.ConnectionUtils;
import com.newsms.util.DBUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * (Topic)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public class TopicServiceImpl implements TopicService {
    /**
     * 连接辅助类（让连接变为线程安全）
     */
    private static ConnectionUtils con = new ConnectionUtils();
    static {
        //向连接辅助类传递一个从JNDI获取的dataSource
        con.setDataSource(DBUtils.getDataSource());
    }

    @Override
    public List<Topic> selectTopicList() {
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        TopicDao topicDao = new TopicDaoImpl(con.getThreadConnection());
        List<Topic> topics = null;
        try {
            topics = topicDao.selectTopicList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }

    @Override
    public boolean addTopic(String topicName) {
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        TopicDao topicDao = new TopicDaoImpl(con.getThreadConnection());
        try {
            if (topicDao.addTopic(topicName) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int delTopic(Integer topicId) {
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        TopicDao topicDao = new TopicDaoImpl(con.getThreadConnection());
        List<News> newsList = new NewsServiceImpl().selectNewsByTopicId(topicId);
        try {
            if (newsList.size() == 0) {
                return topicDao.delTopic(topicId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    @Override
    public int updateTopic(Integer topicId, String newTopic) {
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        TopicDao topicDao = new TopicDaoImpl(con.getThreadConnection());
        List<News> newsList = new NewsServiceImpl().selectNewsByTopicId(topicId);
        int count = 0;
        if (newsList.size() == 0) {
            try {
                count = topicDao.updateTopic(topicId, newTopic);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            return 2;
        }
        return count;
    }

    @Override
    public Topic selectTopicByid(Integer topicId) {
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        TopicDao topicDao = new TopicDaoImpl(con.getThreadConnection());
        Topic topic = null;
        try {
            topic = topicDao.selectTopicByid(topicId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topic;
    }
}