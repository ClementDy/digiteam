package glp.digiteam.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import glp.digiteam.entity.offer.ServiceEntity;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Student;
import glp.digiteam.repository.OfferRepository;
import glp.digiteam.services.OfferService;
import glp.digiteam.services.ServiceService;
import glp.digiteam.services.StaffLille1Service;
import glp.digiteam.services.StudentService;


@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class AdministratorController {

	@Autowired 
	private ServiceService serviceService;


	@Autowired 
	private OfferService offerService;

	@Autowired
	private StaffLille1Service staffLille1Service;

	@Autowired
	private StudentService studentService;

	@Autowired
	private JavaMailSender javaMailSender;


	@RequestMapping(value = "/gestionModerator", method = RequestMethod.GET)
	public String gestionModerator(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");

		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		Iterable<StaffLille1> gestionnaires = staffLille1Service.findAll();
		Iterable<ServiceEntity> services = serviceService.findAll();
		model.addAttribute("gestionnaires",gestionnaires);

		StaffLille1 gestionnaire=new StaffLille1();
		model.addAttribute("newModerator",gestionnaire);
		model.addAttribute("service",services);
		model.addAttribute("user",user);

		return "administrator/gestionModerator";
	}

	@RequestMapping(value = "/gestionReferent", method = RequestMethod.GET)
	public String gestionReferent(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		Iterable<StaffLille1> referents = staffLille1Service.findAll();
		Iterable<ServiceEntity> services = serviceService.findAll();
		model.addAttribute("referents",referents);

		StaffLille1 referent=new StaffLille1();
		model.addAttribute("newReferent",referent);
		model.addAttribute("service",services);
		model.addAttribute("user",user);

		return "administrator/gestionReferent";
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String stats(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		
		int offrePublished=0;
		offrePublished=offerService.getNbOfferPublished();
		int offreRefused=offerService.getNbOfferRefused();
		int offreWaiting=offerService.getNbOfferWaiting();
		int offrePassed=offerService.getNbOfferPassed();
		int nbEtudiant=studentService.nbStudent();
		int nbLille1=staffLille1Service.nbLille1();

		model.addAttribute("nbUser",nbEtudiant+nbLille1);
		model.addAttribute("nbLille1",nbLille1);
		model.addAttribute("nbEtudiant",nbEtudiant);
		model.addAttribute("offrePubliees",offrePublished);
		model.addAttribute("offreRefused",offreRefused);
		model.addAttribute("offreWaiting",offreWaiting);
		model.addAttribute("offrePassed",offrePassed);
		
		model.addAttribute("user",user);

		return "administrator/statistics";
	}
	
	@RequestMapping(value = "/nextYear", method = RequestMethod.GET)
	public String nextYear(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");		
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);

		Iterable<Student> students=studentService.findPublishedCandidature();
		for(Student student:students){
			SimpleMailMessage mail= new SimpleMailMessage();
			mail.setTo(student.getEmail());
			mail.setSubject("Nouvelle année scolaire, mettez votre profil à jour");
			mail.setText("Bonjour "+student.getFirstName()+", veuillez actualiser votre candidature sur le site : http://172.28.2.17:8585 ou celle-ci sera dépubliée");
			javaMailSender.send(mail);
		}
		return "administrator/nextYear";

	}

	@RequestMapping(value = "/gestionReferent", method = RequestMethod.POST)
	public String gestionReferent(
			@RequestParam(value="service", required=true,defaultValue="") String service,
			Model model, HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		ArrayList<StaffLille1> referents;
		if(service.equals(" ")){
			referents= (ArrayList<StaffLille1>) staffLille1Service.findAll();
		}
		else{
			referents = (ArrayList<StaffLille1>) staffLille1Service.findByService(service);
		}
		model.addAttribute("referents",referents);

		Iterable<ServiceEntity> services = serviceService.findAll();
		model.addAttribute("referents",referents);

		StaffLille1 referent=new StaffLille1();
		model.addAttribute("size",referents.size()/2);
		model.addAttribute("newReferent",referent);
		model.addAttribute("service",services);
		model.addAttribute("user",user);
		return "administrator/gestionReferent";

	}

	@RequestMapping(value = "/homeStaffLille1", method = RequestMethod.GET)
	public String getHomeStaffLille1(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		System.out.println(user.getFirstName());
		model.addAttribute("user",user);
		return "homeStaffLille1";
	}

	@RequestMapping(value = "/deleteModerator", method = RequestMethod.GET)
	public ModelAndView deleteModerator(Model model,HttpSession session,@RequestParam(value="name",required=true)String name,@RequestParam(value="mode",required=true)String mode) {

		StaffLille1 user= staffLille1Service.findByEmail(mode);
		StaffLille1 gestionnaire= staffLille1Service.findByEmail(name);
		System.out.println("GGGG"+gestionnaire.getEmail());

		gestionnaire.setModerator(false);
		model.addAttribute("user",user);
		staffLille1Service.saveStaffLille1(gestionnaire);

		return new ModelAndView("redirect:gestionModerator");
	}

	@RequestMapping(value = "/deleteReferent", method = RequestMethod.GET)
	public ModelAndView deleteReferent(Model model,HttpSession session,@RequestParam(value="mode",required=true)String mode,@RequestParam(value="name",required=true)String name) {
		StaffLille1 user= staffLille1Service.findByEmail(mode);
		StaffLille1 referent= staffLille1Service.findByEmail(name);

		referent.setReferent(false);
		model.addAttribute("user",user);
		staffLille1Service.saveStaffLille1(referent);
		return new ModelAndView("redirect:gestionReferent");
	}

	@RequestMapping(value = "/newModerator", method = RequestMethod.POST)
	public ModelAndView newModerator(@ModelAttribute StaffLille1 staffLille1) {
		StaffLille1 gestionnaire;
		if(staffLille1Service.findByEmail(staffLille1.getEmail())!=null){
			gestionnaire=staffLille1Service.findByEmail(staffLille1.getEmail());
			System.out.println(gestionnaire.getFirstName());
			gestionnaire.setModerator(true);
			if(gestionnaire.isReferent==true){
				gestionnaire.setReferent(false);
			}
			staffLille1Service.saveStaffLille1(gestionnaire);
		}
		else{
			gestionnaire=new StaffLille1();
			gestionnaire.setEmail(staffLille1.getEmail());
			gestionnaire.setModerator(true);
			staffLille1Service.saveStaffLille1(gestionnaire);
		}

		return new ModelAndView("redirect:gestionModerator");
	}

	@RequestMapping(value = "/newReferent", method = RequestMethod.POST)
	public ModelAndView newReferent(@ModelAttribute StaffLille1 staffLille1) {
		StaffLille1 referent;
		System.out.println(staffLille1.getEmail());
		if(staffLille1Service.findByEmail(staffLille1.getEmail())!=null){
			referent=staffLille1Service.findByEmail(staffLille1.getEmail());
			referent.setReferent(true);
			referent.setService(staffLille1.getService());
			if(referent.isModerator==true){
				referent.setModerator(false);
			}
			staffLille1Service.saveStaffLille1(referent);
		}
		else{

			referent=new StaffLille1();
			referent.setEmail(staffLille1.getEmail());
			referent.setReferent(true);
			referent.setService(staffLille1.getService());
			staffLille1Service.saveStaffLille1(referent);
		}		

		return new ModelAndView("redirect:gestionReferent");
	}
}
