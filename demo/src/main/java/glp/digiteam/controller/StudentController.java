package glp.digiteam.controller;


import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.student.Mission;
import glp.digiteam.entity.student.Student;
import glp.digiteam.services.MissionService;
import glp.digiteam.services.OfferService;
import glp.digiteam.services.StudentService;
import glp.digiteam.uploadFile.StorageFileNotFoundException;
import glp.digiteam.uploadFile.StorageService;
import glp.digiteam.validator.PublishProfileValidator;
import glp.digiteam.validator.SaveProfileValidator;

@EnableAutoConfiguration
@Controller
@ComponentScan(basePackages = "glp.digiteam.services")
public class StudentController {

	private final StorageService storageService;

	@Autowired
	private MissionService missionService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private StudentService studentService;

	@Value("${cas.client-host-url}")
	String urlredirect;
	
	@Value("${IPCV}")
	String IPCV;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {

		Student student = (Student) session.getAttribute("student");
		student=studentService.getStudentByNip(student.getNip());
		model.addAttribute("student", student);
		model.addAttribute("user", student);
		
		String pathCV = IPCV+"/"+student.getNip();
		System.out.println("************************* "+pathCV);
		model.addAttribute("pathCV", pathCV);
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

		Student student = (Student) session.getAttribute("student");
		if (studentService.getStudentByNip(student.getNip()) != null) {
			model.addAttribute("student", (studentService.getStudentByNip(student.getNip())));
			model.addAttribute("user", (studentService.getStudentByNip(student.getNip())));
			try {
				if (action.equals("unpublish")) {
					studentService.unpublishProfil(student);
				}
			} catch (Exception e) {

			}

		} else{
			model.addAttribute("student", student);
			model.addAttribute("user", student);
		}




		Iterable<AbstractOffer> abstractOffers = offerService.findLastOffers(0, 5);

		model.addAttribute("abstractOffers",abstractOffers);


		return new ModelAndView("homeStudent");
	}

	@RequestMapping(value = "/candidature", method = RequestMethod.GET)
	public ModelAndView hello(Model model, HttpSession session) {
		if (session.getAttribute("student") == null) {
			return new ModelAndView("redirect:authentication");
		}

		Student student = (Student) session.getAttribute("student");
		if (studentService.getStudentByNip(student.getNip()) != null) {
			try {
				Resource cvStudent = storageService.loadAsResource(student.getNip().toString());
				student.setCv(cvStudent.getFilename());
				model.addAttribute("file", cvStudent);
			} catch (Exception e) {
				System.out.println("No CV found");
			}


		} 

		student=studentService.getStudentByNip(student.getNip());
		model.addAttribute("student", student);
		model.addAttribute("user", student);

		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);

		String pathCV = IPCV+"/"+student.getNip();
		model.addAttribute("pathCV", pathCV);

		studentService.saveStudentProfile(student);

		return new ModelAndView("candidature::tab(activeTab='intro', error='false', saved='false')");
	}

	@RequestMapping(value = "/candidature", method = RequestMethod.POST)
	public ModelAndView addEtudiant(@ModelAttribute Student std, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file, @RequestParam(value = "action", required = true) String action,
			@RequestParam(value="currentTab", required=false, defaultValue="intro") String currentTab,
			RedirectAttributes redirectAttributes, Model model, Errors e, HttpSession session) {

		Student student = (Student) session.getAttribute("student");
		if (action.equals("Enregistrer") || action.equals("Publier")) {

			if (action.equals("Enregistrer")) {
				SaveProfileValidator saveValidator = new SaveProfileValidator();
				saveValidator.validate(std, bindingResult);
				String pathCV = IPCV+"/"+student.getNip();
				model.addAttribute("pathCV", pathCV);

				if (bindingResult.hasErrors()) {
					Iterable<Mission> missions = missionService.findAll();
					model.addAttribute("listMission", missions);

					Iterable<String> errorTabs = saveValidator.getErrorTabs(bindingResult);
					model.addAttribute("errorTabs", errorTabs);

					return new ModelAndView(
							"candidature::tab("
									+ "activeTab='" + saveValidator.getFirstErrorTab(bindingResult) + "'"
									+ ", error='true'"
									+ ", saved='false'"
									+ ")");
				}
			}

			if (action.equals("Publier")) {
				PublishProfileValidator publishValidator = new PublishProfileValidator();
				publishValidator.validate(std, bindingResult);

				if (bindingResult.hasErrors()) {
					Iterable<Mission> missions = missionService.findAll();
					model.addAttribute("listMission", missions);

					Iterable<String> errorTabs = publishValidator.getErrorTabs(bindingResult);
					model.addAttribute("errorTabs", errorTabs);

					return new ModelAndView(
							"candidature::tab("
									+ "activeTab='" + publishValidator.getFirstErrorTab(bindingResult) + "'"
									+ ", error='true'"
									+ ", saved='false'"
									+ ")");
				}
			}

			Student realStudent;

			if (studentService.getStudentByNip(student.getNip()) != null) {

				realStudent = studentService.getStudentByNip(student.getNip());

				std.getAddress().setId(realStudent.getAddress().getId());
				std.getAddress().setStudent(realStudent);

				std.getWish().setId(realStudent.getWish().getId());
				std.getWish().setStudent(realStudent);

				std.getAvailability().setId(realStudent.getAvailability().getId());
				std.getAvailability().setStudent(realStudent);

				std.setCv(realStudent.getCv());

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

			std.setNip(student.getNip());
			if(!file.getOriginalFilename().equals("")){
				std.setCv(file.getOriginalFilename());
			}


			if (!file.isEmpty()) {
				storageService.store(file, student.getNip());
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + file.getOriginalFilename() + "!");
			}


			if (action.equals("Publier")) {

				std.setStatut("published");
				model.addAttribute("student", std);
				model.addAttribute("user", student);
				studentService.saveStudentProfile(std);
				return new ModelAndView("redirect:home");

			} 
			std.setStatut("register");
			model.addAttribute("student", std);
			model.addAttribute("user", student);
			studentService.saveStudentProfile(std);
		}

		Iterable<Mission> missions = missionService.findAll();
		model.addAttribute("listMission", missions);
		return new ModelAndView(
				"candidature::tab("
						+ "activeTab='"+ currentTab + "'"
						+ ", error='false'"
						+ ", saved='true'"
						+ ")");
	}


	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	public ModelAndView showOffer(Model model, HttpSession session,@RequestParam(value = "id", required = true) long id) {

		Student student = (Student) session.getAttribute("student");
		if (session.getAttribute("student") == null) {
			return new ModelAndView("redirect:authentication");
		}

		AbstractOffer offer = offerService.findById(id);
		System.out.println(student);
		model.addAttribute("student", student);
		model.addAttribute("user", student);
		if(offer!=null){
			model.addAttribute("offer", offer);
			return new ModelAndView("offers/offerAbstract");
		}

		return new ModelAndView("homeStudent");
	}


	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public ModelAndView deconnexion(Model model, HttpSession session,final ServletRequest servletRequest, HttpServletResponse response) throws IOException {

		System.out.println("*************************************************************************");
		/*if (session.getAttribute("student") == null) {
			return new ModelAndView("redirect:authentication");
		}


		session.invalidate();*/
		/*String url = "https://sso-cas.univ-lille1.fr/logout";
		System.out.println(url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("http.agent", "");
		con.setRequestProperty("", "");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer responsebuf = new StringBuffer();
		System.out.println(responsebuf.toString());
		while ((inputLine = in.readLine()) != null) {
			responsebuf.append(inputLine);
		}
		in.close();*/
		session.invalidate();



		return new ModelAndView("redirect:https://sso-cas.univ-lille1.fr/logout?service="+urlredirect);
	}



	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
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
	public ResponseEntity<Resource> serveFile(@PathVariable String filename,HttpSession session) {

		Student student = (Student) session.getAttribute("student");
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
