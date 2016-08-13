package com.mongodbBlog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public class ArticleRepositoryImpl implements CustomArticleRepository {
	private final MongoOperations mongoOper;
	private final String ARTICLE_COLLECTION = "article";
	
	@Autowired
	public ArticleRepositoryImpl(MongoOperations mongoOper){
		this.mongoOper = mongoOper;
	}
}
