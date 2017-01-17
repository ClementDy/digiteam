package com.example.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Mission;
import com.example.entity.Student;
import com.example.ldap.StudentLDAP;
import com.example.ldap.StudentLDAPService;
import com.example.ldap.TrainingLDAP;
import com.example.ldap.TrainingLDAPService;
import com.example.repository.MissionRepository;
import com.example.repository.StudentRepository;
import com.example.services.StudentService;
import com.example.uploadFile.StorageFileNotFoundException;
import com.example.uploadFile.StorageService;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "com.example.services")
public class Home {

	private StudentLDAP studentLDAP;
	
	@Autowired 
	private StudentLDAPService studentLDAPService;
	@Autowired
	private TrainingLDAPService trainingLDAPService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private MissionRepository missionRepository;

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String hello(Model model) {

		studentLDAP = studentLDAPService.getStudentLDAP(11202572);
		System.out.println(studentLDAP);
		
		TrainingLDAP trainingLDAP = trainingLDAPService.getTrainingLDAP(2017, 11202572);
		System.out.println(trainingLDAP);
		
		Student student = new Student();
		model.addAttribute("firstName", studentLDAP.getEtu_prenom());
		model.addAttribute("lastName", studentLDAP.getEtu_nom());
		model.addAttribute("email", studentLDAP.getEtu_email());
		model.addAttribute("student", student);
		model.addAttribute("nationality", studentLDAP.getEtu_libnationalite());
		model.addAttribute("training", trainingLDAP.getIns_LIBPARCOURS());

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
			return "home";
		}
 

		System.out.println(student.toString());

		student.setFirstName(studentLDAP.getEtu_prenom());
		student.setLastName(studentLDAP.getEtu_nom());
		student.setNationality(studentLDAP.getEtu_libnationalite());
		student.setEmail(studentLDAP.getEtu_email());

		studentService.saveStudentProfile(student);

		System.out.println(student.getAddress().toString());
		System.out.println();
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("mail", student.getEmail());
		model.addAttribute("phone", student.getPhone());
		model.addAttribute("street", student.getAddress().getStreet());
		model.addAttribute("city", student.getAddress().getCity());
		model.addAttribute("postalCode", student.getAddress().getPostalCode());
		model.addAttribute("date", student.getDateVisa());
		model.addAttribute("startDate", student.getAvailability().getStartDate());
		model.addAttribute("endDate", student.getAvailability().getEndDate());
		model.addAttribute("formation", student.getTraining().getName());
		System.out.println(student.getTraining().getName());
		System.out.println(student.getAvailability().getStartTimeMonday());
		model.addAttribute("startTimeMonday", student.getAvailability().getStartTimeMonday());
		model.addAttribute("endTimeMonday", student.getAvailability().getEndTimeMonday());
		System.out.println(student.getDateVisa());

		// CCCCCCCVVVVVVVVVVVVvvv
		if (!file.isEmpty()) {
			storageService.store(file);
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
		}

		return "result";
	}

	@RequestMapping(value = "/resultCedric", method = RequestMethod.POST)
	public String addEtudiant(Student student, Model model) {
		model.addAttribute("lastName", student.getLastName());
		model.addAttribute("firstName", student.getFirstName());
		model.addAttribute("motivations", student.getMotivation());
		studentRepository.save(new Student(student.getFirstName(), student.getLastName(), student.getPhone(),
				student.getEmail(), student.getNationality(), student.getMotivation(), null));
		return "resultCedric";
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
	private final StorageService storageService;

	@Autowired
	public Home(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/lol")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));

		return "CVMaggle";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/CV")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "CVMaggle";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
