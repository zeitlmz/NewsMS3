package com.newsms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表(Comment)实体类
 *
 * @author makejava
 * @since 2020-06-07 21:45:32
 */
public class News implements Serializable {
    /**
     * 新闻id
     */
    private Long newsid;
    /**
     * 新闻标题
     */
    private String newstitle;
    /**
     * 作者姓名
     */
    private String newsauthor;
    /**
     * 新闻内容
     */
    private String content;
    /**
     * 发布时间
     */
    private Date publishdate;
    /**
     * 分类
     */
    private Integer topicId;
    /**
     * 话题名称
     */
    private String topicName;
    /**
     * 图片
     */
    private String picture;

    @Override
    public String toString() {
        return "News{" +
                "newsid=" + newsid +
                ", newstitle='" + newstitle + '\'' +
                ", newsauthor='" + newsauthor + '\'' +
                ", content='" + content + '\'' +
                ", publishdate=" + publishdate +
                ", topicId=" + topicId +
                ", topicName='" + topicName + '\'' +
                ", picture='" + picture + '\'' +
                '}';
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

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
