package com.newsms.dao.impl;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Map;

import com.newsms.dao.NewsDao;
import com.newsms.dao.baseDao;
import com.newsms.entity.News;

import static com.newsms.util.dateForamte.dateToString;

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
                Timestamp publishDate = rs.getTimestamp("publishDate");
                News news = new News();
                news.setNewsid(newsId);
                news.setNewstitle(newsTitle);
                news.setPublishdate(dateToString(publishDate));
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
    public List<News> selectNewsByRealName(Integer page, Integer limit, String newsAuthor) {
        String sql = "select * from news where newsAuthor=? limit ?,?";
        Object[] args = {newsAuthor, page, limit};
        ResultSet rs = executeQuery(sql, args);
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
        ResultSet rs = executeQuery(sql, newsAuthor);
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
    public Integer selectCountBySearch(Map<String, Object> map1) {
        StringBuffer sb = new StringBuffer();
        Integer count = 0;
        ResultSet rs = null;
        Integer size = map1.size();
        if (size == 0) {
            String sql = "select count(*) from news";
            rs = executeQuery(sql);
            try {
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("总数量查询生成的sql---》》" + sql);
        } else {
            sb.append("select count(*) from news");
            List list = new ArrayList();
            for (String key : map1.keySet()) {
                list.add(map1.get(key));
                size--;
                if (size == map1.size() - 1) {
                    switch (key) {
                        case "newsTitle":
                        case "newsAuthor":
                        case "publishDate":
                        case "content":
                            sb.append(" where " + key + " like '%" + map1.get(key) + "%'");
                            break;
                        default:
                            sb.append(" join topic t on news.topicId=t.topicId where t.topicId=" + map1.get("topicId"));
                            break;
                    }
                } else {
                    switch (key) {
                        case "newsTitle":
                        case "newsAuthor":
                        case "content":
                        case "publishDate":
                            sb.append(" and " + key + " like '%" + map1.get(key) + "%' ");
                            break;
                        default:
                        case "topicId":
                            sb.append(" join topic t on news.topicId=t.topicId where t.topicId=" + map1.get("topicId"));
                            break;
                    }
                }
            }
            System.out.println("总数量查询生成的sql---》》" + sb);
            rs = executeQuery(String.valueOf(sb));
            try {
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public List<News> selectNewsBySearch(Map<String, Object> map) {
        System.out.println(map);
        StringBuffer sb = new StringBuffer();
        List<News> newsList = new ArrayList<>();
        Integer size = map.size();
        ResultSet rs = null;
        if (size == 2) {
            String sql = "select newsId,newsTitle,publishDate from news order by publishDate desc limit ?,? ";
            Object[] params = {map.get("page"), map.get("limit")};
            rs = executeQuery(sql, params);
            try {
                while (rs.next()) {
                    News news = new News();
                    news.setNewsid(rs.getLong("newsId"));
                    news.setNewstitle(rs.getString("newsTitle"));
                    news.setPublishdate(dateToString(rs.getDate("publishDate")));
                    newsList.add(news);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("条件查询生成的sql---》》" + sql);
        } else {
            sb.append("select newsId,newsTitle,publishDate from news");
            for (String key : map.keySet()) {
                size--;
                if (size == map.size() - 1) {
                    switch (key) {
                        case "newsTitle":
                        case "newsAuthor":
                        case "content":
                        case "publishDate":
                            sb.append(" where " + key + " like '%" + map.get(key) + "%'");
                            break;
                        case "topicId":
                            sb.append(" join topic t on news.topicId=t.topicId where t.topicId=" + map.get("topicId"));
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (key) {
                        case "newsTitle":
                        case "newsAuthor":
                        case "content":
                        case "publishDate":
                            System.out.println("中间-》 " + key + " 《-条件拼接了");
                            sb.append(" and " + key + " like '%" + map.get(key) + "%'");
                            break;
                        case "topicId":
                            System.out.println("多表联查topicId-》 " + key + " 《-条件拼接了");
                            sb.append(" join topic t on news.topicId=t.topicId where t.topicId=" + map.get("topicId"));
                            break;
                        default:
                            break;
                    }
                }
            }
            sb.append(" order by publishDate desc limit " + map.get("page") + "," + map.get("limit"));
            System.out.println("条件查询生成的sql---》》" + sb);
            rs = super.executeQuery(String.valueOf(sb));
            try {
                while (rs.next()) {
                    News news = new News();
                    news.setNewsid(rs.getLong("newsId"));
                    news.setNewstitle(rs.getString("newsTitle"));
                    news.setPublishdate(rs.getDate("publishDate"));
                    newsList.add(news);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newsList;
    }

    @Override
    public Integer updateNews(News news) {
        String sql = "update news\n" +
                "set newsTitle=?,\n" +
                "    newsAuthor=?,\n" +
                "    content=?,\n" +
                "    topicId=?\n" +
                "   where newsId=?;";
        Object[] params = {news.getNewstitle(), news.getNewsauthor(), news.getContent(), news.getTopicId(), news.getNewsid()};
        return executeUpdate(sql, params);
    }
}
