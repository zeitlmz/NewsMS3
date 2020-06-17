$(".sidebar").load("sidebar");
$("#bottmbar").load("bottombar");
var url = location.href;
var newsid = url.split("=")[1];
$("[name=nid]").val(newsid);
$.ajax({
    url: 'news/newsRead',
    data: {"newsid": newsid},
    success: function (news) {
        $("#title").html(news.newstitle);
        $("#topicName").html(news.topicName);
        $("#pushDate").html(news.publishdate);
        $("#author").html(news.newsauthor);
        $("#content").html(news.content);
    },
    error: function () {
        alert("连接服务器失败！");
    }
});

comments();

function comments() {
    var url = location.href;
    var newsid = url.split("=")[1];
    $.ajax({
        url: 'news/newscomments',
        data: {"newsid": newsid},
        success: function (comments) {
            var commstr = "";
            if (comments.length > 0) {
                var i = comments.length;
                for (const comment of comments) {
                    commstr += "<tr><td colspan=\"6\"><hr/></td></tr>\n" +
                        "     <tr><td>#" + i + "&nbsp;&nbsp;<span>" + comment.cname + "&nbsp;&nbsp;</span></td><td>日期： <span>" + comment.ctime + "</span></td></tr>" +
                        "     <tr><td colspan=\"6\">内容：<p>" + comment.comm + "</p></td></tr>\n" +
                        "     <tr><td colspan=\"6\"><hr/></td></tr>\n";
                    i--;
                }
            } else {
                commstr = "<li>暂时没有评论，快来抢一楼吧~</li>";
            }
            $("#comments").html(commstr);
        },
        error: function () {
            alert("连接服务器失败！");
        }
    });
}

function comms() {
    var commn = $("[name=comm]").val();

    if (commn !== null && commn !== undefined && commn !== "") {
        $.ajax({
            url: 'news/commns',
            method: "post",
            data: $("#commform").serialize(),
            success: function (data) {
                if (data) {
                    comments();
                } else {
                    alert("连接服务器时出了点小问题~")
                }
            },
            error: function () {
                alert("连接服务器失败！");
            }
        });
    } else {
        alert("评论内容不能为空");
        return false;
    }
    return false;
}
