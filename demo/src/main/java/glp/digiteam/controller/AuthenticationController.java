package glp.digiteam.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import glp.digiteam.entity.offer.ServiceEntity;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Student;
import glp.digiteam.services.ServiceService;
import glp.digiteam.services.StaffLille1Service;
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
	
	@Autowired
	private StaffLille1Service staffLille1Service;

	boolean notGoodNip = false;

	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public ModelAndView authenficationStudent(Model model, final ServletRequest servletRequest,
			HttpServletResponse response, HttpSession session) throws JSONException {
		System.out.println("**********************************************************************************");
		final HttpServletRequest request = (HttpServletRequest) servletRequest;

		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();

		Map<String, Object> attributes = principal.getAttributes();

		Iterator attributeNames = attributes.keySet().iterator();

		
		while (attributeNames.hasNext()) {
			   String attributeName = (String) attributeNames.next();

	            System.out.print(attributeName + " : ");
	            System.out.println(attributes.get(attributeName));
	            
		}
		
		/// *
				List<ServiceWebService> services = servicewebsrviceservice.getServicesWS();

				for (ServiceWebService serviceWebService : services) {
					ServiceEntity servicentity = new ServiceEntity(serviceWebService.getCode(), serviceWebService.getLibelle());
					serviceservice.saveService(servicentity);
				}

				// */
				if(attributes.get("nip") == null|| attributes.get("nip").equals("11602419")|| attributes.get("nip").equals("11202572")|| attributes.get("nip").equals("11302480")/*|| attributes.get("nip").equals("11203333")*/){
					StaffLille1 staffLille1 = new StaffLille1();
					staffLille1.setEmail((String) attributes.get("mail"));
					staffLille1.setFirstName((String) attributes.get("givenname"));
					staffLille1.setLastName((String) attributes.get("sn"));
					session.setAttribute("staffLille1", staffLille1);
					if(staffLille1Service.findByEmail(staffLille1.getEmail())==null){
					staffLille1Service.saveStaffLille1(staffLille1);
					}
					return new ModelAndView("redirect:homeStaffLille1");
				}else{
		

			Student student = new Student();
			student.setNip(Integer.parseInt((String) attributes.get("nip")));
			student.setEmail((String) attributes.get("mail"));
			student.setFirstName((String) attributes.get("givenname"));
			student.setLastName((String) attributes.get("sn"));
			
			StudentWebService studentLDAP = studentLDAPService.getStudentLDAP(student.getNip());
			TrainingWebService trainingLDAP = trainingLDAPService.getTrainingLDAP(2017, student.getNip());

			student.setCivilite(studentLDAP.getEtu_civilite());
			/*
			 * student.setFirstName(studentLDAP.getEtu_prenom());
			 * student.setLastName(studentLDAP.getEtu_nom());
			 * student.setEmail(studentLDAP.getEtu_email());
			 */
			student.setNationality(studentLDAP.getEtu_libnationalite());
			student.getTrainings().get(0).setDate(trainingLDAP.getIns_ANNEE());
			student.getTrainings().get(0).setName(trainingLDAP.getIns_LIBDIPLOME());
			student.getTrainings().get(0).setPlace("Lille");
			session.setAttribute("student", student);
			if(studentService.getStudentByNip(student.getNip())==null){
				studentService.saveStudentProfile(student);
			}
			
			return new ModelAndView("redirect:home");
			
		} 
		

		/*if (attributes.get("nip").equals("11302480")) {

			
		}*/
		
	}

	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public ModelAndView getStudent(@Valid @ModelAttribute Student student, WebRequest request,
			BindingResult bindingresult, Model model, HttpSession session, SessionStatus status) {

		if (student.getNip() != null) {

			if (studentService.getStudentByNip(student.getNip()) != null) {
				student = studentService.getStudentByNip(student.getNip());
				model.addAttribute("student", student);
				session.setAttribute("student", student);
				request.removeAttribute("staffLille1", WebRequest.SCOPE_SESSION);
				notGoodNip = false;
				return new ModelAndView("redirect:/home");
			} else {
				try {
					/*
					 * 
					 * StudentWebService studentLDAP =
					 * studentLDAPService.getStudentLDAP(student.getNip());
					 * TrainingWebService trainingLDAP =
					 * trainingLDAPService.getTrainingLDAP(2017,
					 * student.getNip());
					 * 
					 * student.setCivilite(studentLDAP.getEtu_civilite());
					 * /*student.setFirstName(studentLDAP.getEtu_prenom());
					 * student.setLastName(studentLDAP.getEtu_nom());
					 * student.setEmail(studentLDAP.getEtu_email());
					 * student.setNationality(studentLDAP.getEtu_libnationalite(
					 * )); student.getTrainings().get(0).setDate(trainingLDAP.
					 * getIns_ANNEE());
					 * student.getTrainings().get(0).setName(trainingLDAP.
					 * getIns_LIBDIPLOME());
					 * student.getTrainings().get(0).setPlace("Lille");
					 */
				} catch (Exception e) {
					notGoodNip = true;
					return new ModelAndView("redirect:/authentication");
				}

				model.addAttribute("student", student);
				session.setAttribute("student", student);
				studentService.saveStudentProfile(student);
				request.removeAttribute("staffLille1", WebRequest.SCOPE_SESSION);

				return new ModelAndView("redirect:/home");
			}

		}
		return null;
	}

	@RequestMapping(value = "/authenticationStaff", method = RequestMethod.POST)
	public ModelAndView getStaff(@ModelAttribute StaffLille1 staffLille1, BindingResult bindingresult, Model model,
			HttpSession session) {
		System.out.println(staffLille1.getEmail());
		session.setAttribute("staffLille1", staffLille1);
		return new ModelAndView("redirect:/homeStaffLille1");
	}

}
