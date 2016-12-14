package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.example.entity.Miscellaneous;
import com.example.entity.Student;
import com.example.repository.MiscellaneousRepository;
import com.example.repository.StudentRepository;


public class ServiceStudent {

	@Autowired
	StudentRepository repository;
	
	@Autowired
	MiscellaneousRepository miscrepository;
	
	@Bean
	public void addStudent(Student student){
		System.out.println("ADD");
		System.out.println(student.toString());
		repository.save(student);
		//repository.save(new Address(student.getStreet(),student.getComplement(),student.getPostalCode(),student.getCity(),studentT));
	}
	@Bean
	public void addMiscellaneous(Miscellaneous misc){

		//Student studentT=new Student(student.getFirstName(), student.getLastName(), student.getPhone(),

		//		student.getEmail(), student.getNationality());

		
		System.out.println(misc.getNameAssociation()+" "+misc.getItKnowledge()+" "+

				misc.getLanguages()+" "+misc.getOtherFormations());

		
		/*repository.save(new Student(student.getFirstName(), student.getLastName(), student.getPhone(),

				student.getEmail(), student.getNationality()));/*/

		miscrepository.save(misc);

	}
}
