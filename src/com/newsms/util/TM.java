package com.newsms.util;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * jdbc数据库连接线程安全加事务管理
 *
 * @author lmz
 */
public class TM {
    private static ThreadLocal<Connection> t1 = new ThreadLocal<>();
    private static DataSource dataSource = DBUtils.getDataSource();

    /**
     * 获取conn连接
     */
    public static Connection getConn() {
        Connection conn;
        try {
            //从本地线程中获取连接
            conn = t1.get();
            //判断当前线程上是否有连接
            if (conn == null) {
                conn = dataSource.getConnection();
                t1.set(conn);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 开启事务
     */
    public static void begin() {
        Connection conn = getConn();
        try {
            getConn().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            t1.set(conn);
        }
    }

    /**
     * 提交事务
     */
    public static void commit() {
        Connection conn = getConn();
        try {
            getConn().commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            t1.remove();
        }
    }

    /**
     * 回滚事务
     */
    public static void rollback() {
        Connection conn = getConn();
        try {
            conn.rollback();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            t1.remove();
        }
    }
}
