package glp.digiteam.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import glp.digiteam.entity.Mission;
import glp.digiteam.entity.Student;
import glp.digiteam.entity.Training;
import glp.digiteam.ldap.StudentLDAP;
import glp.digiteam.ldap.StudentLDAPService;
import glp.digiteam.ldap.TrainingLDAP;
import glp.digiteam.ldap.TrainingLDAPService;
import glp.digiteam.repository.MissionRepository;
import glp.digiteam.services.StudentService;
import glp.digiteam.uploadFile.StorageFileNotFoundException;
import glp.digiteam.uploadFile.StorageService;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class StudentController {

	private final StorageService storageService;

	@Autowired 
	private StudentLDAPService studentLDAPService;
	@Autowired
	private TrainingLDAPService trainingLDAPService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private MissionRepository missionRepository;
	//11202572
	
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String hello2(Model model) {
		return "/refound/home";
	}
	
		
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String hello(Model model,HttpSession session) {
		Student student=(Student) session.getAttribute("student") ;
		System.out.println("s"+student.getNip());

		StudentLDAP studentLDAP = studentLDAPService.getStudentLDAP(student.getNip());
		
		TrainingLDAP trainingLDAP = trainingLDAPService.getTrainingLDAP(2017, student.getNip());
		
		student.setNip(studentLDAP.getEtu_NIP());
		student.setFirstName(studentLDAP.getEtu_prenom());
		student.setLastName(studentLDAP.getEtu_nom());
		student.setNationality(studentLDAP.getEtu_libnationalite());
		student.setEmail(studentLDAP.getEtu_email());
	
		student.getTrainings().get(0).setName(trainingLDAP.getIns_LIBPARCOURS());

		model.addAttribute("student", student);

		missionRepository.save(new Mission("Accueil des étudiants"));
		missionRepository.save(new Mission("Aide à l'insertion professionelle"));
		missionRepository.save(new Mission("Animation culturelles scientifiques sportives et sociales"));
		missionRepository.save(new Mission("Assistance et accompagnement des étudiants handicapés"));
		missionRepository.save(new Mission("Enquêtes"));
		missionRepository.save(new Mission("Promotion de l'offre de formation"));
		missionRepository.save(new Mission("Secrétariat d'examens"));
		missionRepository.save(new Mission("Service d'appui aux personnels de bibliothèque"));
		missionRepository.save(new Mission("Soutien informatique et aide à l'utilisation des nouvelles technologies"));
		missionRepository.save(new Mission("Tutorat"));
		Iterable<Mission> missions = missionRepository.findAll();
		model.addAttribute("listMission", missions);

		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String addEtudiant(@Valid @ModelAttribute Student student,BindingResult bindingResult, @RequestParam("file") MultipartFile file,
	RedirectAttributes redirectAttributes, Model model, Errors e) {

		if(bindingResult.hasErrors()){
			for (ObjectError error : bindingResult.getAllErrors()) {
				System.out.println(error);
			}
			return "home";
		}
 
		System.out.println(student.getNip());
		studentService.saveStudentProfile(student);

		model.addAttribute("student", student);

		if (!file.isEmpty()) {
			storageService.store(file);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
		}

		return "result";
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
