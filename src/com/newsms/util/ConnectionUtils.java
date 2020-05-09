package com.newsms.util;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接工具类
 * @author lmz
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> t1=new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getThreadConnection(){

        try{
            //1.先从ThreadLocal上获取连接
            Connection conn=t1.get();
            //2.判断当前线程上是否有连接
            if (conn == null){
                conn=dataSource.getConnection();
                t1.set(conn);
            }
            return conn;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        t1.remove();
    }


}
