<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.newsms.util.ObjectEmpty" %>
<%@ page import="com.newsms.entity.Page" %>
<%--
  Created by IntelliJ IDEA.
  User: lmz
  Date: 2020/4/15
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="ppp.jsp" %>
<%
    request.setCharacterEncoding("utf-8");
    String pageIndex = request.getParameter("pageIndex");
    Integer limit = 5;
    if (pageIndex == null || Integer.parseInt(pageIndex) < 2) {
        pageIndex = "1";
    }
    Integer currPage = Integer.valueOf(pageIndex);
    if (currPage < 2) {
        currPage = 1;
    }
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
    session.setAttribute("searchInfo",map);
    Map<String,Object> map1= (Map<String, Object>) session.getAttribute("searchInfo");
    Page pages = newsservice.searchNews(map1);
    System.out.println("doPage:page"+pages);
    session.setAttribute("pages", pages);
    response.sendRedirect("index.jsp");
%>
