package com.NewsMs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.NewsMs.dao.baseDao;
import com.NewsMs.dao.newsDao;
import com.NewsMs.entity.News;

public class newsDaoimpl extends baseDao implements newsDao {

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
				News news = new News(newsid, newstitle);
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
