<%@page import="com.NewsMs.entity.News"%>
<%@page import="java.util.List"%>
<%@page import="com.NewsMs.service.impl.newsServiceimpl"%>
<%@page import="com.NewsMs.service.newsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新闻中国</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<%
	newsService newsservice = new newsServiceimpl();
	Integer pages = 1;
	Integer limit = 5;
	List<News> list = newsservice.selectNewsBypage(pages, limit);
%>
<body>
  
<div id="header">
  <div id="top_login">
    <form action="util/do_login.jsp" method="post">
      <label> 登录名 </label>
      <input type="text" name="uname" id="uname" value="" class="login_input" />
      <label> 密&#160;&#160;码 </label>
      <input type="password" name="upwd" id="upwd" value="" class="login_input" />
      <input type="submit" class="login_sub" value="登录" />
      <label id="error"> </label>
      <img src="images/friend_logo.gif" alt="Google" id="friend_logo" />
    </form>
  </div>
  <div id="nav">
    <div id="logo"> <img src="images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="images/a_b01.gif" alt="" /> </div>
    <!--mainnav end-->
  </div>
</div>
<div id="container">


<div class="sidebar">
    <h1> <img src="images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul>      
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
      </ul>
    </div>
    <h1> <img src="images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">
      <ul>      
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
      </ul>
    </div>
    <h1> <img src="images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <ul>            	
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>
		<li> <a href='news_read.jsp?nid=${ news.nid }'><b> 标题1</b></a> </li>      
      </ul>
    </div>
  </div>
 
  <div class="main">
    <div class="class_type"> <img src="images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
      <c:set value="0" var="n" />      
      	<c:forEach var="topic" items="${list4}">         
	      <c:set var="n" value="${n+1}"></c:set>
	      <c:choose>
	      <c:when test="${n%11==1}">
	      	<li id='class_month'><a href="util/news_control.jsp?opr=topicNew&tid=${topic.tid }"><b> ${topic.tname} </b></a>
	      </c:when>
	      <c:when test="${n%11==0}">    
	      	<a href="util/news_control.jsp?opr=topicNew&tid=${topic.tid }"><b> ${topic.tname} </b></a></li>
	      </c:when>
	      <c:otherwise >
	      	<a href="util/news_control.jsp?opr=topicNew&tid=${topic.tid }"><b> ${topic.tname} </b></a>
	      </c:otherwise>        
         </c:choose>
      </c:forEach>
      <c:if test="${n%11!=0}"></li></c:if>	
      </ul>
      <ul class="classlist">
       
       <%
       for(News news:list){
    	   out.print("<li><a href>"+news.getNewsTitlie()+"</a><li>");
       }
       %>
			当前页数:[1/10]&nbsp; 
			<a href="">首页&nbsp;&nbsp;</a>
			<a href="">上一页&nbsp;&nbsp;</a>
			<a href="">下一页&nbsp;&nbsp;</a> 
			<a href="">末页</a> </p>
	   	<ul>
    </div>   
	<div class="picnews">
	  <ul>
		<li> <a href="#"><img src="images/Picture1.jpg" width="249" alt="" /> </a><a href="#">幻想中穿越时空</a> </li>
		<li> <a href="#"><img src="images/Picture2.jpg" width="249" alt="" /> </a><a href="#">国庆多变的发型</a> </li>
		<li> <a href="#"><img src="images/Picture3.jpg" width="249" alt="" /> </a><a href="#">新技术照亮都市</a> </li>
		<li> <a href="#"><img src="images/Picture4.jpg" width="249" alt="" /> </a><a href="#">群星闪耀红地毯</a> </li>
	  </ul>
	</div>
  </div>
</div>
<div id="friend">
  <h1 class="friend_t"> <img src="images/friend_ico.gif" alt="合作伙伴" /> </h1>
  <div class="friend_list">
    <ul>
      <li> <a href="#">百度</a> </li>
      <li> <a href="#">谷歌</a> </li>
      <li> <a href="#">新浪</a> </li>
      <li> <a href="#">网易</a> </li>
      <li> <a href="#">搜狐</a> </li>
      <li> <a href="#">人人</a> </li>
      <li> <a href="#">中国政府网</a> </li>
    </ul>
  </div>
</div>
<div id="footer">
  <p class=""> 24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a> &#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
    文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a> </p>
  <p class="copyright"> Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
    新闻中国 版权所有 </p>
</div>
</body>
</html>