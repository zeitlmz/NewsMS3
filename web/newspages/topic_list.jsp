<%--
  Created by IntelliJ IDEA.
  User: lmz
  Date: 2020/4/14
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>添加主题--管理后台</title>
    <link href="../css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%@include file="console_element/edit.jsp"%>

    <div id="opt_area">
        <h1 id="opt_type"> 话题列表 </h1>
        <table style="font-size: 14px">
            <tr style="font-weight: bolder;border: dashed 1px #d9d9d9">
            <td>标题</td>
                <td>操作</td>
            </tr>
            <tr>
                <td width="260">娱乐</td>
                <td><a href="topic_modify.jsp" style="color: cornflowerblue">修改</a>-<a href="" style="color: red">删除</a></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
