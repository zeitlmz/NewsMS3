package com.newsms.service.impl;

import com.newsms.dao.AuthorDao;
import com.newsms.dao.impl.AuthorDaoImpl;
import com.newsms.entity.Author;
import com.newsms.service.AuthorService;
import com.newsms.util.TM;

import java.sql.SQLException;

/**
 * (Author)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;

    @Override
    public Author login(String userName, String pwd) {
        authorDao = new AuthorDaoImpl(TM.getConn());
        Author author = null;
        try {
            author = authorDao.login(userName, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
}
