package com.mongodbBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mongodbBlog.domain.Article;
import com.mongodbBlog.repository.ArticleRepository;

public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleRepository articleRepo;
	
	public Page<Article> findAll(int pageNum, int size){
		Pageable pageRequest = new PageRequest(pageNum, size, new Sort(Sort.DEFAULT_DIRECTION.ASC,"date"));
		Page<Article> page = articleRepo.findAll(pageRequest);
		return page;
	}
}
