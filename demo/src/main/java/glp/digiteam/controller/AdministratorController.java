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
import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.repository.AdministratorRepository;
import glp.digiteam.repository.ModeratorRepository;
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
	private ModeratorRepository moderatorRepository;

	@Autowired
	private ReferentRepository referentRepository;


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
	

	@RequestMapping(value = "/homeStaffLille1", method = RequestMethod.GET)
	public String getHomeStaffLille1(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(isAdministrator(staffLille1)){
			Administrator administrator=administratorRepository.findByName(staffLille1.getName());
			model.addAttribute("user",administrator);
		}
		if(isModerator(staffLille1)){
			Moderator moderator=moderatorRepository.findByName(staffLille1.getName());
			model.addAttribute("user",moderator);
		}	
		if(isReferent(staffLille1)){
			Referent referent=referentRepository.findByName(staffLille1.getName());
			System.out.println(referent.getClass().getName());
			model.addAttribute("user",referent);
		}	
		return "homeStaffLille1";
	}
	
	@RequestMapping(value = "/newModerator", method = RequestMethod.POST)
	public String newModerator(@ModelAttribute Moderator mode) {
		administrator.addModerator(mode);
		mode.setAdministrator(administrator);
		
		administratorService.saveAdministrator(administrator);
		
		
		return "administrator/homeAdministrator";
	}
	
	public boolean isAdministrator(StaffLille1 staffLille1){
		if(administratorRepository.findByName(staffLille1.getName())!=null){
			System.out.println("Admin!");
			return true;
		}
		return false;
		
	}
	
	public boolean isModerator(StaffLille1 staffLille1){
		if(moderatorRepository.findByName(staffLille1.getName())!=null){
			System.out.println("Moderateur!");
			return true;
		}
		return false;
	}
	

	public boolean isReferent(StaffLille1 staffLille1){
		if(referentRepository.findByName(staffLille1.getName())!=null){
			System.out.println("Referent!");
			return true;
		}
		return false;
	}
	
}
