$("#adminheader").load("adminheader");
$("#opt_list").load("adsidbar");
$("#friend").load("bottombar");

function topicList() {
    $.ajax({
        url: 'topic/topics',
        success: function (data) {
            var str = "<tr style=\"font-weight: bolder;border: dashed 1px #d9d9d9\">\n" +
                "            <td>标题</td>\n" +
                "            <td>操作</td>\n" +
                "        </tr>";
            for (const topic of data) {
                str += "<tr>\n" +
                    "       <td width=\"200\">" + topic.topicName + "</td>\n" +
                    "       <td>\n" +
                    "           <a href=\"/NewsMS3/topicmodify?topicId=" + topic.topicId + "\" style=\"color: cornflowerblue\">修改</a>-\n" +
                    "           <a class=\"delTopic\" onclick='topicdel(" + topic.topicId + ")' style=\"color: red\">删除</a>\n" +
                    "       </td>\n" +
                    "   </tr>";
            }
            $("table").html(str);
        },
        error: function () {
            alert("连接服务器失败！");
        }
    });
}

function topicdel(id) {
    if (confirm("确定删除此话题吗？")) {
        $.ajax({
            url: 'topic/topicdel',
            method:"post",
            data: {"topicId": id},
            success: function (data) {
                if (data === 1) {
                    topicList();
                    alert("删除成功！")
                } else if (data === 2) {
                    alert("此主题下至少有一篇文章不能删除！")
                }
            },
            error: function () {
                alert("连接服务器失败！");
            }
        });
    }
}
