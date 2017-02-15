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
import org.springframework.web.servlet.ModelAndView;

import glp.digiteam.entity.offer.Administrator;
import glp.digiteam.entity.offer.GenericOffer;
import glp.digiteam.entity.offer.Moderator;
import glp.digiteam.repository.AdministratorRepository;
import glp.digiteam.repository.ReferentRepository;
import glp.digiteam.services.AdministratorService;
import glp.digiteam.services.ReferentService;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class AdministratorController {

	@Autowired
	private AdministratorRepository administratorRepository;


	@Autowired
	private AdministratorService administratorService;
	
	
	Moderator moderator=new Moderator();
	Administrator administrator;

	@RequestMapping(value = "/homeAdministrator", method = RequestMethod.GET)
	public String getHomeAdministrator(Model model) {
		administrator=administratorRepository.findByName("admin");
		model.addAttribute(moderator);
		return "administrator/homeAdministrator";
	}
	
	@RequestMapping(value = "/newModerator", method = RequestMethod.POST)
	public String newModerator(@ModelAttribute Moderator mode) {
		administrator.addModerator(mode);
		mode.setAdministrator(administrator);
		
		administratorService.saveAdministrator(administrator);
		
		
		return "administrator/homeAdministrator";
	}
	
}
