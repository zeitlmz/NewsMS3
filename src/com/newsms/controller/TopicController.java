package com.newsms.controller;

import com.newsms.annotation.GetMapping;
import com.newsms.annotation.Params;
import com.newsms.annotation.PostMapping;
import com.newsms.annotation.RequestMapping;
import com.newsms.entity.Topic;
import com.newsms.service.TopicService;
import com.newsms.service.impl.TopicServiceImpl;
import com.newsms.util.TransactionManger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lmz
 */
@RequestMapping("/topic")
public class TopicController {
    private TopicService topicService = (TopicService) TransactionManger.getInstance(new TopicServiceImpl());

    /**
     * 查询所有话题
     *
     * @return 话题列表
     */
    @GetMapping("/topics")
    public List<Topic> topics() throws SQLException {
        return topicService.selectTopicList();
    }

    /**
     * 增加话题
     *
     * @param topicName 话题名称
     * @return 是否成功
     */
    @PostMapping("/topicadd")
    public boolean topicadd(@Params("topicName") String topicName) throws IOException, SQLException {
        return topicService.addTopic(topicName);
    }

    /**
     * 删除话题
     *
     * @return 是否成功
     */
    @PostMapping("/topicdel")
    public int topicdel(@Params("topicId") Integer topicId) throws IOException, SQLException {
        return topicService.delTopic(topicId);
    }

    /**
     * 修改话题
     *
     * @param topicId   话题id
     * @param topicName 话题新名称
     * @return 修改成功数量
     */
    @PostMapping("/topicmodify")
    public int topicmodify(@Params("topicId") Integer topicId, @Params("topicName") String topicName) throws SQLException {
        return topicService.updateTopic(topicId, topicName);
    }

    /**
     * 查询指定id的话题
     *
     * @param topicId 话题id
     * @return 话题对象
     */
    @PostMapping("/selectTopic")
    public Topic selectTopic(@Params("topicId") Integer topicId) throws IOException, SQLException {
        return topicService.selectTopicByid(topicId);
    }
}
