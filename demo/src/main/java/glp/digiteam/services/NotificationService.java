package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender){
		this.javaMailSender=javaMailSender;
	}
	
	public void sendNotification() throws MailException{
		//send email
		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setTo("dilly.clement@gmail.com");
		mail.setFrom("projetglp2017@gmail.com");
		mail.setSubject("Projet GLP");
		mail.setText("Bonjour Clément, je suis ton test");
		
		javaMailSender.send(mail);
	}
}
