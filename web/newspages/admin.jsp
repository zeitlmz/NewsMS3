<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <title>新闻管理后台</title>
    <link href="./css/admin.css" rel="stylesheet" type="text/css"/>
    <script src="./js/jquery-3.4.1.min.js"></script>
</head>
<body>
<%request.setAttribute("admin", request.getContextPath());%>
<%@include file="console_element/edit.jsp" %>
<div id="opt_area">
    <div class="content">
        <h1 id="opt_type"> 搜索新闻 </h1>
        <form action="${admin}/admin" method="get">
            <input type="hidden" name="action" value="adminSearchNews">
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
                            <c:forEach items="${topics}" var="t">
                                <option value="${t.topicId}">${t.topicName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><label></label></td>
                    <td><input type="submit" value="搜索"></td>
                </tr>
            </table>
        </form>
        <h1 id="opt_type"> 新闻列表 </h1>
        <ul class="classlist">
            <c:forEach items="${adminNews.data}" var="news">
                <li>
                    <a href="${admin}/admin?action=newsRead?newsid=${news.newsid}">${news.newstitle}</a><span></span><span>${news.publishdate}</span>
                </li>
            </c:forEach>
            <c:choose>
                <c:when test="${empty adminNews.data}">
                    <li>没搜到相关新闻(⊙o⊙)…</li>
                </c:when>
            </c:choose>
        </ul>
        <c:choose>
            <c:when test="${adminNews.count>0}">当前${adminNews.page}/${adminNews.count}页</c:when>
        </c:choose>
        <c:choose>
            <c:when test="${adminNews.isPrevious}">
                <a href="${admin}/admin?action=adminSearchNewsPage&pageIndex=1">首页&nbsp;&nbsp;</a>
                <a href="${admin}/admin?action=adminSearchNewsPage&pageIndex=${adminNews.page-1}">上一页&nbsp;&nbsp;</a>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${adminNews.isNext}">
                <a href="${admin}/admin?action=adminSearchNewsPage&pageIndex=${adminNews.page+1}">下一页&nbsp;&nbsp;</a>
                <a href="${admin}/admin?action=adminSearchNewsPage&pageIndex=${adminNews.count}">末页</a> </p>
            </c:when>
        </c:choose>
    </div>
</div>
<%@include file="../index-elements/index_bottom.html" %>
</body>
</html>
