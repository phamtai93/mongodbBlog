package com.mongodbBlog.repository;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodbBlog.domain.User;

public class UserRepositoryImpl implements CustomUserRepository{
	
	private final MongoOperations mongoOper;
	private final String USER_COLLECTION = "user";
	@Autowired
	public UserRepositoryImpl(MongoOperations mongoOper){
		this.mongoOper = mongoOper; 
	}
	//writing custom method access database 
	
	@Override
	public User getUserBy_id(String _id){
		Query query = new Query(Criteria.where("_id").is(_id));
		return this.mongoOper.findOne(query, User.class, USER_COLLECTION);
	}
	
	@Override
	public User getUserByEmail(String email){
		Query query = new Query(Criteria.where("email").is(email));
		return this.mongoOper.findOne(query, User.class, USER_COLLECTION);
	}
	
	@Override
	public void saveUser(User user){
		this.mongoOper.save(user, USER_COLLECTION);
	}
	
	@Override
	public User getUserByName(String name){
		Query query = new Query(Criteria.where("name").is(name));
		return this.mongoOper.findOne(query, User.class,USER_COLLECTION);
	}
	
	@Override
	public User getUserByNameForActivating(String name){
		Query query = new Query(Criteria.where("name").is(name));
		return this.mongoOper.findOne(query, User.class,USER_COLLECTION);
	}
	
	@Override
	public void updateFirstUserByName(String name, Map<String, Object> map){
		Query query = new Query(Criteria.where("name").is(name));
		Update update = new Update();
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()){
			String key = it.next();
			update.set(key, map.get(key));
		}
		//System.out.println("update: "+update.toString());
		this.mongoOper.updateFirst(query, update, USER_COLLECTION);
	}
}
