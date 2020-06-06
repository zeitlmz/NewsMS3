$("#adminheader").load("adminheader");
$("#opt_list").load("adsidbar");
$("#friend").load("bottombar");
$("#addTopic").click(function () {
    $.ajax({
        url: 'topic', data: $("#topicAddForm").serialize(), method: "post", success: function (data) {
            if (data) {
                if (confirm("添加成功是否继续添加？")) {
                    $("[name=topicName]").val("");
                } else {
                    location.href = "/NewsMS3/topiclist";
                }
            } else {
                alert("添加失败,主题已存在！")
            }
        }, error: function () {
            alert("连接服务器失败！");
        }
    });
    return false;
});
