$(function () {
    $(".sidebar").load("sidebar");
    $("#bottmbar").load("bottombar");

    $(".login_sub").click(function () {
        $.ajax({
            url: 'user/login',
            method: "post",
            data: $("#loginform").serialize(),
            success: function (data) {
                if (data.code === 0) {
                    getUser();
                } else {
                    $("#error").html(data.msg);
                }
            },
            error: function () {
                alert("服务器开了点小差~！");
            }
        });
    });
    getUser();

    function getUser() {
        $.ajax({
            url: 'user/getSession',
            method: "post",
            success: function (data) {
                if (data !== null) {
                    $("#top_login").html("<span>欢迎管理员:" + data.realName + "登陆新闻管理系统！</span><a href='/NewsMS3/admin'>进入后台</a>--------<a onclick='logout()'>注销</a>");
                }
            },
            error: function () {
                alert("连接服务器失败！");
            }
        });
    }

    $.ajax({
        url: 'topic/topics',
        method: "get",
        success: function (data) {
            let stop = "<option value=''>全部</option>";
            for (const topic of data) {
                stop += "<option value='" + topic.topicId + "'>" + topic.topicName + "</option>";
            }
            $("#topics").html(stop);
        },
        error: function () {
            alert("连接服务器失败！");
        }
    });
});

function logout() {
    if (confirm("确认退出登录？")) {
        $.ajax({
            url: 'user/logout',
            method: "post",
            success: function () {
                location.reload();
            },
            error: function () {
                alert("连接服务器失败！");
            }
        });
    }
}

newsPage(1);

function newsPage(currPage) {
    $.ajax({
        url: 'news/newsPage',
        method: "get",
        data: {"currPage": currPage},
        success: function (data) {
            var newslist = data.data;
            var snews = "";
            for (const news of newslist) {
                snews += "<li><a href='/NewsMS3/newsread?newsid=" + news.newsid + "'>" + news.newstitle + "</a><span>" + news.newsauthor + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + news.publishdate + "</span></li>";
            }
            if (newslist.length !== 0) {
                snews += "当前页" + data.page + "/" + data.count;
            } else {
                snews += "<li>没有查询到任何信息(⊙o⊙)…</li>"
            }
            if (data.isPrevious) {
                snews += "<a onclick='newsPage(1)'>首页&nbsp;&nbsp;</a>\n" +
                    "<a onclick='newsPage(" + (data.page - 1) + ")'>上一页&nbsp;&nbsp;</a>";
            }
            if (data.isNext) {
                snews += "<a onclick='newsPage(" + (data.page + 1) + ")'>下一页&nbsp;&nbsp;</a>\n" +
                    "<a onclick='newsPage(" + data.count + ")'>末页</a> </p>";
            }
            $("#newslist").html(snews);

        },
        error: function () {
            alert("连接服务器失败！");
        }
    });
    return false;
}

function searchNews() {
    $.ajax({
        url: 'news/searchNews',
        method: "get",
        data: $("#searchNews").serialize(),
        success: function (data) {
            var snews = "";
            var newslist = data.data;
            for (const news of newslist) {
                snews += "<li><a href='/NewsMS3/newsread?newsid=" + news.newsid + "'>" + news.newstitle + "</a><span>" + news.newsauthor + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + news.publishdate + "</span></li>";
            }
            if (newslist.length !== 0) {
                snews += "当前页" + data.page + "/" + data.count;
            } else {
                snews += "<li>没有查询到任何信息(⊙o⊙)…</li>"
            }
            if (data.isPrevious) {
                snews += "<a onclick='newsPage(1)'>首页&nbsp;&nbsp;</a>\n" +
                    "<a onclick='newsPage(" + (data.page - 1) + ")'>上一页&nbsp;&nbsp;</a>";
            }
            if (data.isNext) {
                snews += "<a onclick='newsPage(" + (data.page + 1) + ")'>下一页&nbsp;&nbsp;</a>\n" +
                    "<a onclick='newsPage(" + data.count + ")'>末页</a> </p>";
            }
            $("#newslist").html(snews);

        },
        error: function () {
            alert("连接服务器失败！");
        }
    });
}
