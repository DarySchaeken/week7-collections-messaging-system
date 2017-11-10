package be.pxl.collections.messagingsystem;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import javax.mail.*;
import javax.mail.internet.*;

public class MailService {
	private static MailService mailService = new MailService();
	private Session session;

	private MailService() {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "localhost");
		session = Session.getDefaultInstance(properties);
	}
	
	public static MailService getInstance() {
		return mailService;
	}
	
	public void sendMessage(Message message, Follower follower){
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO , new InternetAddress(follower.getEmail()));
			mimeMessage.setSubject(message.getSubject());
			mimeMessage.setText(message.getContent(follower));
			Transport.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
