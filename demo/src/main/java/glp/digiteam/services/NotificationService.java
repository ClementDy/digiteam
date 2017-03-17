package glp.digiteam.services;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;

@Service
@ComponentScan({ "glp.digiteam.services", "glp.digiteam.entity.offer" })
public class NotificationService {

	@Autowired
	private StudentService studentService;

	private JavaMailSender javaMailSender;

	@Autowired
	public NotificationService(JavaMailSender javaMailSender){
		this.javaMailSender=javaMailSender;
	}

	public void sendNotification() throws MailException{
		//send email
		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setTo("alexandre3.lefebvre@etudiant.univ-lille1.fr");
		mail.setSubject("Projet GLP");
		mail.setText("Bonjour Michel, je suis ton test");

		javaMailSender.send(mail);
	}


	public void sendNotificationNewOffer(AbstractOffer offre) throws MailException{
		List<Student> students=studentService.getAllCandidature();
		for(Student student:students){

			if(student.getEmailSubscribe()==1){
				for (Mission mission : student.getWish().getMissions()) {
					if(mission.getId()==Long.parseLong(offre.getType())){
						SimpleMailMessage mail= new SimpleMailMessage();
						mail.setTo(student.getEmail());
						mail.setSubject("Nouvelle offre en ligne ["+offre.getTitle()+"]");
						mail.setText("Bonjour "+student.getFirstName()+",\n\nToujours à la recherche d'un contrat étudiant?\nBonne nouvelle, une nouvelle offre vient d'être "
								+ "publiée par le service : "+offre.getReferent().getService().getLibelle()+".\n\nTitre : "+offre.getTitle()+"\nMission : "+offre.getMission()
								+ "\nRémuneration : "+offre.getRemuneration()+" € de l'heure\nCompétences : "+offre.getSkills() 
								+ "\n\nSi cette offre t'intéresse, tu peux contacter:\n"+offre.getFirstNameResponsible()+" "+offre.getLastNameResponsible()+ " à l'adresse suivante "
								+ offre.getEmailResponsible()+" ou par téléphone au : "+offre.getPhoneResponsible()+"\n\nCette offre expire le "+offre.getValidityDate()
								+"\n\nCordialement,\n\nL'équipe Digiteam.\n\nCeci est un message automatique, merci de ne pas y répondre.\n"
								+ "Pour ne plus recevoir ces notifications, veuillez vous désinscrire de l'abonnement par mail sur le site : http://172.28.2.17:8585");


						javaMailSender.send(mail);
					}
				}

			}
		}
	}

	public void sendNotificationAcceptOffer(AbstractOffer offre) throws MailException{

		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setTo(offre.getEmailResponsible());
		mail.setSubject("Offre n°"+ offre.getId()+" acceptée!");
		mail.setText("Bonjour "+offre.getFirstNameResponsible()+", \n\nNous avons le plaisir de vous informer que votre offre n° "+offre.getId()+" - "+offre.getTitle()+" a été acceptée."
				+ "\n\n Elle est désormais en ligne, si vous souhaitez retirer cette offre du marché de l'université, merci de vous rendre sur "
				+ "cette page : http://172.28.2.17:8585/offers\n\nPour rappel, cette offre expire le "+offre.getValidityDate().getDay()+"/"+offre.getValidityDate().getMonth()+"."
				+ "\n\nCordialement,\n\nL'équipe Digiteam.\n\nCeci est un message automatique, merci de ne pas y répondre.\n");

		javaMailSender.send(mail);
	}

	public void sendNotificationRefuseOffer(AbstractOffer offre,StaffLille1 referent) throws MailException, MessagingException{
		System.out.println("Refus d'une offre");
		
		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setTo(offre.getEmailResponsible());
		mail.setSubject("Offre n°"+ offre.getId()+" refusée!");
		mail.setText("Bonjour"+referent.getFirstName()+", \n\nNous avons le regret de vous informer que votre offre a été refusée."
				+"\n\nLa raison de ce refus est la suivante : "+offre.getComment()+"."
				+ "\n\nCordialement,\n\nL'équipe Digiteam.\n\nCeci est un message automatique, merci de ne pas y répondre.\n");

		javaMailSender.send(mail);





	
	}

	/*
		templateEngine.addTemplateResolver(templateResolver);
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setOrder(Integer.valueOf(2));
		templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
		templateResolver.setPrefix("/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(false);
		Context ctx = new Context();
		ctx.setVariable("name", offre.getFirstNameResponsible());

		String htmlContent = templateEngine.process("templateMail", ctx);
	
		javaMailSender.send(mimeMessage);
		
				final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
		message.setTo(referent.getEmail());
		message.setSubject("Refus de votre offre n°"+ offre.getId());
		//javaMailSender.send(mail);
	 */
}
