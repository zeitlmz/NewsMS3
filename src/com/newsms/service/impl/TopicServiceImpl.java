package com.newsms.service.impl;

import java.util.ArrayList;

import com.newsms.dao.TopicDao;
import com.newsms.dao.impl.TopicDaoImpl;
import com.newsms.entity.News;
import com.newsms.entity.Topic;
import com.newsms.service.TopicService;
import com.newsms.util.ObjectEmpty;

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
    public int delTopic(Integer topicId) {
        List<News> newsList = new NewsServiceImpl().selectNewsByTopicId(topicId);
        if (newsList.size() == 0) {
            return topicDao.delTopic(topicId);
        } else {
            return 2;
        }
    }

    @Override
    public int updateTopic(Integer topicId, String newTopic) {
        List<News> newsList = new NewsServiceImpl().selectNewsByTopicId(topicId);
        if (newsList.size() == 0) {
            return topicDao.updateTopic(topicId, newTopic);
        } else {
            return 2;
        }
    }

    @Override
    public Topic selectTopicByid(Integer topicId) {
        return topicDao.selectTopicByid(topicId);
    }
}