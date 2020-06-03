package com.newsms.dao.impl;

import com.newsms.dao.NewsDao;
import com.newsms.entity.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.newsms.util.dateForamte.dateToString;

/**
 * @author lmz
 */
public class NewsDaoImpl implements NewsDao {
    private Connection conn = null;

    public NewsDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<News> selectNewsByPage(Integer page, Integer limit) throws SQLException {
        String sql = "select newsId,newsTitle,publishDate from news limit ?,?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setInt(1, page);
        pstm.setInt(2, limit);
        ResultSet rs = pstm.executeQuery();
        List<News> list = new ArrayList<>();
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
        return list;
    }

    @Override
    public int selectCount() throws SQLException {
        String sql = "select count(*) from news";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        int count = 0;
        rs.next();
        count = rs.getInt(1);
        return count;
    }

    @Override
    public News selectNewsByNewsId(Integer newsId) throws SQLException {
        String sql = "select * from news where newsId=?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setInt(1, newsId);
        ResultSet rs = pstm.executeQuery();
        News news = new News();
        rs.next();
        news.setNewstitle(rs.getString("newsTitle"));
        news.setNewsauthor(rs.getString("newsAuthor"));
        news.setContent(rs.getString("content"));
        news.setPublishdate(rs.getDate("publishDate"));
        news.setTopicId(rs.getInt("topicId"));
        return news;
    }

    @Override
    public List<News> selectNewsByTopicId(Integer topicId) throws SQLException {
        String sql = "select newsId,newsTitle from news where topicId=? limit 0,10";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setInt(1, topicId);
        ResultSet rs = pstm.executeQuery();
        List<News> list = new ArrayList<>();
        while (rs.next()) {
            News news = new News();
            news.setNewsid(rs.getLong("newsId"));
            news.setNewstitle(rs.getString("newsTitle"));
            list.add(news);
        }
        return list;
    }

    @Override
    public List<News> selectNewsByRealName(Integer page, Integer limit, String newsAuthor) throws SQLException {
        String sql = "select * from news where newsAuthor=? limit ?,?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, newsAuthor);
        pstm.setInt(2, page);
        pstm.setInt(3, limit);
        ResultSet rs = pstm.executeQuery();
        List<News> list = new ArrayList<>();
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
        return list;
    }

    @Override
    public int selectCountByNewsAuthor(String newsAuthor) throws SQLException {
        String sql = "select count(*) from news where newsAuthor=?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, newsAuthor);
        ResultSet rs = pstm.executeQuery();
        int count = 0;
        rs.next();
        count = rs.getInt(1);
        return count;
    }

    @Override
    public Integer updateNews(News news) throws SQLException {
        String sql = "update news\n" +
                "set newsTitle=?,\n" +
                "    newsAuthor=?,\n" +
                "    content=?,\n" +
                "    topicId=?\n" +
                "   where newsId=?";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, news.getNewstitle());
        pstm.setString(2, news.getNewsauthor());
        pstm.setString(3, news.getContent());
        pstm.setInt(4, news.getTopicId());
        pstm.setLong(5, news.getNewsid());
        int count = pstm.executeUpdate();
        System.out.println("成功修改新闻：:" + count + "条");
        return count;
    }

    @Override
    public int addNews(News news) throws SQLException {
        String sql = "insert into news(newsTitle,newsAuthor,content,topicId,picture) values(?,?,?,?,?)";
        PreparedStatement pstm = this.conn.prepareStatement(sql);
        pstm.setString(1, news.getNewstitle());
        pstm.setString(2, news.getNewsauthor());
        pstm.setString(3, news.getContent());
        pstm.setInt(4, news.getTopicId());
        pstm.setString(5, news.getPicture());
        int count = pstm.executeUpdate();
        System.out.println("成功增加新闻：:" + count + "条");
        return count;
    }

    @Override
    public int delNewsByNewsId(Integer newsId) throws SQLException {
        String sql = "delete from news where newsId=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, newsId);
        int count = pstm.executeUpdate();
        System.out.println("成功删除新闻：:" + count + "条");
        return count;
    }

    @Override
    public List<News> selectNewsBySearch(Map<String, Object> map) throws SQLException {
        StringBuffer sb = new StringBuffer();
        List<News> newsList = new ArrayList<>();
        Integer size = map.size();
        ResultSet rs = null;
        //如果map集合里的参数只有两个，就执行这里的代码，因为我默认没搜索条件
        if (size == 2) {
            String sql = "select newsId,newsTitle,publishDate from news order by publishDate desc limit ?,? ";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            pstm.setInt(1, (Integer) map.get("page"));
            pstm.setInt(2, (Integer) map.get("limit"));
            rs = pstm.executeQuery();
            while (rs.next()) {
                News news = new News();
                news.setNewsid(rs.getLong("newsId"));
                news.setNewstitle(rs.getString("newsTitle"));
                news.setPublishdate(dateToString(rs.getDate("publishDate")));
                newsList.add(news);
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
            PreparedStatement pstm = this.conn.prepareStatement(sb.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                News news = new News();
                news.setNewsid(rs.getLong("newsId"));
                news.setNewstitle(rs.getString("newsTitle"));
                news.setPublishdate(rs.getDate("publishDate"));
                newsList.add(news);
            }
        }
        return newsList;
    }

    @Override
    public Integer selectCountBySearch(Map<String, Object> map1) throws SQLException {
        StringBuffer sb = new StringBuffer();
        Integer count = 0;
        ResultSet rs = null;
        Integer size = map1.size();
        if (size == 0) {
            String sql = "select count(*) from news";
            PreparedStatement pstm = this.conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
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
            PreparedStatement pstm = this.conn.prepareStatement(sb.toString());
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        return count;
    }
}
