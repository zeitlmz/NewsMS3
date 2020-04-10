package com.newsms.entity;

/**
 * (Author)表实体类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
@SuppressWarnings("serial")
public class Author {

    private String userName;
    private String pwd;
    private String realName;
    private String imageUrl;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Author{" +
                "userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", realName='" + realName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}