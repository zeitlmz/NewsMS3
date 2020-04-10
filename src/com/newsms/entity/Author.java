package com.newsms.entity;

/**
 * (Author)表实体类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
@SuppressWarnings("serial")
public class Author{
    //新闻发布者账号
    private String username;
    //账号的密码
    private String pwd;
    //真实姓名
    private String realname;
    //头像路径
    private String immgurl;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getImmgurl() {
        return immgurl;
    }

    public void setImmgurl(String immgurl) {
        this.immgurl = immgurl;
    }

    @Override
    public String toString() {
        return "Author{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", realname='" + realname + '\'' +
                ", immgurl='" + immgurl + '\'' +
                '}';
    }
}