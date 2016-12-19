package com.example;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.repository.AddressRepository;
import com.example.repository.MiscellaneousRepository;
import com.example.repository.StudentRepository;

@EnableAutoConfiguration
@Controller
public class Home {
	
	@Autowired
	AddressRepository repositoryAddress;
	@Autowired
	StudentRepository repositoryStudent;
	@Autowired
	MiscellaneousRepository miscrepository;
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String hello(@RequestParam(value="name", required=false, defaultValue="sousbody") String name, Model model) {
		Student student=new Student();
		model.addAttribute("student",student);
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
		
		
		repositoryAddress.save(new Address(student.getAddress().getStreet(), student.getAddress().getComplement(), student.getAddress().getPostalCode(), student.getAddress().getCity()));
		repositoryStudent.save(new Student(student.getFirstName(), student.getLastName(), student.getPhone(), student.getEmail(), student.getNationality(),student.getMotivation()));
		
		
		
		System.out.println(student.getAddress().toString());
		System.out.println();
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("mail", student.getEmail());
		model.addAttribute("phone", student.getPhone());
		model.addAttribute("street", student.getAddress().getStreet());
		model.addAttribute("city", student.getAddress().getCity());
		model.addAttribute("postalCode", student.getAddress().getPostalCode());
		
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
		repositoryStudent.save(new Student(student.getFirstName(), student.getLastName(), student.getPhone(), student.getEmail(), student.getNationality(),student.getMotivation()));
		return "resultCedric";
	}
}
