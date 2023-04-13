package com.iktpreobuka.emailexampletwo.services;

import java.io.File;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.emailexampletwo.models.EmailObject;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender emailSender;

	@Override
	public void sendSimpleEmailMessage(EmailObject emailObject) {

		// pravimo novu poruku tipa SimpleMailMessage
		SimpleMailMessage mail = new SimpleMailMessage();
		// kome šaljemo tu poruku? To smo dobili od emailObject, što je klasa za slanje podataka, kroz getTo
		mail.setTo(emailObject.getTo());
		mail.setSubject(emailObject.getSubject());
		mail.setText(emailObject.getText());
		// da bismo poslali mejl, treba nam servis JavaMailSender, koji ubacujemo kroz Autowired
		emailSender.send(mail);
	}

	@Override
	public void sendTemplateMessage(EmailObject object) throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		String text = "<html><body><table" + "style='border:2px solid black'>" + "<tr><td>" + object.getText()
				+ "</td></tr>" + "</table></body></html>";
		helper.setText(text, true);
		emailSender.send(mail);
	}

//	@Override
//	public void sendMessageWithAttachment(EmailObject emailObject, String pathToAttachment) throws IOException {
//		MessageWithAttachment mailAttachment = new MessageWithAttachment();
//		mailAttachment.setTo(emailObject.getTo());
//		mailAttachment.setSubject(emailObject.getSubject());
//		mailAttachment.setText(emailObject.getText());
//		mailAttachment.setFile(emailObject.getFile());
//		// emailSender.send(mailAttachment);
//	}

	@Override
	public void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(object.getTo());
		helper.setSubject(object.getSubject());
		helper.setText(object.getText(), false);
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment(file.getFilename(), file);
		emailSender.send(mail);
	}

}