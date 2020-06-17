package com.newsms.service.impl;

import com.newsms.dao.TopicDao;
import com.newsms.dao.impl.TopicDaoImpl;
import com.newsms.entity.News;
import com.newsms.entity.Topic;
import com.newsms.service.TopicService;
import com.newsms.util.TM;

import java.sql.SQLException;
import java.util.List;

/**
 * (Topic)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao;

    @Override
    public List<Topic> selectTopicList() throws SQLException {
        topicDao = new TopicDaoImpl(TM.getConn());
        return topicDao.selectTopicList();
    }

    @Override
    public boolean addTopic(String topicName) throws SQLException {
        topicDao = new TopicDaoImpl(TM.getConn());
        return topicDao.addTopic(topicName) > 0;
    }

    @Override
    public int delTopic(Integer topicId) throws SQLException {
        topicDao = new TopicDaoImpl(TM.getConn());
        List<News> newsList = new NewsServiceImpl().selectNewsByTopicId(topicId);
        return newsList.size() == 0 ? topicDao.delTopic(topicId) : 2;
    }

    @Override
    public int updateTopic(Integer topicId, String newTopic) throws SQLException {
        topicDao = new TopicDaoImpl(TM.getConn());
        List<News> newsList = new NewsServiceImpl().selectNewsByTopicId(topicId);
        return newsList.size() == 0 ? topicDao.updateTopic(topicId, newTopic) : 2;
    }

    @Override
    public Topic selectTopicByid(Integer topicId) throws SQLException {
        topicDao = new TopicDaoImpl(TM.getConn());
        return topicDao.selectTopicByid(topicId);
    }
}
