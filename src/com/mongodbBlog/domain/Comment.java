package com.mongodbBlog.domain;

import java.util.Date;

public class Comment {
//_id, user, date, content
	public String _id;
	public String discussion_id; 
	public User user ;
	public Date date;
	public String content;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getDiscussion_id() {
		return discussion_id;
	}

	public void setDiscussion_id(String discussion_id) {
		this.discussion_id = discussion_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
