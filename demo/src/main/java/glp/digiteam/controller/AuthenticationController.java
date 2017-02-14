package glp.digiteam.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
import glp.digiteam.entity.student.Student;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")

public class AuthenticationController {

	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public String authenficationStudent(Model model) {
		Student student = new Student();
		Referent referent = new Referent();
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
