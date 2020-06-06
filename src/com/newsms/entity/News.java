package com.newsms.entity;

/**
 * (News)表实体类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public class News {
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
    private Integer topicId;
    //话题名称
    private String topicName;
    //图片
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTopicName() {
        return topicName;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsid=" + newsid +
                ", newstitle='" + newstitle + '\'' +
                ", newsauthor='" + newsauthor + '\'' +
                ", content='" + content + '\'' +
                ", publishdate=" + publishdate +
                ", topicId=" + topicId +
                ", topicName=" + topicName +
                ", picture='" + picture + '\'' +
                '}';
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

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

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

}
