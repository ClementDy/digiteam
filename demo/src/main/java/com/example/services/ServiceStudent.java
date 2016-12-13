package com.example.services;

import com.example.entity.Student;
import com.example.repository.StudentRepository;

public class ServiceStudent {

	StudentRepository repository;
	
	public void addStudent(Student student){
		repository.save(new Student());
	}
}
