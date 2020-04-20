<%@ page import="com.newsms.entity.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../ppp.jsp" %>
<%
    request.setCharacterEncoding("utf-8");
    String newsTitle = request.getParameter("newsTitle");
    String newsAuthor = request.getParameter("newsAuthor");
    String content = request.getParameter("content");
    Integer topicId = Integer.valueOf(request.getParameter("topicId"));

    News news = new News();
    news.setNewstitle(newsTitle);
    news.setNewsauthor(newsAuthor);
    news.setContent(content);
    news.setTopicId(topicId);

    if (newsservice.addNews(news)) {
        out.print("<h2>添加成功！</h2>");
        out.print("<a href='admin.jsp?page=1&limit=5'>点击回到主页</a>");
    } else {
        out.print("<h2>添加失败！</h2>");
        out.print("<a href='admin.jsp?page=1&limit=5'>点击回到主页</a>");
    }
%>