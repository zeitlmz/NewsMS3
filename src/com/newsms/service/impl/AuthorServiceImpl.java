package com.newsms.service.impl;

import com.newsms.dao.AuthorDao;
import com.newsms.dao.impl.AuthorDaoImpl;
import com.newsms.entity.Author;
import com.newsms.service.AuthorService;
import com.newsms.util.ConnectionUtils;
import com.newsms.util.DBUtils;

import java.sql.SQLException;

/**
 * (Author)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public class AuthorServiceImpl implements AuthorService {
    /**
     * 连接辅助类（让连接变为线程安全）
     */
    private ConnectionUtils con = new ConnectionUtils();
    /**
     * 事务管理辅助类
     */

    @Override
    public Author login(String userName, String pwd) {
        //向连接辅助类传递一个从JNDI获取的dataSource
        con.setDataSource(DBUtils.getDataSource());
        AuthorDao authorDao = new AuthorDaoImpl(con.getThreadConnection());
        Author author = null;
        try {
            author = authorDao.login(userName, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
}
