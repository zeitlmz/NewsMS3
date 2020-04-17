<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.newsms.entity.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../ppp.jsp" %>
<%
    request.setCharacterEncoding("utf-8");
    Long newsId = Long.parseLong(request.getParameter("newsId"));
    String newsTitle = request.getParameter("newsTitle");
    String newsAuthor = request.getParameter("newsAuthor");
    String content = request.getParameter("content");
    Integer topicId = Integer.valueOf(request.getParameter("topicId"));

    News news = new News();
    news.setNewstitle(newsTitle);
    news.setNewsauthor(newsAuthor);
    news.setContent(content);
    news.setTopicId(topicId);
    news.setNewsid(newsId);

    if (newsservice.updateNews(news)) {
        out.print("<h2>修改成功！</h2>");
        out.print("<a href='admin.jsp?page=1&limit=5'>点击回到主页</a>");
    } else {
        out.print("<h2>修改失败！</h2>");
        out.print("<a href='admin.jsp?page=1&limit=5'>点击回到主页</a>");
    }
%>