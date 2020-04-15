<%@ page import="com.newsms.service.NewsService" %>
<%@ page import="com.newsms.service.impl.NewsServiceImpl" %>
<%@ page import="com.newsms.service.AuthorService" %>
<%@ page import="com.newsms.service.impl.AuthorServiceImpl" %>
<%
    AuthorService authorService = new AuthorServiceImpl();
    NewsService newsservice = new NewsServiceImpl();
%>
