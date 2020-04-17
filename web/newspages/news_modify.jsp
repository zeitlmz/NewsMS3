<%@ page import="java.util.List" %>
<%@ page import="com.newsms.entity.News" %>
<%@ page import="com.newsms.entity.Topic" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>新闻编辑</title>
    <link href="../css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="console_element/edit.jsp" %>
<%
    Integer newsId = Integer.parseInt(request.getParameter("newsId"));
    News news = newsservice.selectNewsByNewsId(newsId);
%>
<div id="opt_area">
    <h1 id="opt_type"> 编辑新闻： </h1>
    <form action="doNews_modify.jsp" method="post">
        <input type="hidden" name="newsId" value="<%=newsId%>">
        <p>
            <label> 主题 &nbsp;</label>
            <select name="topicId">
                <%
                    List<Topic> topics = topicService.selectTopicList();
                    for (Topic topic : topics) {
                        out.print("<option value='" + topic.getTopicId() + "'>" + topic.getTopicName() + "</option>");
                    }
                %>
            </select>
        </p>
        <p>
            <label> 标题 </label>
            <input name="newsTitle" id="ntitle" type="text" required class="opt_input" style="width: 300px;"
                   value="<%=news.getNewstitle()%>"/>
        </p>
        <p>
            <label> 作者 </label>
            <input name="newsAuthor" id="nauthor" type="text" required class="opt_input" style="width:100px"
                   value="<%=news.getNewsauthor()%>"/>
        </p>
        <label> 内容: </label>
        <p>
            <textarea name="content" id="ncontent" cols="120" required rows="20"><%=news.getContent()%></textarea>
        </p>
        <%--        <p>--%>
        <%--            <label> 上传图片 </label>--%>
        <%--            <input name="file" id="file" type="file" class="opt_input"/>--%>
        <%--        </p>--%>
        <input type="submit" value="提交" class="opt_sub"/>
        <input type="reset" value="重置" class="opt_sub"/>
    </form>
    <%--    <h1 id="opt_type">--%>
    <%--        修改新闻评论：--%>
    <%--    </h1>--%>
    <%--    <table width="80%" align="left">--%>
    <%--        <tr>--%>
    <%--            <td colspan="6"> 暂无评论！</td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <td colspan="6">--%>
    <%--                <hr/>--%>
    <%--            </td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <td> 留言人：</td>--%>
    <%--            <td>张三</td>--%>
    <%--            <td> IP：</td>--%>
    <%--            <td>192.168.1.1</td>--%>
    <%--            <td> 留言时间：</td>--%>
    <%--            <td>2020-1-1</td>--%>
    <%--            <td><a href="">删除</a></td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <td colspan="6">今天李名真， 刘云岑， 曾勇杰， 彭海</td>--%>
    <%--        </tr>--%>
    <%--        <tr>--%>
    <%--            <td colspan="6">--%>
    <%--                <hr/>--%>
    <%--            </td>--%>
    <%--        </tr>--%>
    <%--    </table>--%>
</div>
</div>
</body>
</html>
