package com.newsms.service.impl;

import com.newsms.dao.TopicDao;
import com.newsms.dao.impl.TopicDaoImpl;
import com.newsms.entity.Topic;
import com.newsms.service.TopicService;

import java.util.List;

/**
 * (Topic)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao = new TopicDaoImpl();

    @Override
    public List<Topic> selectTopicList() {
        return topicDao.selectTopicList();
    }

    @Override
    public boolean addTopic(String topicName) {
        return topicDao.addTopic(topicName) > 0;
    }

    @Override
    public boolean delTopic(Integer topicId) {
        return topicDao.delTopic(topicId) > 0;
    }

    @Override
    public boolean updateTopic(Integer topicId, String newTopic) {
        return topicDao.updateTopic(topicId, newTopic) > 0;
    }
}