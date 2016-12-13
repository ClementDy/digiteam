package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.example.FormulaireStudent;
import com.example.entity.Address;
import com.example.entity.Student;
import com.example.repository.StudentRepository;

public class ServiceStudent {

	@Autowired
	StudentRepository repository;
	
	@Bean
	public void addStudent(FormulaireStudent student){
		//Student studentT=new Student(student.getFirstName(), student.getLastName(), student.getPhone(),
		//		student.getEmail(), student.getNationality());
		System.out.println(student.getFirstName()+"" + student.getLastName()+"" + student.getPhone()+"" +
		student.getEmail()+"" + student.getNationality());
		repository.save(new Student(student.getFirstName(), student.getLastName(), student.getPhone(),
				student.getEmail(), student.getNationality()));
		//repository.save(new Address(student.getStreet(),student.getComplement(),student.getPostalCode(),student.getCity(),studentT));
	}
}
