package com.mongodbBlog.repository;

import java.util.Map;

import org.springframework.data.mongodb.repository.Query;

import com.mongodbBlog.domain.User;

public interface CustomUserRepository {
	
	//listing custom method access database 
	public User getUserBy_id(String _id);
	public User getUserByEmail(String email);
	public void saveUser(User user);
	public User getUserByName(String name);
	@Query(value="{}",fields="{'_id':1, 'name':1, 'token':1, 'tokenCreated':1, 'enable':1, 'status':1}")
	public User getUserByNameForActivating(String name);
	public void updateFirstUserByName(String name, Map<String, Object> map);
}
