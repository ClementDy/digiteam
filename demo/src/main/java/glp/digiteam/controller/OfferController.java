package glp.digiteam.controller;

import java.util.Calendar;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.Administrator;
import glp.digiteam.entity.offer.GenericOffer;
import glp.digiteam.entity.offer.Moderator;
import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.offer.Responsible;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.offer.StandardOffer;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;
import glp.digiteam.repository.AdministratorRepository;
import glp.digiteam.repository.MissionRepository;
import glp.digiteam.repository.ModeratorRepository;
import glp.digiteam.repository.OfferRepository;
import glp.digiteam.repository.ReferentRepository;
import glp.digiteam.repository.StudentRepository;
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
	private AdministratorRepository administratorRepository;

	@Autowired
	private ModeratorRepository moderatorRepository;


	@Autowired
	private ReferentService referentService;

	@Autowired
	private ReferentRepository referentRepository;

	@Autowired
	private StudentService studentService;

	private Responsible responsible;

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private OfferService offerService;


	private Referent referent;

	@RequestMapping(value = "/offers", method = RequestMethod.GET)
	public String homeContracts(Model model,HttpSession session) {
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
			checkOffer(referent);
			System.out.println(referent.getClass().getName());
			model.addAttribute("user",referent);
		}	


		model.addAttribute("referent", referent);
		return "offers/offersHome";
	}

	@RequestMapping(value = "/newGenericOffer", method = RequestMethod.GET)
	public String newGeneriqueOffer(Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(isReferent(staffLille1)){
			Referent referent=referentRepository.findByName(staffLille1.getName());
			System.out.println(referent.getClass().getName());
			model.addAttribute("user",referent);
		}

		GenericOffer offer=new GenericOffer();
		responsible=new Responsible();	
		model.addAttribute("offer", offer);
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		return "offers/newGenericOffer";
	}

	@RequestMapping(value = "/newGenericOffer", method = RequestMethod.POST)
	public ModelAndView saveGenericOffer(@ModelAttribute GenericOffer ofr,Model model,HttpSession session) {

		Referent referent = new Referent();
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(isReferent(staffLille1)){
			referent=referentRepository.findByName(staffLille1.getName());
			System.out.println(referent.getClass().getName());
			model.addAttribute("user",referent);
		}

		responsible.setEmail(ofr.getResponsible().getEmail());
		responsible.setFirstName(ofr.getResponsible().getFirstName());
		responsible.setLastName(ofr.getResponsible().getLastName());
		responsible.setPhone(ofr.getResponsible().getPhone());
		referent.addResponsible(responsible);
		responsible.setReferent(referent);
		responsible.addOffer(ofr);
		ofr.setService(referent.getService());
		ofr.setResponsible(responsible);
		ofr.setStatus("Waiting");
		model.addAttribute("offer",ofr);


		referentService.saveReferent(referent);
		return new ModelAndView("redirect:offers");
	}

	@RequestMapping(value = "/newStandardOffer", method = RequestMethod.GET)
	public String newStandardOffer(Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(isReferent(staffLille1)){
			Referent referent=referentRepository.findByName(staffLille1.getName());
			System.out.println(referent.getClass().getName());
			model.addAttribute("user",referent);
		}

		StandardOffer offer=new StandardOffer();
		responsible=new Responsible();


		model.addAttribute("offer", offer);
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		return "offers/newStandardOffer";
	}

	@RequestMapping(value = "/newStandardOffer", method = RequestMethod.POST)
	public ModelAndView saveStandardOffer(@ModelAttribute StandardOffer ofr,Model model,HttpSession session) {

		Referent referent = new Referent();
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(isReferent(staffLille1)){
			referent=referentRepository.findByName(staffLille1.getName());
			System.out.println(referent.getClass().getName());
			model.addAttribute("user",referent);
		}

		responsible.setEmail(ofr.getResponsible().getEmail());
		responsible.setFirstName(ofr.getResponsible().getFirstName());
		responsible.setLastName(ofr.getResponsible().getLastName());
		responsible.setPhone(ofr.getResponsible().getPhone());
		referent.addResponsible(responsible);
		responsible.setReferent(referent);
		responsible.addOffer(ofr);
		ofr.setResponsible(responsible);
		ofr.setService(referent.getService());
		ofr.setStatus("Waiting");
		model.addAttribute("offer",ofr);


		referentService.saveReferent(referent);
		return new ModelAndView("redirect:offers");
	}


	@RequestMapping(value = "/consult_candidatures", method = RequestMethod.GET)
	public String consult(Model model,HttpSession session) {

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
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);

		List<Student> listCandidatures = studentService.getAllCandidature();
		model.addAttribute("listCandidature", listCandidatures);
		return "consult_candidatures";
	}

	@RequestMapping(value = "/consult_candidatures", method = RequestMethod.POST)
	public String consultCandidatures(
			@RequestParam(value="name", required=true, defaultValue="") String name,
			@RequestParam(value="formation", required=true,defaultValue="") String formation,Model model, HttpSession session) {

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


		if(!name.isEmpty() && formation.isEmpty() ){
			List<Student> listCandidatures = studentService.findByName(name);;
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "consult_candidatures";

		}
		if(!name.isEmpty()  && !formation.isEmpty()){
			List<Student> listCandidatures = studentService.findWithParameter(name,formation);
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "consult_candidatures";

		}
		if(name.isEmpty()  && !formation.isEmpty() ){
			List<Student> listCandidatures = studentService.findWithTraining(formation);
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "consult_candidatures";

		}

		List<Student> listCandidatures = studentService.getAllCandidature();
		model.addAttribute("listCandidature", listCandidatures);
		model.addAttribute("size", listCandidatures.size());
		return "consult_candidatures";

	}

	@RequestMapping(value = "/consult_offers", method = RequestMethod.GET)
	public String consult_offers(Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(staffLille1!=null){
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
		}
		else{
			Student student=(Student) session.getAttribute("student");
			model.addAttribute("user",student);
		}


		Iterable<AbstractOffer> listOffers = offerRepository.findLastOffers(new PageRequest(0, 30));
		model.addAttribute("listOffers",listOffers);

		return "offers/consult_offers";
	}

	@RequestMapping(value = "/consult_offers", method = RequestMethod.POST)
	public String consult_offers_search(
			@RequestParam(value="libelle", required=true, defaultValue="") String libelle,
			@RequestParam(value="num_offer", required=true,defaultValue="") String num_offer,
			@RequestParam(value="responsive", required=true,defaultValue="") String responsive,
			Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(staffLille1!=null){
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
		}
		else{
			Student student=(Student) session.getAttribute("student");
			model.addAttribute("user",student);
		}

		List<AbstractOffer> listOffers = offerService.searchOffers(libelle,num_offer,responsive);
		model.addAttribute("listOffers",listOffers);

		if(listOffers==null){
			model.addAttribute("size", 0);
		}
		else{
			model.addAttribute("size", listOffers.size());
		}

		return "offers/consult_offers";
	}

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public String profil(Model model,HttpSession session,@RequestParam(value="nip", required=true) Integer nip) {
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
		model.addAttribute("student", studentService.getStudentByNip(nip));
		return "profile";
	}

	public void checkOffer(Referent referent){
		for(int i=0;i<referent.getResponsible().size();i++){
			for(int j=0;j<referent.getResponsible().get(i).getOffers().size();j++){
				if(referent.getResponsible().
						get(i).getOffers().get(j).getValidityDate().
						getTime()<(Calendar.getInstance().getTime().getTime())){
					referent.getResponsible().get(i).getOffers().get(j).setStatus("Expired");
					referentService.saveReferent(referent);
				}
			}
		}
	}

	@RequestMapping(value= "/newOffer",method=RequestMethod.GET)
	public ModelAndView newOffer(Model model, HttpSession session,@RequestParam(value = "id", required = true) long id){

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(isReferent(staffLille1)){
			Referent referent=referentRepository.findByName(staffLille1.getName());
			System.out.println(referent.getClass().getName());
			model.addAttribute("user",referent);
		}	
		AbstractOffer offer = offerRepository.findById(id);
		responsible=new Responsible();
		model.addAttribute("responsible", responsible);
		offer.setStatus("Waiting");
		offer.setCreationDate(Calendar.getInstance().getTime());
		offer.setValidityDate(null);
		model.addAttribute("offer",offer);
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);

		if(offer.getClass().getName().equals("glp.digiteam.entity.offer.GenericOffer")){

			return new ModelAndView("offers/newGenericOffer");
		}else {

			return new ModelAndView("offers/newStandardOffer");
		}

	}


	@RequestMapping(value = "/offerShow", method = RequestMethod.GET)
	public ModelAndView showOffer(Model model, HttpSession session,@RequestParam(value = "id", required = true) long id) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(staffLille1!=null){
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
		}
		else{
			Student student=(Student) session.getAttribute("student");
			model.addAttribute("student",student);
		}
		AbstractOffer offer = offerRepository.findById(id);

		if(offer!=null){
			model.addAttribute("offer", offer);
			return new ModelAndView("offers/offerAbstract");
		}

		return new ModelAndView("offers/offersHome");
	}

	@RequestMapping(value = "/gestionOffers", method = RequestMethod.GET)
	public ModelAndView gestionOffers(Model model, HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		if(isModerator(staffLille1)){
			Moderator moderator=moderatorRepository.findByName(staffLille1.getName());
			model.addAttribute("user",moderator);
		}	


		Iterable<AbstractOffer> offers = offerRepository.findAll();
		model.addAttribute("offers",offers);
		return new ModelAndView("offers/gestionOffers");
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
