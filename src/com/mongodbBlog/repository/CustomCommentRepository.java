package com.mongodbBlog.repository;

import java.util.HashMap;
import java.util.List;

public interface CustomCommentRepository {
	public List<String> CountCommentsOfTheArticle(String _id);
}
