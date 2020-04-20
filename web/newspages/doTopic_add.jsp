<%@ page import="com.newsms.service.TopicService" %>
<%@ page import="com.newsms.service.impl.TopicServiceImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String topicName = request.getParameter("topicName");
    TopicService topicService = new TopicServiceImpl();
    if (topicService.addTopic(topicName)) {
        out.print("<h2>添加成功！</h2>");
    } else {
        out.print("<h2>添加失败！</h2>");
    }
    out.print("<a href='topic_list.jsp'>返回主题列表</a>");
%>