package com.NewsMs.dao;

import java.util.List;

import com.NewsMs.entity.News;

public interface newsDao {

	List<News> selectNewsBypage(Integer page, Integer limit);

}
