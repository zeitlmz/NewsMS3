<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.newsms.util.ObjectEmpty" %>
<%@ page import="com.newsms.entity.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="ppp.jsp" %>
<%
    request.setCharacterEncoding("utf-8");
    String pageIndex = request.getParameter("pageIndex");
    Integer limit = 10;
    if (pageIndex == null || Integer.parseInt(pageIndex) < 2) {
        pageIndex = "1";
    }
    Integer currPage = Integer.valueOf(pageIndex);
    if (currPage < 2) {
        currPage = 1;
    }
    Map<String, Object> map1 = (Map<String, Object>) session.getAttribute("searchInfo");
    if (ObjectEmpty.isNotEmpty(map1)) {
        map1.put("limit", limit);
        map1.put("page", currPage);
        Page pages = newsservice.searchNews(map1);
        session.setAttribute("pages", pages);
    } else {
        Page pages = newsservice.selectNewsByPage(currPage, limit);
        session.setAttribute("pages", pages);
    }
    response.sendRedirect("index.jsp");
%>