package glp.digiteam.services;

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
}
