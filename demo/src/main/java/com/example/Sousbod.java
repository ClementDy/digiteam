
package com.example;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import java.sql.*;

import javax.validation.Valid;


@EnableAutoConfiguration
@SpringBootApplication
@Controller
public class Sousbod {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Example.class, args);
	}



	@RequestMapping(value="/body",method = RequestMethod.GET)
	public String hello(@RequestParam(value="name", required=false, defaultValue="sousbody") String name, Model model) {
		String sousBod = "sousbody";
		model.addAttribute("nSousbody", sousBod);
		return "body";
	}

	
	@RequestMapping(value="/sbody",method = RequestMethod.GET)
	public String sousBody(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		String sousBod = "sousbody2";
		model.addAttribute("sousbody", sousBod);
		return "body";
	}
	
	
}
