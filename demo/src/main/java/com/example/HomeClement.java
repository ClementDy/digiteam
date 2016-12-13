package com.example;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Student;
import com.example.services.ServiceStudent;

@EnableAutoConfiguration
@Controller
public class HomeClement {

	ServiceStudent serviceStudent=new ServiceStudent();
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String hello(@RequestParam(value="name", required=false, defaultValue="sousbody") String name, Model model) {
		FormulaireStudent student=new FormulaireStudent();
		model.addAttribute("formulairestudent",student);
		return "home";
	}
	
	
	@RequestMapping(value="/form_dispos", method = RequestMethod.GET)
	public String form_souhait(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

		return "form_dispos";
	}
	
	
	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String addEtudiant(@Valid FormulaireStudent student, BindingResult bindingResult, Model model, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e,
				"lastName", "lastName.empty", "Last Name is required");
		if(bindingResult.hasErrors()){
			return "form_infos";
		}
		System.out.println(student.toString());
		System.out.println();
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		
		//serviceStudent.addStudent(student);
		return "result";
	}

}
