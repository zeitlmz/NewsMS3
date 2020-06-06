$("#adminheader").load("adminheader");
$("#opt_list").load("adsidbar");
$("#friend").load("bottombar");
$(function () {
    $.ajax({
        url: 'topic',
        data: {"action": "topics"},
        success: function (data) {
            let stop = "";
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

$("#addNews").click(function () {
    $.ajax({
        url: 'news', method: "post", data: $("#newsaddForm").serialize(), success: function (data) {
            if (data) {
                if (confirm("添加成功是否继续添加？")) {
                    $("textarea").val("");
                    $("[name=newsTitle]").val("");
                    $("[name=newsAuthor]").val("");
                    $("[name=content]").val("");
                } else {
                    location.href = "/NewsMS3/admin";
                }
            } else {
                alert("添加失败！")
            }
        }, error: function () {
            alert("连接服务器失败！");
        }
    });
    return false;
});
