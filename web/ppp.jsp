<%@ page import="com.newsms.service.NewsService" %>
<%@ page import="com.newsms.service.impl.NewsServiceImpl" %>
<%@ page import="com.newsms.service.AuthorService" %>
<%@ page import="com.newsms.service.impl.AuthorServiceImpl" %>
<%@ page import="com.newsms.service.TopicService" %>
<%@ page import="com.newsms.service.impl.TopicServiceImpl" %>
<%
    AuthorService authorService = new AuthorServiceImpl();
    NewsService newsservice = new NewsServiceImpl();
    TopicService topicService=new TopicServiceImpl();
%>
