<%@ page import="com.newsms.entity.News" %>
<%@ page import="com.newsms.entity.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <title>新闻管理后台</title>
    <link href="../css/admin.css" rel="stylesheet" type="text/css"/>
    <script src="../js/jquery-3.4.1.min.js"></script>
</head>
<body>
<%@include file="console_element/edit.jsp" %>
<div id="opt_area">
    <h1 id="opt_type"> 新闻列表 </h1>
    <table style="font-size: 14px">
        <tr style="font-weight: bolder;border: dashed 1px #d9d9d9">
            <td>编号</td>
            <td>标题</td>
            <td>操作</td>
        </tr>
        <ul class="classlist">
            <%--处理分页和遍历所有新闻--%>
            <%
                String pageIndex = request.getParameter("page");
                String limitIndex = request.getParameter("limit");
                if (limitIndex == null) {
                    limitIndex = "5";
                }
                if (pageIndex == null || Integer.parseInt(pageIndex) < 2) {
                    pageIndex = "1";
                }
                Integer limit = Integer.parseInt(limitIndex);
                Integer currPage = Integer.valueOf(pageIndex);
                Page pages = null;
                if (realName != null) {
                    pages = newsservice.selectNewsByRealName(currPage, limit, realName);
                    if (pages != null) {
                        for (News news : pages.getData()) {
                            out.print("<tr>\n" +
                                    "            <td width=\"60\" style=\"border-bottom: dashed 1px black;height: 35px\">" + news.getNewsid() + "</td>\n" +
                                    "            <td width=\"600\" style=\"border-bottom: dashed 1px black\">" + news.getNewstitle() + "</td>" +
                                    "            <td style=\"border-bottom:dashed 1px black;\">\n" +
                                    "                <a href=\"news_modify.jsp?newsId=" + news.getNewsid() + "\" style=\"color: cornflowerblue\">修改</a>-\n" +
                                    "                <a class='delNews' href=\"\"style=\"color: red\">删除</a>\n" +
                                    "            </td>\n" +
                                    "        </tr>");
                        }
                    } else {
                        out.print("暂无新闻~");
                    }
                    currPage = pages.getPage();
            %>
            <script>
                $(function () {
                    let url = window.location.href;
                    let limit = parseInt(window.location.href.split("limit=")[1]);
                    let page = parseInt(window.location.href.split("page=")[1].split("&limit=")[0]);
                    $(".delNews").click(function () {
                        const newsId = $(this).parent().parent().find("td:first").text();
                        const msg = confirm("确认删除编号为" + newsId + "的新闻？");
                        if (msg) {
                            location.href = "doDelNews.jsp?newsId=" + newsId;
                        }
                        return false;
                    });
                    if (page == null || page < 2) {
                        page = 1;
                    }
                    if (page >=<%=pages.getCount()%>) {
                        page =<%=pages.getCount()%>;
                    }
                    $("#limit").val(limit);
                    $("#limit").change(function () {
                        location.href = "admin.jsp?page=1&limit=" + $(this).val();
                    });
                    if (page ===<%=pages.getCount()%>) {
                        $("#fristPage").show();
                        $("#previousPage").show();
                        $("#nextPage").hide();
                        $("#lastPage").hide();
                    } else if (page === 1) {
                        $("#fristPage").hide();
                        $("#previousPage").hide();
                        $("#nextPage").show();
                        $("#lastPage").show();
                    }
                    $("#nextPage").click(function () {
                        location.href = "admin.jsp?page=" + (page + 1) + "&limit=" + limit;
                    });
                    $("#lastPage").click(function () {
                        location.href = "admin.jsp?page=<%=pages.getCount()%>&limit=" + limit;
                    });

                    $("#fristPage").click(function () {
                        location.href = "admin.jsp?page=1&limit=" + limit;
                    });
                    $("#previousPage").click(function () {
                        location.href = "admin.jsp?page=" + (page - 1) + "&limit=" + limit;
                    });
                })
            </script>
            <p>当前页数:[<%=currPage%>/<%=pages.getCount()%>]&nbsp;
                <button id="fristPage">首页&nbsp;&nbsp;</button>
                <button id="previousPage">上一页&nbsp;&nbsp;</button>
                <button id="nextPage">下一页&nbsp;&nbsp;</button>
                <button id="lastPage">末页</button>
                <select id="limit">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                    <option value="30">30</option>
                    <option value="35">35</option>
                    <option value="40">40</option>
                    <option value="45">45</option>
                    <option value="50">50</option>
                </select>
            </p>
            <%
                }
            %>
        </ul>
    </table>
</div>
</div>
<%@include file="../index-elements/index_bottom.html" %>
</body>
</html>
</div>
</body>
</html>
