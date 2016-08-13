package com.mongodbBlog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongodbBlog.domain.Article;

public interface ArticleRepository extends CustomArticleRepository, MongoRepository<Article, Long> {
	@Query(value = "{}",fields="{'_id':1, 'title':1, 'urlTitle':1, 'date':1, 'user':1, 'topic':1, 'featuredImage':1, 'excerpt':1}" )
	Page<Article> findAll(Pageable pageRequest);

}
