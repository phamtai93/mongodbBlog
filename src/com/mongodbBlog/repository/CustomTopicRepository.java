package com.mongodbBlog.repository;
import java.util.List;
import com.mongodbBlog.domain.Topic;

public interface CustomTopicRepository {
	public List<Topic> toGetallTopic();
}
