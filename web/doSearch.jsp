<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.newsms.util.ObjectEmpty" %>
<%@ page import="com.newsms.entity.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="ppp.jsp" %>
<%
    request.setCharacterEncoding("utf-8");

    Integer currPage=1;
    Integer limit = 10;

    session.removeAttribute("searchInfo");

    String newsTitle = request.getParameter("newsTitle");
    String newsAuthor = request.getParameter("newsAuthor");
    String content = request.getParameter("content");
    String publishDate = request.getParameter("publishDate");
    String topicId = request.getParameter("topicId");

    Map<String, Object> map = new HashMap<>();

    if (ObjectEmpty.isNotEmpty(newsTitle)) {
        map.put("newsTitle", newsTitle);
    }
    if (ObjectEmpty.isNotEmpty(newsAuthor)) {
        map.put("newsAuthor", newsAuthor);
    }
    if (ObjectEmpty.isNotEmpty(content)) {
        map.put("content", content);
    }
    if (ObjectEmpty.isNotEmpty(publishDate)) {
        map.put("publishDate", publishDate);
    }
    if (ObjectEmpty.isNotEmpty(topicId)) {
        map.put("topicId", topicId);
    }

    map.put("limit", limit);
    map.put("page", currPage);
    session.setAttribute("searchInfo", map);
    Page pages = newsservice.searchNews(map);
    session.setAttribute("pages", pages);
    response.sendRedirect("index.jsp");
%>

