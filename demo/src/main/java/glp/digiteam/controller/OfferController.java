package glp.digiteam.controller;

import java.util.List;

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
import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.GenericOffer;
import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.offer.Responsible;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;
import glp.digiteam.repository.MissionRepository;
import glp.digiteam.repository.ReferentRepository;
import glp.digiteam.services.OfferService;
import glp.digiteam.services.ReferentService;
import glp.digiteam.services.StudentService;

@EnableAutoConfiguration
@Controller
@ComponentScan({"glp.digiteam.services","glp.digiteam.entity.offer"})
public class OfferController {

	@Autowired
	private MissionRepository missionRepository;
	

	@Autowired
	private ReferentService referentService;
	
	@Autowired
	private ReferentRepository referentRepository;

	@Autowired
	private StudentService studentService;
	
	private Responsible responsible;
	
	private GenericOffer offer=new GenericOffer();
	
	
	private OfferService offerService;
	
	private Referent referent;
	
	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
	public String homeContracts(Model model,HttpSession session) {
		
		referent= (Referent) session.getAttribute("referent");
		referent=referentRepository.findByName(referent.getName());
		model.addAttribute("referent", referent);
		return "offers/offersHome";
	}
	
	@RequestMapping(value = "/newGeneriqueOffer", method = RequestMethod.GET)
	public String newGeneriqueOffer(Model model,HttpSession session) {
	
		responsible=new Responsible();
		model.addAttribute("referent", referent);
		System.out.println(referent.getName());
		
		
		for(int i=0;i<referent.getResponsible().size();i++){
			for(int j=0;j<referent.getResponsible().get(i).getOffers().size();j++){
				System.out.println(referent.getResponsible().get(i).getOffers().get(j).getTitle());
			}
		}
		
		
		model.addAttribute("offer", offer);
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		model.addAttribute("referent", referent);
		return "offers/newGeneriqueOffer";
	}
	
	@RequestMapping(value = "/newGeneriqueOffer", method = RequestMethod.POST)
	public ModelAndView saveOffer(@ModelAttribute GenericOffer ofr,Model model,HttpSession session) {

		model.addAttribute("referent",referent);
		
		responsible.setEmail(ofr.getResponsible().getEmail());
		responsible.setFirstName(ofr.getResponsible().getFirstName());
		responsible.setLastName(ofr.getResponsible().getLastName());
		responsible.setPhone(ofr.getResponsible().getPhone());
		referent.addResponsible(responsible);
		responsible.setReferent(referent);
		responsible.addOffer(ofr);
		ofr.setResponsible(responsible);

		model.addAttribute("offer",ofr);
		
		
		referentService.saveReferent(referent);
		return new ModelAndView("redirect:contracts");
	}
	
	@RequestMapping(value = "/consult", method = RequestMethod.GET)
	public String consult(Model model,HttpSession session) {
		referent= (Referent) session.getAttribute("referent");
		referent=referentRepository.findByName(referent.getName());
		model.addAttribute("referent", referent);
		
		List<Student> listCandidature = studentService.getAllCandidature();
		model.addAttribute("listCandidature", listCandidature);
		return "consult";
	}
	
	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public String profil(Model model,HttpSession session,@RequestParam(value="nip", required=true) Integer nip) {
		referent= (Referent) session.getAttribute("referent");
		referent=referentRepository.findByName(referent.getName());
		model.addAttribute("referent", referent);
		model.addAttribute("student", studentService.getStudentByNip(nip));
		return "profile";
	}
}
