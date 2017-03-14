package glp.digiteam.controller;

import java.util.Calendar;

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
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.offer.StandardOffer;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;
import glp.digiteam.services.MissionService;
import glp.digiteam.services.OfferService;
import glp.digiteam.services.StaffLille1Service;
import glp.digiteam.services.StudentService;


@EnableAutoConfiguration
@Controller
@ComponentScan({"glp.digiteam.services","glp.digiteam.entity.offer"})
public class OfferController {

	@Autowired
	private MissionService missionService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private StaffLille1Service staffLille1Service;

	@Autowired
	private StudentService studentService;


	@RequestMapping(value = "/offers", method = RequestMethod.GET)
	public String homeContracts(Model model,HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		checkOffer(user);
		model.addAttribute("user",user);

		return "offers/offersHome";
	}

	@RequestMapping(value = "/newGenericOffer", method = RequestMethod.GET)
	public String newGeneriqueOffer(Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());


		GenericOffer offer=new GenericOffer();
		model.addAttribute("user",user);
		model.addAttribute("offer", offer);
		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);
		return "offers/newGenericOffer";
	}

	@RequestMapping(value = "/newGenericOffer", method = RequestMethod.POST,params="action=Enregistrer")
	public ModelAndView saveGenericOffer(@ModelAttribute GenericOffer ofr,Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());


		user.addOffer(ofr);
		ofr.setService(user.getService());
		ofr.setReferent(user);

		ofr.setStatus("Waiting");
		model.addAttribute("offer",ofr);


		staffLille1Service.saveStaffLille1(user);
	
		return new ModelAndView("redirect:offers");
	}

	@RequestMapping(value = "/newGenericOffer", method = RequestMethod.POST,params="action=Accepter")
	public ModelAndView acceptGenericOffer(@ModelAttribute GenericOffer ofr,Model model,HttpSession session) {
		GenericOffer offre=(GenericOffer) offerService.findById(ofr.getId());
		StaffLille1 referent=staffLille1Service.findByEmail(offre.getReferent().getEmail());
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		model.addAttribute("user",user);
		model.addAttribute("referent",referent);
		offre.setTitle(ofr.getTitle());
		offre.setComment(ofr.getComment());
		offre.setMission(ofr.getMission());
		offre.setModerationDate(new java.util.Date());
		offre.setRemuneration(ofr.getRemuneration());
		offre.setRemunerationInfo(ofr.getRemunerationInfo());




		offre.setSkills(ofr.getSkills());
		offre.setValidityDate(ofr.getValidityDate());
		offre.setStatus("Validated");


		staffLille1Service.saveStaffLille1(referent);
		return new ModelAndView("redirect:gestionOffers");
	}

	@RequestMapping(value = "/newGenericOffer", method = RequestMethod.POST,params="action=Refuser")
	public ModelAndView refuseGenericOffer(@ModelAttribute GenericOffer ofr,Model model,HttpSession session) {
		GenericOffer offre=(GenericOffer) offerService.findById(ofr.getId());

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		offre.setStatus("Refused");

		offre.setComment(ofr.getComment());

		offerService.saveOffer(offre);

		return new ModelAndView("redirect:gestionOffers");

	}

	@RequestMapping(value = "/newStandardOffer", method = RequestMethod.GET)
	public String newStandardOffer(Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		StandardOffer offer=new StandardOffer();
		model.addAttribute("user",user);

		model.addAttribute("offer", offer);
		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);
		return "offers/newStandardOffer";
	}

	@RequestMapping(value = "/newStandardOffer", method = RequestMethod.POST,params="action=Enregistrer")
	public ModelAndView saveStandardOffer(@ModelAttribute StandardOffer ofr,Model model,HttpSession session) {


		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		user.addOffer(ofr);
		ofr.setReferent(user);
		ofr.setService(user.getService());
		ofr.setStatus("Waiting");
		model.addAttribute("offer",ofr);


		staffLille1Service.saveStaffLille1(user);
		return new ModelAndView("redirect:offers");
	}


	@RequestMapping(value = "/dispublish", method = RequestMethod.GET)
	public String dispublish(Model model,@RequestParam(value = "", required = true) long id, HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		model.addAttribute("user",user);
		offerService.dispublish(id, user);

		model.addAttribute("referent", user);

		return "offers/offersHome";

	}

	@RequestMapping(value = "/removeOffer", method = RequestMethod.GET)
	public String removeOffer(Model model,@RequestParam(value = "", required = true) long id, HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);
		offerService.removeOffer(id, user);

		model.addAttribute("referent", user);

		return "offers/offersHome";

	}
	@RequestMapping(value = "/newStandardOffer", method = RequestMethod.POST,params="action=Accepter")
	public ModelAndView acceptStandardOffer(@ModelAttribute StandardOffer ofr,Model model,HttpSession session) {

		StandardOffer offre=(StandardOffer) offerService.findById(ofr.getId());
		StaffLille1 referent=(StaffLille1) staffLille1Service.findByEmail(offre.getReferent().getEmail());
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());


		model.addAttribute("user",user);
		model.addAttribute("referent",referent);
		offre.setTitle(ofr.getTitle());
		offre.setComment(ofr.getComment());
		offre.setEndDate(ofr.getEndDate());
		offre.setHoursPerWeek(ofr.getHoursPerWeek());
		offre.setMission(ofr.getMission());
		offre.setModerationDate(new java.util.Date());
		offre.setRemuneration(ofr.getRemuneration());
		offre.setRemunerationInfo(ofr.getRemunerationInfo());

		offre.setSkills(ofr.getSkills());
		offre.setStartDate(ofr.getStartDate());
		offre.setValidityDate(ofr.getValidityDate());
		offre.setComment(ofr.getComment());
		offre.setStatus("Validated");


		staffLille1Service.saveStaffLille1(referent);
		return new ModelAndView("redirect:gestionOffers");
	}

	@RequestMapping(value = "/newStandardOffer", method = RequestMethod.POST,params="action=Refuser")
	public ModelAndView refuseStandardOffer(@ModelAttribute StandardOffer ofr,Model model,HttpSession session) {

		StandardOffer offre=(StandardOffer) offerService.findById(ofr.getId());

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		offre.setStatus("Refused");

		offre.setComment(ofr.getComment());

		offerService.saveOffer(offre);

		return new ModelAndView("redirect:gestionOffers");

	}



	@RequestMapping(value = "/consult_candidatures", method = RequestMethod.GET)
	public String consult(Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());

		model.addAttribute("user",user);
		Mission mission = new Mission();
		model.addAttribute("mission", mission);
		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);

		List<Student> listCandidatures = studentService.getAllCandidature();
		model.addAttribute("listCandidature", listCandidatures);
		return "consult_candidatures";
	}

	@RequestMapping(value = "/consult_candidatures", method = RequestMethod.POST)
	public String consultCandidatures(
			@ModelAttribute Mission mission,
			@RequestParam(value="name", required=true, defaultValue="") String name,
			@RequestParam(value="formation", required=true,defaultValue="") String formation,
			Model model, HttpSession session) {
		
		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);

		
		
		if(name.isEmpty() && mission.getTitle().isEmpty() && formation.isEmpty()){
			List<Student> listCandidatures = studentService.getAllCandidature();
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "consult_candidatures";
		}else{
			List<Student> listCandidatures = studentService.findWithParameter(name,formation,mission.getTitle());
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "consult_candidatures";
		
		}

	}

	@RequestMapping(value = "/consult_offers", method = RequestMethod.GET)
	public String consult_offers(Model model,HttpSession session) {

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");

		if(staffLille1!=null){
			StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
			model.addAttribute("user",user);

		}
		else{
			Student student=(Student) session.getAttribute("student");
			model.addAttribute("user",student);
		}


		Iterable<AbstractOffer> listOffers = offerService.findLastOffers(0, 30);
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
			StaffLille1	user=staffLille1Service.findByEmail(staffLille1.getEmail());
			model.addAttribute("user",user);	
		}
		else{
			Student student=(Student) session.getAttribute("student");
			model.addAttribute("user",student);
		}

		List<AbstractOffer> listOffers = offerService.searchOffers(libelle,num_offer);
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
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);

		model.addAttribute("student", studentService.getStudentByNip(nip));
		return "profile";
	}

	public void checkOffer(StaffLille1 referent){
		for(int i=0;i<referent.getOffers().size();i++){
			if(referent.getOffers().get(i).getValidityDate().
					getTime()<(Calendar.getInstance().getTime().getTime())){

				referent.getOffers().get(i).setStatus("Expired");
				staffLille1Service.saveStaffLille1(referent);
			}
		}
	}

	@RequestMapping(value= "/newOffer",method=RequestMethod.GET)
	public ModelAndView newOffer(Model model, HttpSession session,@RequestParam(value = "id", required = true) long id){

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);
		
		AbstractOffer offer = offerService.findById(id);
		offer.setStatus("Waiting");
		offer.setCreationDate(Calendar.getInstance().getTime());
		offer.setValidityDate(null);
		model.addAttribute("offer",offer);
		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);

		if(offer.getClass().getName().equals("glp.digiteam.entity.offer.GenericOffer")){

			return new ModelAndView("offers/newGenericOffer");
		}else {

			return new ModelAndView("offers/newStandardOffer");
		}

	}
	
	@RequestMapping(value= "/modifyOffer",method=RequestMethod.GET)
	public ModelAndView modifyOffer(Model model, HttpSession session,@RequestParam(value = "id", required = true) long id){

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);
		
		AbstractOffer offer = offerService.findById(id);
		offer.setStatus("Waiting");
		offer.setCreationDate(Calendar.getInstance().getTime());
		model.addAttribute("offer",offer);
		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);

		if(offer.getClass().getName().equals("glp.digiteam.entity.offer.GenericOffer")){
			return new ModelAndView("offers/newGenericOffer");
		}else {
			return new ModelAndView("offers/newStandardOffer");
		}

	}

	@RequestMapping(value= "/manageOffer",method=RequestMethod.GET)
	public ModelAndView manageOffer(Model model, HttpSession session,@RequestParam(value = "id", required = true) long id){

		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);
	
		AbstractOffer offer = offerService.findById(id);
		model.addAttribute("offer",offer);
		Iterable<Mission> missions = missionService.findAll();
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
			StaffLille1	user=staffLille1Service.findByEmail(staffLille1.getEmail());
			model.addAttribute("user",user);
		
		}
		else{
			Student student=(Student) session.getAttribute("student");
			model.addAttribute("user",student);
		}
		AbstractOffer offer = offerService.findById(id);

		if(offer!=null){
			model.addAttribute("offer", offer);
			return new ModelAndView("offers/offerAbstract");
		}

		return new ModelAndView("offers/offersHome");
	}

	@RequestMapping(value = "/gestionOffers", method = RequestMethod.GET)
	public ModelAndView gestionOffers(Model model, HttpSession session) {
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1	user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);

		Iterable<AbstractOffer> offers = offerService.findAll();
		model.addAttribute("offers",offers);
		return new ModelAndView("offers/gestionOffers");
	}

	@RequestMapping(value = "/validateGenericOffer", method = RequestMethod.GET)
	public ModelAndView validateOffer(Model model,@RequestParam(value = "offer", required = true) GenericOffer offer, HttpSession session) {

		if(offer.getClass().getName().equals("glp.digiteam.entity.offer.GenericOffer")){
			GenericOffer go=(GenericOffer) offerService.findById(offer.getId());
			System.out.println(go.getId());

		}
		else if(offer.getClass().getName().equals("glp.digiteam.entity.offer.StandardOffer")){
			StandardOffer so=(StandardOffer) offerService.findById(offer.getId());
			System.out.println(so.getId());
		}
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);
		
		return new ModelAndView("redirect:offers/gestionOffers");
	}

	@RequestMapping(value = "/validateStandardOffer", method = RequestMethod.GET)
	public ModelAndView validateStandardOffer(Model model,@RequestParam(value = "offer", required = true) StandardOffer offer, HttpSession session) {


		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		StaffLille1 user=staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user",user);
		System.out.println(offer.getId());
		return new ModelAndView("redirect:offers/gestionOffers");
	}


}
