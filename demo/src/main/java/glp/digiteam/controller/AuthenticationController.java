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
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Student;
import glp.digiteam.services.ServiceService;
import glp.digiteam.services.StudentService;
import glp.digiteam.webServices.ServiceWebService;
import glp.digiteam.webServices.ServiceWebServiceService;
import glp.digiteam.webServices.StudentWebService;
import glp.digiteam.webServices.StudentWebServiceService;
import glp.digiteam.webServices.TrainingWebService;
import glp.digiteam.webServices.TrainingWebServiceService;


@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class AuthenticationController {

	@Autowired
	private ServiceWebServiceService servicewebsrviceservice;

	@Autowired
	private ServiceService serviceservice;

	@Autowired
	private StudentService studentService;

	@Autowired
	private TrainingWebServiceService trainingLDAPService;

	@Autowired
	private StudentWebServiceService studentLDAPService;

	boolean notGoodNip=false;

	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public String authenficationStudent(Model model) throws JSONException {
		Student student = new Student();
		Referent referent = new Referent();
		StaffLille1 staffLille1 = new StaffLille1();

		List<ServiceWebService> services = servicewebsrviceservice.getServicesWS();

		for (ServiceWebService serviceWebService : services) {
			ServiceEntity servicentity = new ServiceEntity(serviceWebService.getCode(), serviceWebService.getLibelle());
			serviceservice.saveService(servicentity);
		}

		model.addAttribute("student",student);

		model.addAttribute("referent",referent);


		model.addAttribute("staffLille1",staffLille1);
		model.addAttribute("notGoodNip",notGoodNip);
		System.out.println(notGoodNip);
		return "authentication";
	}


	@RequestMapping(value ="/authentication", method = RequestMethod.POST)
	public ModelAndView getStudent(@Valid @ModelAttribute Student student,Referent referent,BindingResult bindingresult, Model model, HttpSession session) {

		if (student.getNip()!=null) {

			if (studentService.getStudentByNip(student.getNip())!=null){
				student= studentService.getStudentByNip(student.getNip());
				model.addAttribute("student", student);
				session.setAttribute("student", student);
				notGoodNip=false;
				return new ModelAndView("redirect:/home");			
			}else {
				try{
					StudentWebService studentLDAP = studentLDAPService.getStudentLDAP(student.getNip());
					TrainingWebService trainingLDAP =  trainingLDAPService.getTrainingLDAP(2017,
							student.getNip());

					student.setCivilite(studentLDAP.getEtu_civilite());
					student.setFirstName(studentLDAP.getEtu_prenom());
					student.setLastName(studentLDAP.getEtu_nom());
					student.setEmail(studentLDAP.getEtu_email());
					student.setNationality(studentLDAP.getEtu_libnationalite());
					student.getTrainings().get(0).setDate(trainingLDAP.getIns_ANNEE());
					student.getTrainings().get(0).setName(trainingLDAP.getIns_LIBDIPLOME());
					student.getTrainings().get(0).setPlace("Lille");

				} catch (Exception e) {
					notGoodNip=true;				
					return new ModelAndView("redirect:/authentication");
				}


				model.addAttribute("student", student);
				session.setAttribute("student", student);
				studentService.saveStudentProfile(student);

				return new ModelAndView("redirect:/home");
			}

		}else{
			session.setAttribute("referent", referent);
			return new ModelAndView("redirect:/offers");
		}
	}

	@RequestMapping(value ="/authenticationStaff", method = RequestMethod.POST)
	public ModelAndView getStaff(@ModelAttribute StaffLille1 staffLille1,BindingResult bindingresult, Model model, HttpSession session) {
		session.setAttribute("staffLille1", staffLille1);
		return new ModelAndView("redirect:/homeStaffLille1");
	}
}
