package com.iktpreobuka.emailexampletwo.models;

import java.io.File;

public class MessageWithAttachment {
	
	private String to;
	private String subject;
	private String text;
	private File file; 
	// private File attachment; 
	
	
	public MessageWithAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageWithAttachment(String to, String subject, String text, File file) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.file = file;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	
	

}
