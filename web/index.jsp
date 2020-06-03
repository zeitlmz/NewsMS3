<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新闻中国</title>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/calendar/WdatePicker.js"></script>
    <style>
        .classlist li {
            background: red;
        }
    </style>
</head>
<body>
<%request.setAttribute("index", request.getContextPath());%>
<div id="header">
    <div id="top_login">
        <c:choose>
            <c:when test="${empty author}">
                <form action="${index}/login" method="post">
                    <label> 登录名 </label>
                    <input type="text" name="username" required value=""/>
                    <label> 密&#160;&#160;码 </label>
                    <input type="password" name="pwd" required value=""/>
                    <input type="submit" class="login_sub" value="登录"/>
                    <label id="error"></label>
                    <img src="images/friend_logo.gif" alt="Google" id="friend_logo"/>
                </form>
            </c:when>
        </c:choose>
    </div>
    <c:choose>
        <c:when test="${!empty author}">
            <script>
                document.getElementById("top_login").innerHTML = "欢迎管理员:${author.realName} 登陆新闻管理系统！--><a href='${index}/admin?action=adminSearchNews'>进入后台</a>";
            </script>
        </c:when>
    </c:choose>
    <div id="nav">
        <div id="logo"><img src="images/logo.jpg" alt="新闻中国"/></div>
        <div id="a_b01"><img src="images/a_b01.gif" alt=""/></div>
        <!--mainnav end-->
    </div>
</div>
<div id="container">
    <%@include file="index-elements/index_sidebar.jsp" %>
    <div class="main">
        <div class="class_type"><img src="images/class_type.gif" alt="新闻中心"/></div>
        <div class="content">
            <h1 id="opt_type"> 搜索新闻 </h1>
            <form action="${index}/News" method="get">
                <input type="hidden" name="action" value="searchNews">
                <table>
                    <tr>
                        <td><label>新闻标题</label></td>
                        <td><input type="text" name="newsTitle" placeholder="输入搜素内容"></td>
                        <td><label>作者</label></td>
                        <td><input type="text" name="newsAuthor" placeholder="输入搜素内容"></td>
                    </tr>
                    <tr>
                        <td><label>内容</label></td>
                        <td><input type="text" name="content" placeholder="输入搜素内容"></td>
                        <td><label>发布时间</label></td>
                        <td><input type="text" name="publishDate" class="Wdate" onFocus="WdatePicker({lang:'zh-cn'})">
                        </td>
                    </tr>
                    <tr>
                        <td><label>主题</label></td>
                        <td>
                            <select name="topicId">
                                <option value="">全部</option>
                            </select>
                        </td>
                        <td><label></label></td>
                        <td><input type="submit" value="搜索"></td>
                    </tr>
                </table>
            </form>
            <h1 id="opt_type"> 新闻列表 </h1>
            <ul class="classlist">
            </ul>
                    <a href="${index}/News?action=searchNewsByPage&pageIndex=1">首页&nbsp;&nbsp;</a>
                    <a href="${index}/News?action=searchNewsByPage&pageIndex=${indexNews.page-1}">上一页&nbsp;&nbsp;</a>
                    <a href="${index}/News?action=searchNewsByPage&pageIndex=${indexNews.page+1}">下一页&nbsp;&nbsp;</a>
                    <a href="${index}/News?action=searchNewsByPage&pageIndex=${indexNews.count}">末页</a> </p>
        </div>
        <div class="picnews">
            <ul>
                <li><a href="#"><img src="images/Picture1.jpg" width="249" alt=""/> </a><a href="#">幻想中穿越时空</a></li>
                <li><a href="#"><img src="images/Picture2.jpg" width="249" alt=""/> </a><a href="#">国庆多变的发型</a></li>
                <li><a href="#"><img src="images/Picture3.jpg" width="249" alt=""/> </a><a href="#">新技术照亮都市</a></li>
                <li><a href="#"><img src="images/Picture4.jpg" width="249" alt=""/> </a><a href="#">群星闪耀红地毯</a></li>
            </ul>
        </div>
    </div>
</div>
<%@include file="index-elements/index_bottom.html" %>
</body>
</html>
