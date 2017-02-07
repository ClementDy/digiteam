package glp.digiteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import glp.digiteam.entity.offer.GenericOffer;
import glp.digiteam.entity.offer.Offer;
import glp.digiteam.entity.student.Student;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class OfferController {

	Student student;
	
	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
	public String homeContracts(Model model,HttpSession session) {
		
		student=(Student) session.getAttribute("student");
		model.addAttribute("student", student);
		return "offers/offersHome";
	}
	
	@RequestMapping(value = "/newGeneriqueOffer", method = RequestMethod.GET)
	public String newGeneriqueOffer(Model model,HttpSession session) {
		System.out.println("GET offer");
		Offer offer=new GenericOffer();
		model.addAttribute("offer", offer);
		model.addAttribute("student", student);
		return "offers/newGeneriqueOffer";
	}
	
	@RequestMapping(value = "/newGeneriqueOffer", method = RequestMethod.POST)
	public ModelAndView saveOffer(@ModelAttribute Offer offer,Model model,HttpSession session) {

		System.out.println("Enregistrement de l'offre");
		return new ModelAndView("redirect:offersHome");
	}
}
