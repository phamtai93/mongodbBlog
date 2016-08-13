package com.mongodbBlog.service;

import org.springframework.data.domain.Page;

import com.mongodbBlog.domain.Article;

public interface ArticleService {
	
	public Page<Article> findAll(int pageNum, int size);
}
