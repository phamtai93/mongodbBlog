package com.mongodbBlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodbBlog.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, Long>, CustomCommentRepository{

}
