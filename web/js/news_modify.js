$("#adminheader").load("adminheader");
$("#opt_list").load("adsidbar");
$("#friend").load("bottombar");

function newsread() {
    var url = location.href;
    var newsid = url.split("=")[1];
    $("[name=newsid]").val(newsid);
    $.ajax({
        url: 'news/adnewsRead', data: {"newsid": newsid}, success: function (data) {
            const topics = data.topics;
            let stop = "";
            for (const topic of topics) {
                stop += "<option value='" + topic.topicId + "'>" + topic.topicName + "</option>";
            }
            $("#topics").html(stop);
            const news = data.news;
            $("[name=newstitle]").val(news.newstitle);
            $("[name=publishdate]").val(news.publishdate);
            $("[name=newsauthor]").val(news.newsauthor);
            $("[name=content]").val(news.content);
        }, error: function () {
            alert("连接服务器失败！");
        }
    });
}

function newsUpdate() {
    $.ajax({
        url: 'news/newsmodify', method: "post", data: $("#newsform").serialize(), success: function (data) {
            if (data) {
                alert("修改成功！")
            } else {
                alert("修改失败！")
            }
        }, error: function () {
            alert("连接服务器失败！");
        }
    });
}
