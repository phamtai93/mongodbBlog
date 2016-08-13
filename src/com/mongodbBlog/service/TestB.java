package com.mongodbBlog.service;

public  class TestB extends TestA {
	String bb(){
		//return "bb";
		return "bb" + super.aa();
	}
	
	

}
