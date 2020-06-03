<div class="sidebar">
    <h1><img src="images/title_1.gif" alt="国内新闻"/></h1>
    <div class="side_list">
        <ul>
            <c:forEach items="${gn}" var="n">
                <li><a href="news_read.jsp?newsId=${n.newsid}">${n.newstitle}</a></li>
            </c:forEach>
        </ul>
    </div>
    <h1><img src="images/title_2.gif" alt="国际新闻"/></h1>
    <div class="side_list">
        <ul>
            <c:forEach items="${gj}" var="j">
                <li><a href="news_read.jsp?newsId=${j.newsid}">${j.newstitle}</a></li>
            </c:forEach>
        </ul>
    </div>
    <h1><img src="images/title_3.gif" alt="娱乐新闻"/></h1>
    <div class="side_list">
        <ul>
            <c:forEach items="${yl}" var="l">
                <li><a href="news_read.jsp?newsId=${l.newsid}">${l.newstitle}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
