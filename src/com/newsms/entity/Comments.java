package com.newsms.entity;

import java.util.Date;

public class Comments {
    /**
     * 评论id
     */
    private Long cid;
    /**
     * 留言者昵称，默认为匿名
     */
    private String cname;

    /**
     * 评论内容
     */
    private String comm;
    /**
     * 新闻的id
     */
    private Long nid;
    /**
     * 留言时间
     */
    private Date ctime;

    @Override
    public String toString() {
        return "Comments{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", comm='" + comm + '\'' +
                ", nid=" + nid +
                ", ctime=" + ctime +
                '}';
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
