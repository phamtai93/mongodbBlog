package com.mongodbBlog.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.mongodbBlog.domain.User;

public interface UserService {
	public User getUserBy_id(String _id);
	public User getUserByEmail(String email);
	public User getUserByName(String name);
	public Page<User> findAll(int pageNum, int size);
	public String insertUser(User user);
	public String activateAccount(String name, String token);
	public String saveDateTimeLogin(String name);
	public String saveChangeUserInfo(String name, Map<String, Object> map);
	public void sendMailForActivatingAccount(String [] address, String username, String token);
}
