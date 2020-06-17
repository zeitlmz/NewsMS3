$("#adminheader").load("adminheader");
$("#opt_list").load("adsidbar");
$("#friend").load("bottombar");

function newsPage(currPage) {
    $.ajax({
        url: 'news/newsPage',
        method: "get",
        data: {"currPage": currPage},
        success: function (data) {
            var newslist = data.data;
            var snews = "";
            for (const news of newslist) {
                snews += "<li><a href='/NewsMS3/newsread?newsid=" + news.newsid + "'>" + news.newstitle + "</a><span>" + news.newsauthor + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='/NewsMS3/newsmodify?newsid=" + news.newsid + "'>修改</a>&nbsp;&nbsp;<a onclick='del(" + news.newsid + ")'>删除</a></span></li>";
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

searchNews();

function searchNews() {
    $.ajax({
        url: 'news/searchNews',
        method: "get",
        data: $("#searchNews").serialize(),
        success: function (data) {
            var snews = "";
            var newslist = data.data;
            for (const news of newslist) {
                snews += "<li><a href='/NewsMS3/newsread?newsid=" + news.newsid + "'>" + news.newstitle + "</a><span>" + news.newsauthor + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='/NewsMS3/newsmodify?newsid=" + news.newsid + "'>修改</a>&nbsp;&nbsp;<a onclick='del(" + news.newsid + ")'>删除</a></span></li>";
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

function del(id) {
    if (confirm("确定删除此条新闻吗？")) {
        $.ajax({
            url: 'news/delete',
            data: {"newsid": id},
            success: function (data) {
                if (data) {
                    searchNews();
                    alert("删除成功！")
                } else {
                    alert("删除失败！")
                }
            },
            error: function () {
                alert("连接服务器失败！");
            }
        });
    }
}

$(function () {
    $.ajax({
        url: 'topic/topics',
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
