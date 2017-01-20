package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.Student;
import glp.digiteam.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public Student saveStudentProfile(Student student){
		return studentRepository.save(student);
	}
	
	public Student getStudentByNip(long nip) {
		return studentRepository.findByNip(nip);
	}
}
