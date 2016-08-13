package com.mongodbBlog.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.mongodbBlog.domain.Topic;

public interface TopicRepository extends CustomTopicRepository, MongoRepository<Topic, Long>{
	//public List<Topic> toGetallTopic();
}
