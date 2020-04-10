package com.newsms.service.impl;

import com.newsms.dao.AuthorDao;
import com.newsms.dao.impl.AuthorDaoImpl;
import com.newsms.entity.Author;
import com.newsms.service.AuthorService;

/**
 * (Author)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 10:26:33
 */
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao = new AuthorDaoImpl();

    @Override
    public Author login(String userName, String pwd) {
        return authorDao.login(userName, pwd);
    }
}