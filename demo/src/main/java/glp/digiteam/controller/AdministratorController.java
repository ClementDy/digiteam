package glp.digiteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		model.addAttribute("user",user);
		return "administrator/gestionModerator";
	}
	
	
	@RequestMapping(value = "/gestionReferent", method = RequestMethod.GET)
	public String gestionReferent(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		user=staffLille1Repository.findByEmail(staffLille1.getEmail());
		
	//	Iterable<Referent> referents = referentRepository.findAll();
		Iterable<ServiceEntity> services = serviceRepository.findAll();
   //	model.addAttribute("referents",referents);

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
	public ModelAndView deleteModerator(Model model,HttpSession session,@RequestParam(value="admin",required=true)String admin,@RequestParam(value="name",required=true)String name) {
	
		/*
		Administrator administrator=administratorRepository.findByName(admin);
	
		administrator.removeModerator(name);
		moderatorRepository.deleteByName(name);
		administratorService.saveAdministrator(administrator);*/
		
		return new ModelAndView("redirect:gestionModerator");
	}
	
	@RequestMapping(value = "/deleteReferent", method = RequestMethod.GET)
	public ModelAndView deleteReferent(Model model,HttpSession session,@RequestParam(value="mode",required=true)String mode,@RequestParam(value="name",required=true)String name) {
	
		/*Moderator moderator=moderatorRepository.findByName(mode);
	
		referentRepository.deleteByName(name);
		moderatorService.saveModerator(moderator);*/
		
		return new ModelAndView("redirect:gestionReferent");
	}
	
	@RequestMapping(value = "/newModerator", method = RequestMethod.POST)
	public ModelAndView newModerator(/*@ModelAttribute Moderator mode*/) {
		/*administrator.addModerator(mode);
		mode.setAdministrator(administrator);
		
		administratorService.saveAdministrator(administrator);*/
		
		
		return new ModelAndView("redirect:gestionModerator");
	}
	
	@RequestMapping(value = "/newReferent", method = RequestMethod.POST)
	public ModelAndView newReferent(/*@ModelAttribute Referent referent*/) {
		
	//	referentService.saveReferent(referent);
		

		return new ModelAndView("redirect:gestionReferent");
	}
	
	
}
