package com.newsms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.newsms.dao.AuthorDao;
import com.newsms.dao.baseDao;
import com.newsms.entity.Author;

/**
 * @author lmz
 */
public class AuthorDaoImpl extends baseDao implements AuthorDao {

    @Override
    public Author login(String userName, String pwd) {
        String sql = "select * from user where userName=? and pwd=?";
        Object[] args = {userName, pwd};
        ResultSet rs = executeQuery(sql, args);
        Author author=new Author();
        try {
            while (rs.next()){
                author.setRealName(rs.getString("realName"));
                author.setImageUrl(rs.getString("imageUrl"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return author;
    }
}
