package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

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
