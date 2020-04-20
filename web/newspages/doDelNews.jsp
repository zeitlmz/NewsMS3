<%@ page import="com.newsms.service.NewsService" %>
<%@ page import="com.newsms.service.impl.NewsServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--删除新闻--%>
<%
    NewsService newsService = new NewsServiceImpl();
    String getNewsId = request.getParameter("newsId");
    Integer newsId = Integer.parseInt(getNewsId);
    if (newsService.delNewsByNewsId(newsId)) {
        out.print("<h2>删除成功！</h2>");
    } else {
        out.print("<h2>删除失败！</h2>");
    }
    out.print("<a href='admin.jsp?page=1&limit=5'>点击回到主页</a>");
%>
