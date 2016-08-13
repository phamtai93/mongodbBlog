package com.mongodbBlog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongodbBlog.domain.User;

public interface UserRepository extends MongoRepository<User, Long>, CustomUserRepository{
	//public User getUserBy_id(String _id);
	Page<User> findAll(Pageable pageRequest);
}
