<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>新闻编辑</title>
    <link href="../css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <%@include file="console_element/edit.jsp"%>

    <div id="opt_area">
        <h1 id="opt_type"> 编辑新闻： </h1>
        <form action="../util/news_control.jsp?opr=updateNews" method="post" enctype="multipart/form-data" onsubmit="return check()">
            <p>
                <label> 主题 </label>
                <select name="ntid">
                    <option value='1'>娱乐</option>
                    <option value='1'>军事</option>
                    <option value='1'>科技</option>
                    <option value='1'>教育</option>
                    <option value='1'>金融</option>
                </select>
                <input type="hidden" name="nid" value="" />
            </p>
            <p>
                <label> 标题 </label>
                <input name="ntitle" id="ntitle" type="text" class="opt_input" value=""/>
            </p>
            <p>
                <label> 作者 </label>
                <input name="nauthor" id="nauthor" type="text" class="opt_input" value=""/>
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
                <input name="file" id="file" type="file" class="opt_input" />
            </p>
            <input type="submit" value="提交" class="opt_sub" />
            <input type="reset" value="重置" class="opt_sub" />
        </form>
        <h1 id="opt_type">
            修改新闻评论：
        </h1>
        <table width="80%" align="left">
            <tr>
                <td colspan="6"> 暂无评论！ </td>
            </tr>
            <tr>
                <td colspan="6"><hr /></td>
            </tr>
            <tr>
                <td> 留言人： </td>
                <td>张三</td>
                <td> IP： </td>
                <td>192.168.1.1</td>
                <td> 留言时间： </td>
                <td>2020-1-1</td>
                <td><a href="">删除</a></td>
            </tr>
            <tr>
                <td colspan="6">今天李名真， 刘云岑， 曾勇杰， 彭海</td>
            </tr>
            <tr>
                <td colspan="6"><hr />
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
