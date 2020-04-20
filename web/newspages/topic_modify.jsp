<%@ page import="com.newsms.entity.Topic" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>主题编辑</title>
    <link href="../css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="console_element/edit.jsp" %>
<%
    String getTopicId = request.getParameter("topicId");
    Integer topicId = Integer.parseInt(getTopicId);
    Topic topic = topicService.selectTopicByid(topicId);
%>
<div id="opt_area">
    <h1 id="opt_type"> 修改主题： </h1>
    <form action="doTopic_modify.jsp" method="post">
        <p>
            <label> 主题名称 </label>
            <input type="hidden" name="topicId" value="<%=topic.getTopicId()%>">
            <input name="topicName" type="text" class="opt_input" value="<%=topic.getTopicName()%>"/>
        </p>
        <input type="submit" value="提交" class="opt_sub"/>
        <input type="reset" value="重置" class="opt_sub"/>
    </form>
</div>
</body>
</html>
