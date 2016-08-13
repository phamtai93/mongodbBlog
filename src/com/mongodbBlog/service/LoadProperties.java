package com.mongodbBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mongodbBlog.service.LoadProperties;

@Component("loadProperties")
@Scope("singleton")
public class LoadProperties {
	private static String rootUrl;
	private static String urlDefaultAvatar;
	private static String resource_path_image;
	private static String resource_path_uploadedfile;
	
	@Autowired
	public LoadProperties(
			@Value("${ROOT_URL}") String rootUrl, 
			@Value("${URL_DEFAULT_AVATAR}") String urlDefaultAvatar,
			@Value("${RESOURCE_PATH_AVATAR}") String resource_path_avatar,
			@Value("${RESOURCE_PATH_IMAGE}") String resource_path_image,
			@Value("${RESOURCE_PATH_SOURCECODE}") String resource_path_uploadedfile) {
		LoadProperties.rootUrl = rootUrl;
		LoadProperties.urlDefaultAvatar = urlDefaultAvatar;
		LoadProperties.resource_path_image = resource_path_image;
		LoadProperties.resource_path_uploadedfile = resource_path_uploadedfile;
	 }
	 public LoadProperties(){}
	 
	 public String getRootUrl(){
		 return rootUrl;
	 }
	 
	 public String getUrlDefaultAvatar(){
		 return urlDefaultAvatar;
	 }
	 
	 public String getResourcePathImage(){
		 return resource_path_image;
	 }
	 public String getResourcePathUploadedFile(){
		 return resource_path_uploadedfile;
	 }
	
}
