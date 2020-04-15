<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>添加新闻</title>
    <link href="../css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <%@include file="console_element/edit.jsp"%>

    <div id="opt_area">
        <h1 id="opt_type"> 添加新闻： </h1>
        <form action="../util/news_control.jsp?opr=addNews" enctype="multipart/form-data" method="post">
            <p>
                <label> 主题 </label>
                <select name="ntid" id="ntid">
                    <option value='1'>娱乐</option>
                    <option value='1'>军事</option>
                    <option value='1'>科技</option>
                    <option value='1'>教育</option>
                    <option value='1'>金融</option>
                </select>
            </p>
            <p>
                <label> 标题 </label>
                <input name="ntitle" id="ntitle" type="text" class="opt_input"/>
            </p>
            <p>
                <label> 作者 </label>
                <input name="nauthor" id=name="nauthor" type="text" class="opt_input"/>
            </p>
            <p>
                <label> 摘要 </label>
                <textarea name="nsummary" id="nsummary" cols="40" rows="3"></textarea>
            </p>
            <p>
                <label> 内容 </label>
                <textarea name="ncontent" id="ncontent" cols="70" rows="10"></textarea>
            </p>
            <p>
                <label> 上传图片 </label>
                <input type="file" name="nfile"/>
            </p>
            <input name="action" type="hidden" value="addnews"/>
            <input type="submit" value="提交" class="opt_sub"/>
            <input type="reset" value="重置" class="opt_sub"/>
        </form>
    </div>
</div>
</body>
</html>
