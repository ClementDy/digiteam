package glp.digiteam.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.offer.ServiceEntity;
import glp.digiteam.entity.student.Student;
import glp.digiteam.services.ServiceService;
import glp.digiteam.webServices.ServiceWebService;
import glp.digiteam.webServices.ServiceWebServiceService;


@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class AuthenticationController {

	@Autowired
	private ServiceWebServiceService servicewebsrviceservice;
	
	@Autowired
	private ServiceService serviceservice;
	
	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public String authenficationStudent(Model model) throws JSONException {
		Student student = new Student();
		Referent referent = new Referent();
		List<ServiceWebService> services = servicewebsrviceservice.getServicesWS();
		
		for (ServiceWebService serviceWebService : services) {
			ServiceEntity servicentity = new ServiceEntity(serviceWebService.getCode(), serviceWebService.getLibelle());
			serviceservice.saveService(servicentity);
					}
	
		model.addAttribute("student",student);

		model.addAttribute("referent",referent);
		
		return "authentication";
	}


	@RequestMapping(value ="/authentication", method = RequestMethod.POST)
	public ModelAndView getStudent(@Valid @ModelAttribute Student student,Referent referent,BindingResult bindingresult, Model model, HttpSession session) {

		if (student.getNip() != null) {
			session.setAttribute("student", student);
			return new ModelAndView("redirect:/home");
		} else {
			session.setAttribute("referent", referent);
			return new ModelAndView("redirect:/offers");
		}

	}
}
