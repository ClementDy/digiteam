package com.example.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "com.example.services")
public class ContractsController {

	@RequestMapping(value = "/contracts", method = RequestMethod.GET)
	public String homeContracts(Model model) {
		return "contractsHome";
	}
}