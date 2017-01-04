package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Mission;
import com.example.entity.Student;
import com.example.entity.Wish;
import com.example.repository.MiscellaneousRepository;
import com.example.repository.MissionRepository;
import com.example.repository.StudentRepository;
import com.example.services.StudentService;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages="com.example.services")
public class Home {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRepository repositoryStudent;
	@Autowired
	MiscellaneousRepository miscrepository;
	@Autowired
	MissionRepository missionRepository;
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String hello(@RequestParam(value="name", required=false, defaultValue="sousbody") String name, Model model) {
		Student student=new Student();
		model.addAttribute("student",student);
		missionRepository.save(new Mission("Secrétariat d'examens"));
		missionRepository.save(new Mission("Animation culturelles scientifiques sportives et sociales"));
		missionRepository.save(new Mission("Accueil des étudiants"));
		missionRepository.save(new Mission("Assistance et accompagnement des étudiants handicapés"));
		missionRepository.save(new Mission("Soutien informatique et aide à l'utilisation des nouvelles technologies"));
		missionRepository.save(new Mission("Promotion de l'offre de formation"));
		missionRepository.save(new Mission("Tutorat"));
		missionRepository.save(new Mission("Service d'appui aux personnels de bibliothèque"));
		missionRepository.save(new Mission("Aide à l'insertion professionelle"));
		missionRepository.save(new Mission("Enquêtes"));
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		return "home";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String addEtudiant(@Valid Student student, BindingResult bindingResult, Model model, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e,
				"lastName", "lastName.empty", "Last Name is required");
		if(bindingResult.hasErrors()){
			return "form_infos";
		}
		System.out.println(student.toString());
		

		studentService.saveStudentProfile(student);
		
		
		System.out.println(student.getAddress().toString());
		System.out.println();
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("mail", student.getEmail());
		model.addAttribute("phone", student.getPhone());
		model.addAttribute("street", student.getAddress().getStreet());
		model.addAttribute("city", student.getAddress().getCity());
		model.addAttribute("postalCode", student.getAddress().getPostalCode());
		model.addAttribute("date",student.getDateVisa());
		model.addAttribute("startDate",student.getAvailability().getStartDate());
		model.addAttribute("endDate",student.getAvailability().getEndDate());
		model.addAttribute("startTimeMonday",student.getAvailability().getStartTimeMonday());
		model.addAttribute("endTimeMonday",student.getAvailability().getEndTimeMonday());
		System.out.println(student.getDateVisa());
		
		return "result";
	}
	
	@RequestMapping(value = "/resultAlex", method = RequestMethod.POST)
	public String addEtudian(Student student, Model model) {
		model.addAttribute("nameAssociation", student.getMisc().getNameAssociation());
		model.addAttribute("itKnowledge", student.getMisc().getNameAssociation());
		model.addAttribute("languages", student.getMisc().getLanguages());
		model.addAttribute("otherFormations", student.getMisc().getOtherFormations());

		miscrepository.save(student.getMisc());
		return "resultAlex";
	}
	
	@RequestMapping(value = "/resultCedric", method = RequestMethod.POST)
	public String addEtudiant(Student student, Model model) {
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("motivations", student.getMotivation());
		repositoryStudent.save(new Student(student.getFirstName(), student.getLastName(), student.getPhone(), student.getEmail(), student.getNationality(),student.getMotivation(),null));
		return "resultCedric";
	}
	
	@RequestMapping(value = "/resultWilly", method = RequestMethod.POST)
	public String addEtudia(Student student, Model model) {
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("motivations", student.getMotivation());
		model.addAttribute("otherWish",student.getWish().getOtherWish());
		model.addAttribute("wish",student.getWish().getMissions());
		repositoryStudent.save(new Student(student.getFirstName(), student.getLastName(), student.getPhone(), student.getEmail(), student.getNationality(),student.getMotivation(),new Wish(student.getWish().getMissions(),student.getWish().getOtherWish())));
		return "resultWilly";
	}
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	    //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}
}
