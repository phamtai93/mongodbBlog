package com.mongodbBlog.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodbBlog.domain.Topic;

public class TopicRepositoryImpl implements CustomTopicRepository{
	
	private final MongoOperations mongoOper;
	private final String TOPIC_COLLECTION = "topic";
	
	@Autowired
	public TopicRepositoryImpl (MongoOperations mongoOper){
		this.mongoOper = mongoOper;
	}
	
	@Override
	public List<Topic> toGetallTopic(){
		return mongoOper.findAll(Topic.class, TOPIC_COLLECTION);
	}
}
