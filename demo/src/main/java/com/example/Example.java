package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Student;

import java.sql.*;

import javax.validation.Valid;


@EnableAutoConfiguration
@SpringBootApplication
@Controller
public class Example {
	
	@Autowired
	private StudentRepository studentRepository;
	
	/*public static void main(String[] args) throws Exception {
		SpringApplication.run(Example.class, args);
	}*/

	@RequestMapping(value="/forminfos",method = RequestMethod.GET)
	public String form_info(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		Student student=new Student();

		model.addAttribute("student", student);
		return "forminfos";
	}

	
	@RequestMapping("/form_souhait")
	public String form_souhait(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "form_souhait";
	}

	@RequestMapping("/form_chargerCV")
	public String form_chargerCV(@RequestParam(value="name", required = false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "form_chargerCV";
	}

	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String addEtudiant(@Valid Student student, BindingResult bindingResult, Model model, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e,
				"lastName", "lastName.empty", "Last Name is required");
		if(bindingResult.hasErrors()){
			return "form_info";
		}
		System.out.println(student.toString());
		System.out.println();
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		//studentRepository.save(new Student(student.getLastName(),student.getFirstName()));
		return "result";
	}




	@RequestMapping("/form_divers")
	public String form_divers(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "form_divers";
	}

	
	@RequestMapping(value="/form_motivations", method=RequestMethod.GET)
	public String form_motivations_GET(Model model) {
		return "form_motivations";
	}

	@RequestMapping(value="/form_motivations", method=RequestMethod.POST)
	public String form_motivations_POST(Model model) {
		return "form_motivations";
	}
	
	
	public String connect(){
		try{
			String url="jdbc:mysql://172.28.2.10:3306/siteweb";
			Connection conn = DriverManager.getConnection(url,"root","MYSECRET");
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT * from test");
			while ( rs.next() ) {
				String lastName = rs.getString("colonne1");
				return lastName;
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return e.getMessage();
		}
		return "erreur..";
	}

}
