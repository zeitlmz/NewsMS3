package com.newsms.entity;

/**
 * (Topic)表实体类
 *
 * @author lmz
 * @since 2020-04-10 10:26:33
 */
public class Topic {
    private Integer topicId;
    private String topicName;


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

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}