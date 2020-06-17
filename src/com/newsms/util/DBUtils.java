package com.newsms.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
}
