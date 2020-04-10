package com.newsms.dao.impl;

import java.sql.ResultSet;

import java.util.ArrayList;

import java.sql.SQLException;
import java.util.List;

import com.newsms.dao.NewsDao;
import com.newsms.dao.baseDao;
import com.newsms.entity.News;

/**
 * @author lmz
 */
public class NewsDaoImpl extends baseDao implements NewsDao {

    @Override
    public List<News> selectNewsByPage(Integer page, Integer limit) {
        String sql = "select newsId,newsTitle from news limit ?,?";
        Object[] parem = {(page - 1) * limit, limit};
        ResultSet rs = executeQuery(sql, parem);
        List<News> list = new ArrayList<>();
        try {
            while (rs.next()) {
                long newsId = rs.getLong("newsId");
                String newsTitle = rs.getString("newsTitle");
                News news = new News();
                news.setNewsid(newsId);
                news.setNewstitle(newsTitle);
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int selectCount() {
        String sql = "select count(*) from news";
        ResultSet rs = executeQuery(sql);
        int count = 0;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return count;
    }

    @Override
    public News selectNewsByNewsId(Integer newsId) {
        String sql = "select * from news where newsId=?";
        ResultSet rs = executeQuery(sql, newsId);
        News news = new News();
        try {
            rs.next();
            news.setNewstitle(rs.getString("newsTitle"));
            news.setNewsauthor(rs.getString("newsAuthor"));
            news.setContent(rs.getString("content"));
            news.setPublishdate(rs.getDate("publishDate"));
            news.setTopicId(rs.getInt("topicId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return news;
    }

    @Override
    public List<News> selectNewsByTopicId(Integer topicId) {
        String sql = "select * from news where topicId=?";
        ResultSet rs = executeQuery(sql, topicId);
        List<News> list =new ArrayList<>();
        try {
            while (rs.next()) {
                News news=new News();
                news.setNewsid(rs.getLong("newsId"));
                news.setNewstitle(rs.getString("newsTitle"));
                news.setNewsauthor(rs.getString("newsAuthor"));
                news.setContent(rs.getString("content"));
                news.setPublishdate(rs.getDate("publishDate"));
                news.setTopicId(rs.getInt("topicId"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }

}
