package glp.digiteam.controller;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import glp.digiteam.entity.Student;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")

public class AuthenticationController {
	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public String authenfication(Model model) {
		Student student = new Student(); 
		model.addAttribute("student",student);
		return "authentication";
	}
	
	@RequestMapping(value ="/authentication", method = RequestMethod.POST)
	public String getStudent(@Valid @ModelAttribute Student student,BindingResult bindingresult, Model model) {
		System.out.println("lol");
		System.out.println(student.getNip());
		model.addAttribute("student",student);
		return "home";
		
	}
}
