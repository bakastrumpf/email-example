package com.iktpreobuka.emailexampletwo.services;

import java.io.IOException;

import models.EmailObject;

public interface EmailService {
	
	public void sendSimpleEmailMessage(EmailObject emailObject);
	public void sendMessageWithAttachment(EmailObject emailObject, 
			String pathToAttachment) throws IOException, Exception;
	void sendTemplateMessage(EmailObject object) throws Exception;

}
