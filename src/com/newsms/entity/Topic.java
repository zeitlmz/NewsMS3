package com.newsms.entity;

/**
 * (Topic)表实体类
 *
 * @author lmz
 * @since 2020-04-10 10:26:33
 */
public class Topic{
    //话题id
    private Integer topicid;
    //话题名称
    private String topicname;


    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicid=" + topicid +
                ", topicname='" + topicname + '\'' +
                '}';
    }
}