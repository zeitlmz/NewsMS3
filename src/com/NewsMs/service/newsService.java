package com.NewsMs.service;

import com.NewsMs.entity.Page;

public interface newsService {
	/**
	 * 获取新闻列表，只含标题和文章id
	 * @param page 当前页
	 * @param limit 每页数量
	 * @return 分页对象
	 */
	Page selectNewsBypage(Integer page, Integer limit);
	
	
}
