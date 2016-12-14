package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.example.entity.Student;
import com.example.repository.StudentRepository;


public class ServiceStudent {

	@Autowired
	StudentRepository repository;
	
	@Bean
	public void addStudent(Student student){
		System.out.println("ADD");
		System.out.println(student.toString());
		repository.save(student);
		//repository.save(new Address(student.getStreet(),student.getComplement(),student.getPostalCode(),student.getCity(),studentT));
	}
}
