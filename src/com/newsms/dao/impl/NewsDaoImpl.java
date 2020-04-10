package com.newsms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newsms.dao.NewsDao;
import com.newsms.dao.baseDao;
import com.newsms.entity.News;

/**
 * @author lmz
 */
public class NewsDaoImpl extends baseDao implements NewsDao {

	@Override
	public List<News> selectNewsBypage(Integer page, Integer limit) {
		String sql = "select newsId,newsTitle from news limit ?,?";
		Object[] parem = { (page - 1) * limit, limit };
		ResultSet rs = executeQuery(sql, parem);
		List<News> list = new ArrayList<>();
		try {
			while (rs.next()) {
				long newsid = rs.getLong("newsId");
				String newstitle = rs.getString("newsTitle");
				News news = new News();
				news.setNewsid(newsid);
				news.setNewstitle(newstitle);
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int selectCount() {
		String sql="select count(*) from news";
		ResultSet rs=executeQuery(sql);
		int count = 0;
		try {
			rs.next();
			count=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
