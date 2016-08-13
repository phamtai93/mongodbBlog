package com.mongodbBlog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodbBlog.repository.CommentRepository;

public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepo;
	
	public HashMap<String, Object> CountCommentsOfTheArticle(String _id){
		List<String> list = commentRepo.CountCommentsOfTheArticle(_id);
		HashMap<String,Object> hm = new HashMap<String,Object>();
		ObjectMapper obm = new ObjectMapper();
		
		String oneString = list.get(0); //// cause inside list only contain 1 String
		//Iterator<String> it= list.iterator();
		//while(it.hasNext()){
			try{
				hm = obm.readValue(oneString, new TypeReference<HashMap<String, Object>>(){});
			}
			catch(Exception ex){
				System.out.println("ERROR : convert JSON String to HashMap...");
			}
		//}
		return hm;
	}
}
