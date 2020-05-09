package com.newsms.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据访问辅助类
 *
 * @author cyg
 */
public class DBUtils {

    /**
     * 使用JNDI获取数据库连接对象
     *
     * @return
     */
    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/NewsMS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 释放资源
     *
     * @param ps 命令对象
     * @param rs 结果集
     */
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
