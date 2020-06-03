<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>添加新闻</title>
    <link href="../css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="console_element/edit.jsp" %>

<div id="opt_area">
    <h1 id="opt_type"> 添加新闻： </h1>
    <form action="doAdd_news.jsp" method="post" enctype="multipart/form-data">
        <p>
            <label> 主题 &nbsp;</label>
            <select name="topicId">
                <c:forEach items="${topicList}" var="t">
                    <option value="${t.topicId}">${t.topicName}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label> 标题 </label>
            <input name="newsTitle" type="text" required class="opt_input" style="width: 300px;"/>
        </p>
        <p>
            <label> 作者 </label>
            <input name="newsAuthor" type="text" required class="opt_input" style="width:100px"/>
        </p>
        <label> 内容: </label>
        <p>
            <textarea name="content" cols="120" required rows="20"></textarea>
        </p>
        <p><label> 图片: </label>
            <input type="file" name="pic">
        </p>
        <input type="submit" value="提交" class="opt_sub"/>
        <input type="reset" value="重置" class="opt_sub"/>
    </form>
</div>
</div>
</body>
</html>
