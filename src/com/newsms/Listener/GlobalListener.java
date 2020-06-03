package com.newsms.Listener;

import com.newsms.entity.Topic;
import com.newsms.service.TopicService;
import com.newsms.service.impl.TopicServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class GlobalListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化成功！");
        TopicService topicService = new TopicServiceImpl();
        List<Topic> topics = topicService.selectTopicList();
        sce.getServletContext().setAttribute("topics", topics);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
