package com.mongodbBlog.service;

import java.util.Date;

public interface SendSimpleEmailService {
	public void ReadyToSendEmail (String[] toAddress, String fromAddress, 
			String subject, String msgBody, Date sentDate, boolean multipart, String inLineID,
			String inLineResource,	String attachmentFilename, String attachmentResource);
}
