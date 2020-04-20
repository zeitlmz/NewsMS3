<%@ page import="com.newsms.entity.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/read.css" rel="stylesheet" type="text/css"/>
    <%@include file="ppp.jsp"%>
    <%
        Integer newsId = Integer.valueOf(request.getParameter("newsId"));
        News news=newsservice.selectNewsByNewsId(newsId);
    %>
    <title><%=news.getNewstitle()%></title>
</head>
<body>
<div id="header_read"><a href="index.jsp">新闻主页</a></div>
<div id="container">
    <%@include file="index-elements/index_sidebar.jsp" %>
    <div class="main">
        <div class="class_type"><img src="images/class_type.gif" alt="新闻中心"/></div>
        <div class="content">
            <ul class="classlist">
                <table width="80%" align="center">
                    <tr width="100%">
                        <td colspan="2" align="center"><%=news.getNewstitle()%>></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">作者:<%=news.getNewsauthor()%></td>
                        <td align="left">发布时间：<%=news.getPublishdate()%></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"></td>
                    </tr>
                </table>
            </ul>
            <div id="content" style="padding: 50px;font-size:16px"><%=news.getContent()%></div>
            <ul class="classlist">
                <table width="80%" align="center">
                        <td colspan="6"> 暂无评论！</td>
                        <tr>
                            <td colspan="6">
                                <hr/>
                            </td>
                        </tr>
                            <tr>
                                <td> 留言人：</td>
                                <td>${comment.cauthor}</td>
                                <td> IP：</td>
                                <td>${comment.cip}</td>
                                <td> 留言时间：</td>
                                <td>${comment.cdate}</td>
                            </tr>
                            <tr>
                                <td colspan="6">${comment.ccontent}</td>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    <hr/>
                                </td>
                            </tr>
                </table>
            </ul>
            <ul class="classlist">
                <form action="util/news_control.jsp?opr=addComment&nid=${ news.nid}" method="post">
                    <table width="80%" align="center">
                        <tr>
                            <td> 评 论</td>
                        </tr>
                        <tr>
                            <td> 用户名：</td>
                            <td>
                                <c:if test="${admin!=null && admin.toString().length()>0}">
                                    <input id="cauthor" name="cauthor" value="<%=session.getAttribute("admin") %>"
                                           readonly="readonly" style="border:0px;"/>
                                </c:if>
                                <c:if test="${admin==null || admin.toString().length()<=0}">
                                    <input id="cauthor" name="cauthor" value="这家伙很懒什么也没留下"/>
                                </c:if>
                                IP：
                                <input name="cip" id="cip" value="<%=request.getRemoteAddr() %>" readonly="readonly"
                                       style="border:0px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><textarea name="ccontent" id="ccontent" cols="70" rows="10"></textarea>
                            </td>
                        </tr>
                        <td><input name="submit" value="发  表" type="submit"/>
                        </td>
                    </table>
                </form>
            </ul>
        </div>
    </div>
</div>
<%@include file="index-elements/index_bottom.html" %>
</body>
</html>
