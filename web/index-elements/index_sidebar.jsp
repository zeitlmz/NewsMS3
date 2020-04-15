<%@ page import="com.newsms.entity.News" %>
<%@ page import="java.util.List" %>
<div class="sidebar">
    <h1><img src="images/title_1.gif" alt="国内新闻"/></h1>
    <div class="side_list">
        <ul>
            <%
                List<News> gnNew1 = newsservice.selectNewsByTopicId(1);
                for (News new1 : gnNew1) {
                    out.print("<li><a href=\"news_read.jsp?newsId=" + new1.getNewsid() + "\">" + new1.getNewstitle() + "</a></li>");
                }
            %>
        </ul>
    </div>
    <h1><img src="images/title_2.gif" alt="国际新闻"/></h1>
    <div class="side_list">
        <ul>
            <%
                List<News> gwNew2 = newsservice.selectNewsByTopicId(2);
                for (News new2 : gwNew2) {
                    out.print("<li><a href=\"news_read.jsp?newsId=" + new2.getNewsid() + "\">" + new2.getNewstitle() + "</a></li>");
                }
            %>
        </ul>
    </div>
    <h1><img src="images/title_3.gif" alt="娱乐新闻"/></h1>
    <div class="side_list">
        <ul>
            <%
                List<News> ylNew3 = newsservice.selectNewsByTopicId(3);
                for (News new3 : ylNew3) {
                    out.print("<li><a href=\"news_read.jsp?newsId=" + new3.getNewsid() + "\">" + new3.getNewstitle() + "</a></li>");
                }
            %>
        </ul>
    </div>
</div>
