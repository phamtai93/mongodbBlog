package com.mongodbBlog.repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
//import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;


public class CommentRepositoryImpl implements CustomCommentRepository {
	private final MongoOperations mongoOper;
	private final String COMMENT_COLLECTION = "comment";
	@Autowired
	public CommentRepositoryImpl(MongoOperations mongoOper){
		this.mongoOper = mongoOper; 
	}
	
	public List<String> CountCommentsOfTheArticle(String _id){
		System.out.println("I'm CountCommentsOfTheArticle method at CommentRepositoryImpl");
		System.out.println("_id : " + _id);
		ObjectId objectId = new ObjectId(_id);
		Aggregation agg = newAggregation(
				match(Criteria.where("discussion_id").is(objectId)),
				project("_id").and("comments").size().as("count")//,sort(Sort.Direction.ASC, "date"),
				);
		AggregationResults<String> groupResults 
		=  mongoOper.aggregate(agg, COMMENT_COLLECTION,String.class);
	List<String> result = groupResults.getMappedResults();
	
		return result;
	}
}
