package glp.digiteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import glp.digiteam.entity.offer.ServiceEntity;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.repository.ServiceRepository;
import glp.digiteam.repository.StaffLille1Repository;


@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class AdministratorController {


	@Autowired 
	private ServiceRepository serviceRepository;

	@Autowired
	StaffLille1Repository staffLille1Service;

	@Autowired 
	private StaffLille1Repository staffLille1Repository;

	StaffLille1 user;


	@RequestMapping(value = "/gestionModerator", method = RequestMethod.GET)
	public String gestionModerator(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		user=staffLille1Repository.findByEmail(staffLille1.getEmail());

		Iterable<StaffLille1> gestionnaires = staffLille1Repository.findAll();
		Iterable<ServiceEntity> services = serviceRepository.findAll();
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
		user=staffLille1Repository.findByEmail(staffLille1.getEmail());

		Iterable<StaffLille1> referents = staffLille1Repository.findAll();
		Iterable<ServiceEntity> services = serviceRepository.findAll();
		model.addAttribute("referents",referents);

		StaffLille1 referent=new StaffLille1();
		model.addAttribute("newReferent",referent);
		model.addAttribute("service",services);
		model.addAttribute("user",user);

		return "administrator/gestionReferent";
	}



	@RequestMapping(value = "/homeStaffLille1", method = RequestMethod.GET)
	public String getHomeStaffLille1(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		System.out.println(staffLille1.getEmail());
		user=staffLille1Service.findByEmail(staffLille1.getEmail());
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
		staffLille1Service.save(gestionnaire);

		return new ModelAndView("redirect:gestionModerator");
	}

	@RequestMapping(value = "/deleteReferent", method = RequestMethod.GET)
	public ModelAndView deleteReferent(Model model,HttpSession session,@RequestParam(value="mode",required=true)String mode,@RequestParam(value="name",required=true)String name) {
		StaffLille1 user= staffLille1Service.findByEmail(mode);
		StaffLille1 referent= staffLille1Service.findByEmail(name);

		referent.setReferent(false);
		model.addAttribute("user",user);
		staffLille1Service.save(referent);
		return new ModelAndView("redirect:gestionReferent");
	}

	@RequestMapping(value = "/newModerator", method = RequestMethod.POST)
	public ModelAndView newModerator(@ModelAttribute StaffLille1 staffLille1) {
		StaffLille1 gestionnaire;
		if(staffLille1Service.findByEmail(staffLille1.getEmail())!=null){
			gestionnaire=staffLille1Service.findByEmail(staffLille1.getEmail());
			System.out.println(gestionnaire.getFirstName());
			gestionnaire.setModerator(true);
			staffLille1Service.save(gestionnaire);
		}
		else{
			gestionnaire=new StaffLille1();
			gestionnaire.setEmail(staffLille1.getEmail());
			gestionnaire.setModerator(true);
			staffLille1Service.save(gestionnaire);
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
			staffLille1Service.save(referent);
		}
		else{

			referent=new StaffLille1();
			referent.setEmail(staffLille1.getEmail());
			referent.setReferent(true);
			staffLille1Service.save(referent);
		}		

		return new ModelAndView("redirect:gestionReferent");
	}


}
