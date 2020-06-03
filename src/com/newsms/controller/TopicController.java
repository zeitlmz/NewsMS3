package com.newsms.controller;

import com.google.gson.Gson;
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
        String action = req.getParameter("actioon");
        if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        } else if ("".equals(action)) {

        }
    }

    public void selectTopicList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        List<Topic> list = topicService.selectTopicList();
        request.getSession().setAttribute("topicList", list);
        String a = gson.toJson(list);
        Writer out = response.getWriter();
        out.write(a);
//        response.sendRedirect("index.jsp");
    }

    public void addTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String topicName = request.getParameter("topicName");
        TopicService topicService = new TopicServiceImpl();
        Writer out = response.getWriter();
        if (topicService.addTopic(topicName)) {
            out.write("<h2>添加成功！</h2>");
        } else {
            out.write("<h2>添加失败！</h2>");
        }
        out.write("<a href='topic_list.jsp'>返回主题列表</a>");
    }

    public void delTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        int flag = topicService.delTopic(topicId);
        Writer writer = response.getWriter();
        if (flag == 1) {
            response.sendRedirect("topic_list.jsp");
        } else if (flag == 0) {
            writer.write("<h2>删除失败</h2><br><a href='topic_list.jsp'>点击回到主题列表</a>");
        } else if (flag == 2) {
            writer.write("<h2>该主题下至少有一篇新闻不能删除！</h2><br><a href='topic_list.jsp'>点击回到主题列表</a>");
        }
    }

    public void updateTopic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        String newTopic = request.getParameter("newTopic");
        int flag = topicService.updateTopic(topicId, newTopic);
        Writer writer = response.getWriter();
        if (flag > 0) {
            response.sendRedirect("topic_list.jsp");
        } else {
            writer.write("<h2>更新失败</h2><br><a href='topic_list.jsp'>点击回到主题列表</a>");
        }
    }

    public void selectTopicByid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        Topic topic = topicService.selectTopicByid(topicId);
        Gson gson = new Gson();
        String a = gson.toJson(topic);
        Writer writer = response.getWriter();
        writer.write(a);
    }
}
