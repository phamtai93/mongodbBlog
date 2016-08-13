package com.mongodbBlog.service;

import java.util.HashMap;

public interface CommentService {
	public HashMap<String, Object> CountCommentsOfTheArticle(String _id);
}
