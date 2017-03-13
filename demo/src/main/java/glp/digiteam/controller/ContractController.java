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

import glp.digiteam.entity.offer.Contract;
import glp.digiteam.entity.offer.GenericOffer;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;
import glp.digiteam.repository.MissionRepository;
import glp.digiteam.repository.StaffLille1Repository;
import glp.digiteam.services.ContractService;
import glp.digiteam.services.OfferService;
import glp.digiteam.services.StudentService;

@EnableAutoConfiguration
@Controller
@ComponentScan({ "glp.digiteam.services", "glp.digiteam.entity.offer" })
public class ContractController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private StaffLille1Repository staffLille1Repository;
	
	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private StudentService studentService;


	@RequestMapping(value = "/newContract", method = RequestMethod.GET)
	public String contract(Model model, HttpSession session,
			@RequestParam(value = "nip", required = true, defaultValue = "") Integer nip) {
		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");

		Student student = studentService.getStudentByNip(nip);

		model.addAttribute("user", staffLille1);

		Contract contract = new Contract();

		model.addAttribute("student", student);
		model.addAttribute("contract", contract);

		return "contract/new_contract";
	}


	@RequestMapping(value = "/newContract", method = RequestMethod.POST)
	public ModelAndView saveContract(@ModelAttribute Contract contract, Model model, HttpSession session) {


		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");

		contract.setReferent(staffLille1);

		contractService.saveContract(contract);
		
		return new ModelAndView("redirect:homeContract");

	}
	
	@RequestMapping(value = "/homeContract", method = RequestMethod.GET)
	public String homeContact(Model model, HttpSession session) {
		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		model.addAttribute("user", staffLille1);

		List<Student> listCandidatures = studentService.getAllCandidature();
		model.addAttribute("listCandidature", listCandidatures);

		return "contract/homeContract";
	}
	
	@RequestMapping(value = "/homeContract", method = RequestMethod.POST)
	public String consultCandidatures(
			@RequestParam(value="name", required=true, defaultValue="") String name,
			@RequestParam(value="formation", required=true,defaultValue="") String formation,
			@RequestParam(value="mission", required=true,defaultValue="") String mission,Model model, HttpSession session) {
		
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		StaffLille1 staffLille1=(StaffLille1)session.getAttribute("staffLille1");
		
		model.addAttribute("user",staffLille1);

		if(name.isEmpty() && mission.isEmpty() && formation.isEmpty()){
			List<Student> listCandidatures = studentService.getAllCandidature();
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "consult_candidatures";
		}else{
		
		/*if(name.isEmpty()  && !formation.isEmpty()){
			List<Student> listCandidatures = studentService.findWithTraining(formation);
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "consult_candidatures";

		}*/
			List<Student> listCandidatures = studentService.findWithParameter(name,formation,mission);
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "consult_candidatures";
		
		}/*
		if(!name.isEmpty() && formation.isEmpty() ){
			List<Student> listCandidatures = studentService.findByName(name);;
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "contract/homeContract";

		}
		if(!name.isEmpty()  && !formation.isEmpty()){
			List<Student> listCandidatures = studentService.findWithParameter(name,formation);
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "contract/homeContract";

		}
		if(name.isEmpty()  && !formation.isEmpty() ){
			List<Student> listCandidatures = studentService.findWithTraining(formation);
			model.addAttribute("listCandidature", listCandidatures);
			model.addAttribute("size", listCandidatures.size());
			return "contract/homeContract";

		}
		
		List<Student> listCandidatures = studentService.getAllCandidature();
		model.addAttribute("listCandidature", listCandidatures);
		model.addAttribute("size", listCandidatures.size());
		return "contract/homeContract";*/

	}
}
