package com.bus.vo;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public MailSender(String name, String firstemail, String pwd) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("busprogram2022@gmail.com", "wgajnkqxczskmndl");
			}
		});

		String receiver = firstemail+"@gmail.com"; // 메일 받을 주소
		String title = "안녕하세요 버스 예매 프로그램 서비스 입니다.";
		String content = "<h1 style='color:blue'>"+name+"님 버스 예매 시스템을 이용해 주셔서 감사 합니다!</h2>"
				+ " <h3 style='color:black'>"+name+"님이 찾으시려는 비밀번호는 "+pwd+"입니다.<h3><br>"
				+ " <h3 style='color:grey'>앞으로도 저희 버스 예매시스템을 많이 이용부탁드립니다!<h3>";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("busprogram2022@gmail.com", "버스예매 관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MailSender mail = new MailSender("이름","yuhwanbeom","버스 프로그램 비번");
	}
}
