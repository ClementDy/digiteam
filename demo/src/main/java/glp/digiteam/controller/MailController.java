package glp.digiteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.MailException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import glp.digiteam.services.NotificationService;

@EnableAutoConfiguration
@RestController
@ComponentScan({"glp.digiteam.services","glp.digiteam.entity.offer"})
public class MailController {

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = "/mail")
	public ModelAndView mail(Model model,HttpSession session) {
		System.out.println("Envoi de mails...........");
		try{
			notificationService.sendNotification();
		}
		catch(MailException e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:offers");

	}
}