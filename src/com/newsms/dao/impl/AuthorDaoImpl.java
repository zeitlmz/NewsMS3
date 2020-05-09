package com.newsms.dao.impl;

import com.newsms.dao.AuthorDao;
import com.newsms.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lmz
 */
public class AuthorDaoImpl implements AuthorDao {
    private Connection conn = null;

    public AuthorDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Author login(String userName, String pwd) throws SQLException {
        String sql = "select * from author where userName=? and pwd=?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, pwd);
        ResultSet rs = pstm.executeQuery();
        Author author = new Author();
        while (rs.next()) {
            author.setUserName(rs.getString("userName"));
            author.setRealName(rs.getString("realName"));
            author.setImageUrl(rs.getString("imageUrl"));
        }
        return author;
    }
}
