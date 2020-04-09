package com.NewsMs.service.impl;

import com.NewsMs.dao.newsDao;
import com.NewsMs.dao.impl.newsDaoimpl;
import com.NewsMs.entity.News;
import com.NewsMs.entity.Page;
import com.NewsMs.service.newsService;

import java.util.List;

public class newsServiceimpl implements newsService {
	private newsDao newdao=new newsDaoimpl();

	@Override
	public Page selectNewsBypage(Integer page, Integer limit) {
		newsDao newdao=new newsDaoimpl();
		List<News> news=newdao.selectNewsBypage(page,limit);
		Page pages=new Page(page,limit,news.size(),news);
		return pages;
	}

}
