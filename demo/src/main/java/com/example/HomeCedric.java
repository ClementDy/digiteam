package com.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Student;


public class HomeCedric {

	@RequestMapping(value="/homeCedric",method = RequestMethod.GET)
	public String hello(Model model) {
		Student student=new Student();
		student.setFirstName("Prénom");
		student.setLastName("Nom");
		student.setMotivation("Motivations");
		
		model.addAttribute("student",student);
		
		return "homeCedric";
	}
	
	@RequestMapping(value="/form_motivations", method = RequestMethod.GET)
	public String form_motivations(Model model) {
		Student student=new Student();
		
		model.addAttribute("student",student);
		
		return "form_motivations";
	}

	@RequestMapping(value = "/resultCedric", method = RequestMethod.POST)
	public String addEtudiant(Student student, Model model) {
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("motivations", student.getMotivation());
		
		return "resultCedric";
	}
}
