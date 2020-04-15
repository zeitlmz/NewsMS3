<%--
  Created by IntelliJ IDEA.
  User: lmz
  Date: 2020/4/14
  Time: 15:03
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
        <h1 id="opt_type"> 添加主题： </h1>
        <form action="" method="post">
            <p>
                <label> 主题名称 </label>
                <input name="tname" type="text" class="opt_input" id="tname"/>
            </p>
            <input name="action" type="hidden" value="addtopic"/>
            <input type="submit" value="提交" class="opt_sub" />
            <input type="reset" value="重置" class="opt_sub" />
        </form>
    </div>
</div>
</body>
</html>
