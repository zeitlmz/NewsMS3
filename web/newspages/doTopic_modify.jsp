<%@ page import="com.newsms.service.TopicService" %>
<%@ page import="com.newsms.service.impl.TopicServiceImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String topicName = request.getParameter("topicName");
    String getTopicId = request.getParameter("topicId");
    Integer topicId = Integer.parseInt(getTopicId);
    TopicService topicService = new TopicServiceImpl();
    int flag = topicService.updateTopic(topicId, topicName);
    if (flag == 1) {
        out.print("<h2>修改主题成功！</h2>");
    } else if (flag == 2) {
        out.print("<h2>该主题下至少有一篇新闻！不能修改！</h2>");
    } else {
        out.print("<h2>修改主题失败！</h2>");
    }
    out.print("<a href='topic_list.jsp'>返回主题列表</a>");
%>