package glp.digiteam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;
import glp.digiteam.repository.MissionRepository;
import glp.digiteam.services.StudentService;
import glp.digiteam.uploadFile.StorageFileNotFoundException;
import glp.digiteam.uploadFile.StorageService;
import glp.digiteam.validator.StudentValidator;
import glp.digiteam.webServices.StudentWebService;
import glp.digiteam.webServices.StudentWebServiceService;
import glp.digiteam.webServices.TrainingWebService;
import glp.digiteam.webServices.TrainingWebServiceService;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class StudentController {

	private final StorageService storageService;

	Student student;

	@Autowired
	private StudentWebServiceService studentLDAPService;
	@Autowired
	private TrainingWebServiceService trainingLDAPService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private MissionRepository missionRepository;
	// 11202572



	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {
		student=studentService.getStudentByNip(student.getNip());
		model.addAttribute("student", student);
		return "profile";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView redirectslash(Model model, HttpSession session) {

		return new ModelAndView("redirect:authentication");
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homeStudent(Model model, HttpSession session,
			@RequestParam(value = "action", required = false) String action) {

		if (session.getAttribute("student") == null) {
			return new ModelAndView("redirect:authentication");
		}

		student = (Student) session.getAttribute("student");

		if (studentService.getStudentByNip(student.getNip()) == null) {
			StudentWebService studentLDAP = /* new StudentLDAP(); */ studentLDAPService.getStudentLDAP(student.getNip());
			// TrainingLDAP trainingLDAP = /*new TrainingLDAP();*/
			// trainingLDAPService.getTrainingLDAP(2017, student.getNip());
			student.setCivilite(studentLDAP.getEtu_civilite());
			student.setFirstName(studentLDAP.getEtu_prenom());
			student.setLastName(studentLDAP.getEtu_nom());

		} else {
			student = studentService.getStudentByNip(student.getNip());
			try {
				if (action.equals("unpublish")) {
					studentService.unpublishProfil(student);
				}
			} catch (Exception e) {

			}
		}

		model.addAttribute("student", student);

		return new ModelAndView("homeStudent");
	}

	@RequestMapping(value = "/candidature", method = RequestMethod.GET)
	public ModelAndView hello(Model model, HttpSession session) {
		if (session.getAttribute("student") == null) {
			return new ModelAndView("redirect:authentication");
		}

		student = (Student) session.getAttribute("student");

		if (studentService.getStudentByNip(student.getNip()) == null) {
			System.out.println("Home GET Dans le premier if");
			StudentWebService studentLDAP = /* new StudentLDAP(); */ studentLDAPService.getStudentLDAP(student.getNip());

			TrainingWebService trainingLDAP = /* new TrainingLDAP(); */ trainingLDAPService.getTrainingLDAP(2017,
					student.getNip());
			System.out.println("Home GET Apres la recup LDAP");
			student.setFirstName(studentLDAP.getEtu_prenom());
			student.setLastName(studentLDAP.getEtu_nom());
			student.setNationality(studentLDAP.getEtu_libnationalite());
			student.setEmail(studentLDAP.getEtu_email());
			student.setCivilite(studentLDAP.getEtu_civilite());
			student.getTrainings().get(0).setDate(trainingLDAP.getIns_ANNEE());
			student.getTrainings().get(0).setName(trainingLDAP.getIns_LIBDIPLOME());
			student.getTrainings().get(0).setPlace("Lille");
			System.out.println("Home GET fin du if");

		} else {
			System.out.println("dans le else");
			student = studentService.getStudentByNip(student.getNip());

			try {
				Resource cvStudent = storageService.loadAsResource(student.getNip().toString());
				model.addAttribute("file", cvStudent);
			} catch (Exception e) {
				System.out.println("pas de cv trouvé");
			}

		}

		model.addAttribute("student", student);
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);
		studentService.saveStudentProfile(student);
		return new ModelAndView("candidature::tab(activeTab='intro')");
	}

	@RequestMapping(value = "/candidature", method = RequestMethod.POST)
	public ModelAndView addEtudiant(@ModelAttribute Student std, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file, @RequestParam(value = "action", required = true) String action,
			RedirectAttributes redirectAttributes, Model model, Errors e, HttpSession session) {

		if (action.equals("Publier") || action.equals("Enregistrer")) {

			if (action.equals("Publier")) {
				StudentValidator studentValidator = new StudentValidator();
				studentValidator.validate(std, bindingResult);

				if (bindingResult.hasErrors()) {
					Iterable<Mission> missions = missionRepository.findAll();
					model.addAttribute("listMission", missions);
					return new ModelAndView("candidature::tab(activeTab='intro')");
				}
			}

			Student realStudent;

			if (studentService.getStudentByNip(this.student.getNip()) != null) {

				realStudent = studentService.getStudentByNip(this.student.getNip());

				std.getAddress().setId(realStudent.getAddress().getId());
				std.getAddress().setStudent(realStudent);

				std.getWish().setId(realStudent.getWish().getId());
				std.getWish().setStudent(realStudent);

				std.getAvailability().setId(realStudent.getAvailability().getId());
				std.getAvailability().setStudent(realStudent);

				std.getMisc().setId(realStudent.getMisc().getId());
				std.getMisc().setStudent(realStudent);

				for (int i = 0; i < std.getTrainings().size(); i++) {
					std.getTrainings().get(i).setId(realStudent.getTrainings().get(i).getId());
					std.getTrainings().get(i).setStudent(realStudent);
				}
				for (int i = 0; i < std.getExternalContracts().size(); i++) {
					std.getExternalContracts().get(i).setId(realStudent.getExternalContracts().get(i).getId());
					std.getExternalContracts().get(i).setStudent(realStudent);
				}

			}

			std.setNip(this.student.getNip());
			std.setCv(file.getOriginalFilename());

			if (!file.isEmpty()) {
				storageService.store(file, student.getNip());
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + file.getOriginalFilename() + "!");
			}
			

			if (action.equals("Publier")) {

				std.setStatut("published");
				model.addAttribute("student", std);
				studentService.saveStudentProfile(std);
				return new ModelAndView("redirect:home");

			} 
			std.setStatut("register");
			model.addAttribute("student", std);
			studentService.saveStudentProfile(std);
		}
		
		return new ModelAndView("candidature::tab(activeTab='intro')");
	}

	/*
	 * @InitBinder private void dateBinder(WebDataBinder binder) {
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 * //Create a new CustomDateEditor CustomDateEditor editor = new
	 * CustomDateEditor(dateFormat, true);
	 * binder.registerCustomEditor(Date.class, editor); }
	 * 
	 * 
	 * @InitBinder public void initBinder(final WebDataBinder binder){ final
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	 * binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
	 * true)); }
	 */
	// CV

	@Autowired
	public StudentController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		student.setCv(filename);
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
