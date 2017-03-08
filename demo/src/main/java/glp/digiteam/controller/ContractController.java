package glp.digiteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.repository.ReferentRepository;
import glp.digiteam.services.OfferService;
import glp.digiteam.services.ReferentService;
import glp.digiteam.services.StudentService;

@EnableAutoConfiguration
@Controller
@ComponentScan({ "glp.digiteam.services", "glp.digiteam.entity.offer" })
public class ContractController {

	@Autowired
	private OfferService offerService;

	@Autowired
	private ReferentService referentService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ReferentRepository referentRepository;

	@RequestMapping(value = "/newContract", method = RequestMethod.GET)
	public String contract(Model model, HttpSession session) {
		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");

		if (isReferent(staffLille1)) {
			Referent referent = referentRepository.findByName(staffLille1.getName());
			model.addAttribute("user", referent);
		}
		return "contract/new_contract";
	}
	
	
	public boolean isReferent(StaffLille1 staffLille1){
		if(referentRepository.findByName(staffLille1.getName())!=null){
			System.out.println("Referent!");
			return true;
		}
		return false;
	}
}
