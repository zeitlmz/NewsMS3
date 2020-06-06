package com.newsms.controller;

import com.alibaba.fastjson.JSON;
import com.newsms.entity.Topic;
import com.newsms.service.TopicService;
import com.newsms.service.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * @author lmz
 */
@WebServlet("/topic")
public class TopicController extends HttpServlet {

    private TopicService topicService = new TopicServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("action");
        if ("topics".equals(method)) {
            selectTopicList(req, resp);
        } else if ("topicadd".equals(method)) {
            addTopic(req, resp);
        } else if ("topicmodify".equals(method)) {
            updateTopic(req, resp);
        } else if ("topicdel".equals(method)) {
            delTopic(req, resp);
        } else if ("selectTopic".equals(method)) {
            selectTopicByid(req, resp);
        } else {
            req.getRequestDispatcher("/404.html").forward(req, resp);
        }
    }

    public void selectTopicList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        List<Topic> list = topicService.selectTopicList();
        Writer out = response.getWriter();
        String s = JSON.toJSONString(list);
        out.write(s);
    }

    public void addTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String topicName = request.getParameter("topicName");
        TopicService topicService = new TopicServiceImpl();
        Writer out = response.getWriter();
        boolean b = topicService.addTopic(topicName);
        String s = JSON.toJSONString(b);
        out.write(s);
    }

    public void delTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Writer writer = response.getWriter();
        Writer out = response.getWriter();
        int b = topicService.delTopic(topicId);
        String s = JSON.toJSONString(b);
        out.write(s);
    }

    public void updateTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        String newTopic = request.getParameter("topicName");
        Writer out = response.getWriter();
        int b = topicService.updateTopic(topicId, newTopic);
        String s = JSON.toJSONString(b);
        out.write(s);
    }

    public void selectTopicByid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.selectTopicByid(topicId);
        Writer out = response.getWriter();
        String s = JSON.toJSONString(topic);
        out.write(s);
    }
}
