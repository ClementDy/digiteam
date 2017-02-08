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
import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.GenericOffer;
import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.repository.MissionRepository;
import glp.digiteam.services.OfferService;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class OfferController {

	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	OfferService offerService;
	Referent referent;
	
	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
	public String homeContracts(Model model,HttpSession session) {
		
		referent= (Referent) session.getAttribute("referent");
		model.addAttribute("referent", referent);
		return "offers/offersHome";
	}
	
	@RequestMapping(value = "/newGeneriqueOffer", method = RequestMethod.GET)
	public String newGeneriqueOffer(Model model,HttpSession session) {
		System.out.println("GET offer");
		AbstractOffer offer=new GenericOffer();
		model.addAttribute("offer", offer);
		
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		model.addAttribute("referent", referent);
		return "offers/newGeneriqueOffer";
	}
	
	@RequestMapping(value = "/newGeneriqueOffer", method = RequestMethod.POST)
	public ModelAndView saveOffer(@ModelAttribute AbstractOffer offer,Model model,HttpSession session) {

		
		offerService.saveOffer(offer);
		return new ModelAndView("redirect:offersHome");
	}
}
