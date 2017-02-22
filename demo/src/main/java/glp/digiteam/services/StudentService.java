package glp.digiteam.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.student.Student;
import glp.digiteam.entity.student.Training;
import glp.digiteam.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public Student saveStudentProfile(Student student){
		return studentRepository.save(student);
	}
	
	public Student getStudentByNip(Integer nip) {
		if(studentRepository.findByNip(nip)!=null){
			return studentRepository.findByNip(nip);
		}
		return null;
	}
	
	public void unpublishProfil(Student student){
		student.setStatut("register");
		studentRepository.save(student);
	}
	
	public List<Student> getAllCandidature(){
		List<Student> allStudent = studentRepository.findPublishedCandidature();
		return allStudent;
	}
	
	public List<Student> findWithParameter(String firstName, String lastName, String formation){
		
			List<Student> allStudent =studentRepository.findWithFirstNameLastName(firstName, lastName);
			List<Student> students = new ArrayList<>();
			if(!formation.isEmpty()){
				for (Student student : allStudent) {
					List<Training> t = student.getTrainings();
					for (Training training : t) {
						if(training.getName().contains(formation)){
							students.add(student);
						}
					}
				}
				return students;
			}
			return students;
	}
	
	public List<Student> findByFirstNamePublish(String firstName){
		return studentRepository.findByFirstNamePublish(firstName);
	}
	
	public List<Student> findByLastNamePublish(String lastName){
		return studentRepository.findByLastNamePublish(lastName);
	}
	
	public List<Student> findWithTraining(String formation){
		List<Student> allStudent = studentRepository.findPublishedCandidature();
		List<Student> students = new ArrayList<>();
		for (Student student : allStudent) {
			List<Training> t = student.getTrainings();
			for (Training training : t) {
				if(training.getName().contains(formation.toUpperCase())){
					students.add(student);
				}
			}
		}
		return students;
	}
	
}
