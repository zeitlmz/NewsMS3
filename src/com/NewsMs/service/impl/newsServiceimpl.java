package com.NewsMs.service.impl;

import java.util.List;

import com.NewsMs.dao.newsDao;
import com.NewsMs.dao.impl.newsDaoimpl;
import com.NewsMs.entity.News;
import com.NewsMs.service.newsService;

public class newsServiceimpl implements newsService {

	@Override
	public List<News> selectNewsBypage(Integer page, Integer limit) {
		newsDao newdao=new newsDaoimpl();
		return newdao.selectNewsBypage(page,limit);
	}

}
