package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Miscellaneous;
import com.example.entity.Student;import com.example.repository.MiscellaneousRepository;
import com.example.services.ServiceStudent;

@EnableAutoConfiguration
@Controller
public class HomeAlex {
	
	
	ServiceStudent serviceStudent=new ServiceStudent();
	@Autowired
	MiscellaneousRepository miscrepository;
	
	@RequestMapping(value="/form_divers", method = RequestMethod.GET)
	public String form_divers(Model model) {
		Student student=new Student();
		
		model.addAttribute("student",student);
		
		return "form_divers";
	}

	@RequestMapping(value="/form_formations", method = RequestMethod.GET)
	public String form_formations(Model model) {
		Student student=new Student();
		
		model.addAttribute("student",student);
		
		return "form_formations";
	}
	
	@RequestMapping(value = "/resultAlex", method = RequestMethod.POST)
	public String addEtudiant(Student student, Model model) {
		/*System.out.println(student.getMisc().isAssociation()+" "+ student.getMisc().getNameAssociation()+" "+student.getMisc().getItKnowledge()+" "+
				student.getMisc().getLanguages()+" "+student.getMisc().getOtherFormations());*/
		model.addAttribute("nameAssociation", student.getMisc().getNameAssociation());
		model.addAttribute("itKnowledge", student.getMisc().getNameAssociation());
		model.addAttribute("languages", student.getMisc().getLanguages());
		model.addAttribute("otherFormations", student.getMisc().getOtherFormations());

		miscrepository.save(student.getMisc());
		return "resultAlex";
	}
}
