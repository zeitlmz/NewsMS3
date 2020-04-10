package com.newsms.entity;

/**
 * (News)表实体类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public class News{
    //新闻id
    private Long newsid;
    //新闻标题
    private String newstitle;
    //作者姓名
    private String newsauthor;
    //新闻内容
    private String content;
    //发布时间
    private Object publishdate;
    //分类
    private String newsclass;


    public Long getNewsid() {
        return newsid;
    }

    public void setNewsid(Long newsid) {
        this.newsid = newsid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewsauthor() {
        return newsauthor;
    }

    public void setNewsauthor(String newsauthor) {
        this.newsauthor = newsauthor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Object publishdate) {
        this.publishdate = publishdate;
    }

    public String getNewsclass() {
        return newsclass;
    }

    public void setNewsclass(String newsclass) {
        this.newsclass = newsclass;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsid=" + newsid +
                ", newstitle='" + newstitle + '\'' +
                ", newsauthor='" + newsauthor + '\'' +
                ", content='" + content + '\'' +
                ", publishdate=" + publishdate +
                ", newsclass='" + newsclass + '\'' +
                '}';
    }
}