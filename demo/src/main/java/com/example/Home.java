package com.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Student;

@EnableAutoConfiguration
@Controller
public class Home {

	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String hello(@RequestParam(value="name", required=false, defaultValue="sousbody") String name, Model model) {
		Student student=new Student();
		model.addAttribute("student",student);
		return "home";
	}
	
}
