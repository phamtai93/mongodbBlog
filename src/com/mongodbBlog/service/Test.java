package com.mongodbBlog.service;

public class Test extends TestC{

	
	public static void main (String a[]){
		TestC tc =  new TestC();
		System.out.println(tc.cc());
		TestA tA2 = new TestC();
		System.out.println(tA2.aa());
		TestB tb =new TestC();
		System.out.println(tb.aa());
		
	}
}
