package com.mongodbBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodbBlog.domain.Topic;
import com.mongodbBlog.repository.TopicRepository;

public class TopicServiceImpl implements TopicService {
	
	@Autowired
	public TopicRepository topicRepo;
	@Override
	public List<Topic> getAllTopic() {
		
		return topicRepo.toGetallTopic();
	}
}
