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
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;
import glp.digiteam.services.ContractService;
import glp.digiteam.services.MissionService;
import glp.digiteam.services.StaffLille1Service;
import glp.digiteam.services.StudentService;

@EnableAutoConfiguration
@Controller
@ComponentScan({ "glp.digiteam.services", "glp.digiteam.entity.offer" })
public class ContractController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private MissionService missionService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StaffLille1Service staffLille1Service;


	@RequestMapping(value = "/newContract", method = RequestMethod.POST)
	public ModelAndView saveContract(@ModelAttribute Contract contract, Model model, HttpSession session) {

		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");

		StaffLille1 user = staffLille1Service.findByEmail(staffLille1.getEmail());

		if (user.isReferent == true) {

			Student student = studentService.getStudentByNip((Integer) session.getAttribute("student_contract"));
			contract.setReferent(user);

			contract.setStudent(student);

			contractService.saveContract(contract);

			return new ModelAndView("redirect:homeContract");

		}
		return new ModelAndView("redirect:homeStaffLille1");

	}


	@RequestMapping(value = "/homeContract", method = RequestMethod.GET)
	public String homeContact(Model model, HttpSession session) {
		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");
		StaffLille1 user = staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user", user);

		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);

		List<Student> listCandidatures = studentService.getAllCandidature();
		model.addAttribute("listCandidature", listCandidatures);

		return "contract/homeContract";
	}

	@RequestMapping(value = "/homeContract", method = RequestMethod.POST)
	public String consultCandidatures(Model model, HttpSession session,
			@RequestParam(value = "nip", required = true, defaultValue = "") Integer nip,
			@RequestParam(value = "type", required = true, defaultValue = "") Integer type) {
		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");

		StaffLille1 user = staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user", user);

		if (user.isReferent == true) {
			Student student = studentService.getStudentByNip(nip);
			
			if(student==null || !student.getStatut().equals("published")){
				return "contract/homeContract";
			}
			
			session.setAttribute("student_contract", student.getNip());
			Contract contract = new Contract();
			if(type==1){
				contract.setType("Vacation");
			}else{
				contract.setType("Etudiant");
			}
			

			model.addAttribute("student", student);
			model.addAttribute("contract", contract);

			return "contract/new_contract";

		}
		return "homeStaffLille1";
	}
	
	@RequestMapping(value = "/validateContract", method = RequestMethod.GET)
	public ModelAndView consultCandidatures(Model model, HttpSession session,
			@RequestParam(value = "id", required = true, defaultValue = "") Long id) {
		StaffLille1 staffLille1 = (StaffLille1) session.getAttribute("staffLille1");

		StaffLille1 user = staffLille1Service.findByEmail(staffLille1.getEmail());
		model.addAttribute("user", user);
		Contract contract = contractService.getContractByID(id);
		
		if (user.isReferent == true && contract!=null && contract.getReferent()==user) {
			contractService.validateContract(contract);
			return new ModelAndView("redirect:homeContract");
		}
		
		return new ModelAndView("homeStaffLille1");
	}
}
