package glp.digiteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class AdministratorController {

	
	

	@RequestMapping(value = "/homeAdministrator", method = RequestMethod.GET)
	public String getHomeAdministrator(Model model) {
		
		return "administrator/homeAdministrator";
	}
	
	
}
