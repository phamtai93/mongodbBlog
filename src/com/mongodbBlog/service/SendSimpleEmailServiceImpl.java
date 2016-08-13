package com.mongodbBlog.service;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class SendSimpleEmailServiceImpl implements SendSimpleEmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void ReadyToSendEmail(String[] toAddress, String fromAddress, String subject, String msgBody, Date sentDate,
			boolean multipart, String inLineID, String inLineResource, String attachmentFilename,
			String attachmentResource) {
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {  

            public void prepare(MimeMessage mimeMessage) throws Exception {
            	//mimeMessage.setContent(msgBody, "text/html");
            	MimeMessageHelper message = new MimeMessageHelper(mimeMessage, multipart, "UTF-8");
            	
	            message.setTo(toAddress);
	            message.setFrom(fromAddress);
	            message.setSubject(subject);
	            message.setText(msgBody,true);
	            message.setSentDate(sentDate);
//                message.addInline("myLogo", new ClassPathResource("img/mylogo.gif"));
//                message.addAttachment("myDocument.pdf", new ClassPathResource("doc/myDocument.pdf"));
            }  
		};  
		javaMailSender.send(messagePreparator);

	}

}
