package com.NewsMs.service;

import java.util.List;

import com.NewsMs.entity.News;

public interface newsService {
	//获取新闻列表，只含标题和文章id
	List<News> selectNewsBypage(Integer page, Integer limit);
	
	
}
