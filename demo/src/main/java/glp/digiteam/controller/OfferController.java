package glp.digiteam.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.GenericOffer;
import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.offer.Responsible;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.repository.MissionRepository;
import glp.digiteam.services.OfferService;
import glp.digiteam.services.ReferentService;
import glp.digiteam.services.ResponsibleService;

@EnableAutoConfiguration
@Controller
@ComponentScan({"glp.digiteam.services","glp.digiteam.entity.offer"})
public class OfferController {

	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired

	private ResponsibleService responsibleService;
	

	@Autowired
	ReferentService referentService;


	
	Responsible responsible;
	
	AbstractOffer offer;
	
	
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
		
		referent=new Referent();
		responsible=new Responsible();
		

		offer=new GenericOffer();
		
		
		model.addAttribute("offer", offer);
		
		
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		model.addAttribute("referent", referent);
		return "offers/newGeneriqueOffer";
	}
	
	@RequestMapping(value = "/newGeneriqueOffer", method = RequestMethod.POST)
	public ModelAndView saveOffer(Model model,HttpSession session) {

		
		referent.addOffer(offer);
		offer.setReferent(referent);
		
		responsible.addOffer(offer);
		offer.setResponsible(responsible);
		System.out.println(responsible.getOffers().toString());
		responsibleService.saveResponsible(responsible);
		referentService.saveReferent(referent);
		return new ModelAndView("redirect:offersHome");
	}
}
