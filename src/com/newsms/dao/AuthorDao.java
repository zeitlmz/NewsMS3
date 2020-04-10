package com.newsms.dao;

import com.newsms.entity.Author;

/**
 * (Author)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public interface AuthorDao {
    /**
     *通过用户名和密码查询数据库用户表
     * @param userName 用户名
     * @param pwd 密码
     * @return 用户信息对象
     */
    Author login(String userName, String pwd);
}