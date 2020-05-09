package com.newsms.service.impl;

import com.newsms.dao.NewsDao;
import com.newsms.dao.impl.NewsDaoImpl;
import com.newsms.entity.News;
import com.newsms.entity.Page;
import com.newsms.service.NewsService;
import com.newsms.util.ConnectionUtils;
import com.newsms.util.DBUtils;
import com.newsms.util.TransactionManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (News)表服务实现类
 *
 * @author lmz
 * @since 2020-04-10 10:26:33
 */
public class NewsServiceImpl implements NewsService {
    /**
     * 连接辅助类（让连接变为线程安全）
     */
    private static ConnectionUtils con;
    /**
     * 事务管理辅助类
     */
    private static TransactionManager transactionManager;

    static {
        con = new ConnectionUtils();
        //向连接辅助类传递一个从JNDI获取的dataSource
        con.setDataSource(DBUtils.getDataSource());
    }

    @Override
    public Page selectNewsByPage(Integer currPage, Integer limit) {
        Page pages = new Page();
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        NewsDao newsDao = new NewsDaoImpl(con.getThreadConnection());
        pages.setLimit(limit);
        try {
            pages.setCount(newsDao.selectCount());
        } catch (SQLException e) {

            e.printStackTrace();
        }
        if (currPage > pages.getCount()) {
            currPage = pages.getCount();
        }
        pages.setPage(currPage);
        List<News> news = null;
        try {
            news = newsDao.selectNewsByPage((currPage - 1) * limit, limit);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        pages.setData(news);
        return pages;
    }

    @Override
    public News selectNewsByNewsId(Integer newsId) {
        News news = null;
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        NewsDao newsDao = new NewsDaoImpl(con.getThreadConnection());
        try {
            news = newsDao.selectNewsByNewsId(newsId);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return news;
    }

    @Override
    public List<News> selectNewsByTopicId(Integer topicId) {
        List<News> news = null;
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        NewsDao newsDao = new NewsDaoImpl(con.getThreadConnection());
        try {
            news = newsDao.selectNewsByTopicId(topicId);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return news;
    }

    /**
     * 通过分页信息和作者查询新闻
     *
     * @param currPage   当前页
     * @param limit      每页数据条数
     * @param newsAuthor 作者
     * @return 新闻列表
     */
    @Override
    public Page selectNewsByRealName(Integer currPage, Integer limit, String newsAuthor) {

        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        NewsDao newsDao = new NewsDaoImpl(con.getThreadConnection());
        Page pages = new Page();
        pages.setLimit(limit);
        try {
            pages.setCount(newsDao.selectCountByNewsAuthor(newsAuthor));
        } catch (SQLException e) {

            e.printStackTrace();
        }
        if (currPage > pages.getCount()) {
            currPage = pages.getCount();
        }
        pages.setPage(currPage);
        List<News> list = null;
        try {
            list = newsDao.selectNewsByRealName((currPage - 1) * limit, limit, newsAuthor);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        pages.setData(list);

        return pages;
    }

    @Override
    public Page searchNews(Map<String, Object> map) {

        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        NewsDao newsDao = new NewsDaoImpl(con.getThreadConnection());
        System.out.println("前端传入的当前页->" + map.get("page"));
        Page pages = new Page();
        pages.setLimit((Integer) map.get("limit"));
        Map<String, Object> map1 = new HashMap<>();
        for (String key : map.keySet()) {
            map1.put(key, map.get(key));
        }
        map1.remove("page");
        map1.remove("limit");
        Integer count = null;
        try {
            count = newsDao.selectCountBySearch(map1);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        System.out.println("查出的数据总数-》" + count);
        pages.setCount(count);
        if ((Integer) map.get("page") > pages.getCount() & pages.getCount() > 0) {
            map.put("page", pages.getCount());
        } else if ((Integer) map.get("page") < 1) {
            map.put("page", 1);
        }
        pages.setPage((Integer) map.get("page"));
        map.put("page", ((Integer) map.get("page") - 1) * (Integer) map.get("limit"));
        System.out.println("业务层计算的-》->" + map.get("page"));
        List<News> list = null;
        try {
            list = newsDao.selectNewsBySearch(map);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        pages.setData(list);
        return pages;
    }

    @Override
    public boolean updateNews(News news) {
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        NewsDao newsDao = new NewsDaoImpl(con.getThreadConnection());
        try {
            if (newsDao.updateNews(news) > 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addNews(News news) {
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        NewsDao newsDao = new NewsDaoImpl(con.getThreadConnection());
        try {
            if (newsDao.addNews(news) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delNewsByNewsId(Integer newsId) {
        //把从连接辅助类经过线程安全化处理的conn连接传给dao层
        NewsDao newsDao = new NewsDaoImpl(con.getThreadConnection());
        try {
            if (newsDao.delNewsByNewsId(newsId) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}