package com.newsms.service;

import com.newsms.entity.Author;

import java.sql.SQLException;

/**
 * (Author)表服务接口
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public interface AuthorService {
    /**
     *通过账号和密码查询数据库用户表
     * @param userName 用户名
     * @param pwd 密码
     * @return 用户信息对象
     */
    Author login(String userName, String pwd) throws SQLException;
}