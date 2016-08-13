package com.mongodbBlog.domain;

public class Topic {
	private String _id;
	private String name;
	private String urlName;
	private String describe;
	private int sumArtical;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getSumArtical() {
		return sumArtical;
	}
	public void setSumArtical(int sumArtical) {
		this.sumArtical = sumArtical;
	}
	
	
}
