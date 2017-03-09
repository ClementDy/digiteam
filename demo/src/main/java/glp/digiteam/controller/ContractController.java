package glp.digiteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.repository.StaffLille1Repository;
import glp.digiteam.services.OfferService;
import glp.digiteam.services.StudentService;

@EnableAutoConfiguration
@Controller
@ComponentScan({ "glp.digiteam.services", "glp.digiteam.entity.offer" })
public class ContractController {

	@Autowired
	private OfferService offerService;

	@Autowired
	private StaffLille1Repository staffLille1Repository;

	@Autowired
	private StudentService studentService;
	
	StaffLille1 user;

	@RequestMapping(value = "/newContract", method = RequestMethod.GET)
	public String contract(Model model, HttpSession session) {
		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");
		
		user=staffLille1Repository.findByEmail(staffLille1.getEmail());
		model.addAttribute("user", user);
		return "contract/new_contract";
	}
	
	

}
