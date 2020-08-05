package com.app.aliexpress.common.util;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	private String platform;
	private String id;
	private String password;
	private String address;
	private Session session;
	private Transport transport;
	
	public MailSender() { }
	
	public MailSender(Map<String, Object> map) throws Exception {
		
		// Step1
		platform = (String) map.get("emailPlatform") != null ? (String) map.get("emailPlatform") : (String) map.get("SERVER_EMAIL_PLATFORM"); 
		id = (String) map.get("emailId") != null ? (String) map.get("emailId") : (String) map.get("SERVER_EMAIL_ID");
		password = (String) map.get("emailPassword") != null ? (String) map.get("emailPassword") : (String) map.get("SERVER_EMAIL_PASSWORD");
		address = id + "@" + platform + ".com";
		
		Properties mailProps = new Properties();
		
		mailProps.put("mail.smtp.host", "smtp." + platform + ".com");
		mailProps.put("mail.smtp.port", "587");
		mailProps.put("mail.smtp.auth", "true");
		mailProps.put("mail.smtp.starttls.enable", "true");
		
		// Step2
		Authenticator auth = null;
		
		if (platform.equals("naver")) {
			auth = new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(id, password);
				}};
				
		} else if (platform.equals("gmail")) {
			auth = new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(address, password);
				}};
		}
		
		session = Session.getInstance(mailProps, auth);
		
		// Step3
		transport = session.getTransport("smtp");
		
	}
	
	public void connect() throws Exception {
		transport.connect();
	}
	
	public void disconnect() throws Exception {
		transport.close();
	}

	public void sendMessage(Map<String, Object> map) throws Exception {
		String recipient = (String) map.get("contactEmail");
		String title = (String) map.get("contactReplyTitle");
		String content = (String) map.get("contactReplyContent");
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(address));
		
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.setSubject(title);
		message.setContent(content, "text/html; charset=UTF-8");
		
		transport.sendMessage(message, message.getAllRecipients());
	}
}
