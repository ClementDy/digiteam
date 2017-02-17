package glp.digiteam.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.student.Student;
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
		Iterable<Student> allStudent = studentRepository.findAll();
		List<Student> candidaturePublished = new ArrayList<>();
		for (Student student : allStudent) {
			if(student.getStatut()!=null && student.getStatut().equals("published")){
				candidaturePublished.add(student);
			}
		}
		
		return candidaturePublished;
	}
}
