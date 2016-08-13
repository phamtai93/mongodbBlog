package com.mongodbBlog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.mongodbBlog.domain.User;
import com.mongodbBlog.repository.UserRepository;
import com.mongodbBlog.service.LoadProperties;
import com.mongodbBlog.service.SendSimpleEmailService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SendSimpleEmailService sendSimpleEmailService;
	
	@Override
	public User getUserBy_id(String _id){
		User u = new User();
		u = userRepo.getUserBy_id(_id);
		return u;
	}
	
	@Override
	public User getUserByEmail(String email){
		User u = new User();
		u = userRepo.getUserByEmail(email);
		return u;
		
	}
	
	@Override
	public Page<User> findAll(int pageNum, int size){
		PageRequest pageRequest = new PageRequest(pageNum, size, new Sort(Sort.DEFAULT_DIRECTION.ASC,"name"));
		Page<User> page = userRepo.findAll(pageRequest);
		return page;
	}
	
	@Override
	public User getUserByName(String name){
		User u = new User();
		u = userRepo.getUserByName(name);
		return u;
	}
	
	@Override
	public String insertUser(User user){
		Date date = new Date();
		Random rd = new Random();
		int intRandom = rd.nextInt(9001) + 1000; // random từ 1000 -> 10000 
		String raw = "" + user.getName() + intRandom;
		String token = BCrypt.hashpw(raw, BCrypt.gensalt());
		String psw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(psw_hash);
		user.setUrlName(user.getName());// tạm thời cho urlName bằng name, chưa xử lý urlName
		user.setCreated(date);
		user.setRole("user_role");
		user.setAvatar("0");
		user.setToken(token);
		user.setTokenCreated(date);
		user.setEnable(0);
		user.setStatus(1);
		userRepo.save(user);
		
		/* send mail*/
		String [] address ={user.getEmail()};
		sendMailForActivatingAccount(address, user.getName(), token);
		System.out.println("user id: " + user.get_id());
		
		return user.get_id();
	}
	
	@Override
	public String activateAccount(String name, String token){
		User user = userRepo.getUserByNameForActivating(name);
		String tokenFromDB = "";
		try{
			tokenFromDB = user.getToken();
			if(tokenFromDB.equals("") || tokenFromDB == null){
				return "invalid-name";
			}
		}
		catch(NullPointerException n){
			return "invalid-name"; // tên không tồn tại
		}
		
		if(user.getEnable()!=0){
			return "url-broken"; // url token đã truy cập rồi, account đã được hoạt rồi
		}
		
		if(user.getStatus()!=1){
			return "non-active-account"; // account bị khóa hoặc xóa
		}
		
		if(!token.equals(tokenFromDB)){
			return "invalid-token"; // token không khớp
		}
		else{
			//...
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("enable", 1);
			map.put("status", 1);
			try{
				userRepo.updateFirstUserByName(name, map);
				return "activated";
			}
			catch(Exception e){
				return "error";
			}
			
		}
		
		//return "";
	}
	
	@Override
	public String saveDateTimeLogin(String name){
		Date date = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		//String dateString = sdf.format(date);
		Map<String,Object> map = new HashMap<String,Object>();
		DateTime result = new DateTime(date,DateTimeZone.UTC); // đã thay đổi dateString --> date
		map.put("lastLogin", result.toDate());
		try{
			System.out.println("Name update : " + name);
			userRepo.updateFirstUserByName(name, map);
		}
		catch(Exception e){
			System.out.println("error" + e.getMessage());
			return "fail";
			
		}
		
		return "success";
	}
	
	@Override
	public String saveChangeUserInfo(String name, Map<String, Object> map){
		System.out.println(map.toString());
		try{
			userRepo.updateFirstUserByName(name, map);
		}
		catch(Exception ex){
			System.out.println("error at saveChangeUserInfo UserServiceImpl : " + ex);
			return "fail";
		}
		return "success";
	}
	
	@Override
	public void sendMailForActivatingAccount(String [] address, String username, String token){
		/* send mail*/
		String rootUrl = (new LoadProperties().getRootUrl());
		String content = "<h4>mongodbBlog:</h4>  <h3><i>Thanks for registering ! </i></h3> "
				+ "<p>Click to this link to active your account (or you can copy this link, paste into browser and press enter) :</p>"
				+ "<p><a href='" 
				+ rootUrl + "active_user.html?username=" 
				+ username + "&token=" + token +"' + target='_blank' >" 
				+ rootUrl + "active_user.html?username=" 
				+ username + "&token=" + token +"</a> </p>" ;
		Date date = new Date();
		String subject = "Active your account ";
		String fromAddress = "greenapp4u@gmail.com";
		boolean multipart = false;
		sendSimpleEmailService.ReadyToSendEmail(address, fromAddress, subject, content, date, multipart,"","","","");
		
	}
}
