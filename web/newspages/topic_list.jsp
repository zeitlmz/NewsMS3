<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>添加主题--管理后台</title>
    <link href="../css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="console_element/edit.jsp" %>

<div id="opt_area">
    <h1 id="opt_type"> 话题列表 </h1>
    <label> 主题 &nbsp;</label>
    <table style="font-size: 14px">
        <tr style="font-weight: bolder;border: dashed 1px #d9d9d9">
            <td>编号</td>
            <td>标题</td>
            <td>操作</td>
        </tr>
        <tr>
            <td width="60">
            </td>
            <td width="200">
            </td>
            <td><a href="topic_modify.jsp?topicId=" style="color: cornflowerblue">修改</a>-<a class="delTopic" href="" style="color: red">删除</a>
        </tr>
    </table>
</div>
<script src="../js/jquery-3.4.1.min.js"></script>
<script>
    $(function () {
        $(".delTopic").click(function () {
            const topicId = $(this).parent().parent().find("td:first").text();
            const msg = confirm("确认删除编号为" + topicId + "的新闻？");
            if (msg) {
                location.href = "doTopic_del.jsp?topicId=" + topicId;
            }
            return false;
        });
    })
</script>
</body>
</html>
