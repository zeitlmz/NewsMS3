<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header">
    <div id="welcome">欢迎使用新闻管理系统！/<a href="${admin}/News?action=index">主页</a></div>
    <div id="nav">
        <div id="logo"><img src="./images/logo.jpg" alt="新闻中国"/></div>
        <div id="a_b01"><img src="./images/a_b01.gif" alt=""/></div>
    </div>
</div>
<div id="admin_bar">
    <div id="status">管理员
</div>
    <div id="channel"></div>
</div>
<div id="main">
    <div id="opt_list">
        <ul>
            <li><a href="admin.jsp?page=1&limit=5">新闻管理</a></li>
            <li><a href="news_add.jsp">添加新闻</a></li>
            <li><a href="topic_add.jsp">添加主题</a></li>
            <li><a href="topic_list.jsp">主题列表</a></li>
        </ul>
    </div>
