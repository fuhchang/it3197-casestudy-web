package com.example.CommunityOutreach.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;



public class ArticleEMain {
	private static final String username = "IT3161GeoVizAssignment@gmail.com";
	private static final String password = "nypsitkeith";
	private static Properties properties = null;
	private static Session session = null;

	private static ArticleEMain email = null;
	
	private ArticleEMain(){
		
	}
	
	public static ArticleEMain getInstance(){
		if(email == null){
			setUp();
			email = new ArticleEMain();
		}
		return email;
	}
	
	private static void setUp() {
		properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	//	properties.put("mail.smtp.host", "smtp.live.com");
	//	properties.put("mail.smtp.port", "25");
		session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public boolean send(String from, String to, String subject, String text) {

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);
			
			return true;

		} catch (MessagingException e) {
			return false;
			//throw new RuntimeException(e);
		}
	}
}

