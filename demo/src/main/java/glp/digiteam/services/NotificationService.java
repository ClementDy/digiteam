package glp.digiteam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Student;

@Service
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
			if(student.getEmail().equals("clement.dilly@etudiant.univ-lille1.fr") || student.getEmail().equals("willy.pezant@etudiant.univ-lille1.fr") ){
				System.out.println("send mail to "+ student.getEmail());
				SimpleMailMessage mail= new SimpleMailMessage();
				mail.setTo(student.getEmail());
				mail.setSubject("Nouvelle offre en ligne ["+offre.getTitle()+"]");
				mail.setText("Bonjour "+student.getFirstName()+",\n\nToujours à la recherche d'un contrat étudiant?\nBonne nouvelle, une nouvelle offre vient d'être "
						+ "publiée par le service : "+offre.getReferent().getService().getLibelle()+".\n\nTitre : "+offre.getTitle()+"\nMission : "+offre.getMission()
								+ "\nRémuneration : "+offre.getRemuneration()+" € de l'heure\nCompétences : "+offre.getSkills() 
								+ "\n\nSi cette offre t'intéresse, tu peux contacter:\n"+offre.getFirstNameResponsible()+" "+offre.getLastNameResponsible()+ " à l'adresse suivante "
								+ offre.getEmailResponsible()+" ou par téléphone au : "+offre.getPhoneResponsible()+"\n\nCette offre expire le "+offre.getValidityDate()
								+"\n\nCordialement,\n\nL'équipe Digiteam.\n\nCeci est un message automatique, merci de ne pas y répondre.\n"
								+ "Pour ne plus recevoir ses notifications, veuillez vous désinscrire de l'abonnement par mail sur le site : http://172.28.2.17:8585");
						

				javaMailSender.send(mail);
			}
		}
	}
	
	public void sendNotificationAcceptOffer(AbstractOffer offre) throws MailException{

		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setTo(offre.getEmailResponsible());
		mail.setSubject("Acception de votre offre n°"+ offre.getId());
		mail.setText("Bonjour "+offre.getFirstNameResponsible()+", \n\nNous avons le plaisir de vous informer que votre offre n° "+offre.getId()+" - "+offre.getTitle()+" a été acceptée."
				+ "\n\n Elle est désormais en ligne, si vous souhaitez retirer cette offre du marché de l'université, merci de vous rendre sur "
				+ "cette page : http://172.28.2.17:8585/offers\n\nPour rappel, cette offre expire le "+offre.getValidityDate().getDay()+"/"+offre.getValidityDate().getMonth()+"."
				+ "\n\nCordialement,\n\nL'équipe Digiteam.\n\nCeci est un message automatique, merci de ne pas y répondre.\n");

		javaMailSender.send(mail);
	}
	
	public void sendNotificationRefuseOffer(AbstractOffer offre,StaffLille1 referent) throws MailException{
		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setTo(referent.getEmail());
		mail.setSubject("Refus de votre offre n°"+ offre.getId());
		mail.setText("Bonjour"+referent.getFirstName()+", \n\nNous avons le regret de vous informer que votre offre a été refusée.");

		javaMailSender.send(mail);
	}
}
