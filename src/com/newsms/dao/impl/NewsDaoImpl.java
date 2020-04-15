package com.newsms.dao.impl;

import java.util.ArrayList;

import java.sql.ResultSet;

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
        String sql = "select newsId,newsTitle,publishDate from news limit ?,?";
        Object[] parem = {page, limit};
        ResultSet rs = executeQuery(sql, parem);
        List<News> list = new ArrayList<>();
        try {
            while (rs.next()) {
                long newsId = rs.getLong("newsId");
                String newsTitle = rs.getString("newsTitle");
                String publishDate = rs.getString("publishDate");
                News news = new News();
                news.setNewsid(newsId);
                news.setNewstitle(newsTitle);
                news.setPublishdate(publishDate);
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        } finally {
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
        } finally {
            closeAll();
        }
        return news;
    }

    @Override
    public List<News> selectNewsByTopicId(Integer topicId) {
        String sql = "select newsId,newsTitle from news where topicId=?";
        ResultSet rs = executeQuery(sql, topicId);
        List<News> list = new ArrayList<>();
        try {
            while (rs.next()) {
                News news = new News();
                news.setNewsid(rs.getLong("newsId"));
                news.setNewstitle(rs.getString("newsTitle"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public List<News> selectNewsByRealName(Integer page,Integer limit,String newsAuthor) {
        String sql = "select * from news where newsAuthor=? limit ?,?";
        Object[] args={newsAuthor,page,limit};
        ResultSet rs = executeQuery(sql,args);
        List<News> list = new ArrayList<>();
        try {
            while (rs.next()) {
                News news = new News();
                news.setNewsid(rs.getLong("newsId"));
                news.setNewstitle(rs.getString("newsTitle"));
                news.setNewsauthor(rs.getString("newsAuthor"));
                news.setContent(rs.getString("content"));
                news.setPublishdate(rs.getString("publishDate"));
                news.setTopicId(rs.getInt("topicId"));
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }

    @Override
    public int selectCountByNewsAuthor(String newsAuthor) {
        String sql = "select count(*) from news where newsAuthor=?";
        ResultSet rs = executeQuery(sql,newsAuthor);
        int count = 0;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return count;
    }

}
