package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.entity.Student;


@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner test(StudentRepository repository) {
		return (args) -> {
			// save a couple of Students
			repository.save(new Student("Jack", "Bauer"));
			repository.save(new Student("Chloe", "O'Brian"));
			repository.save(new Student("Kim", "Bauer"));
			repository.save(new Student("David", "Palmer"));
			repository.save(new Student("Michelle", "Dessler"));

			// fetch all Students
			log.info("Students found with findAll():");
			log.info("-------------------------------");
			for (Student Student : repository.findAll()) {
				log.info(Student.toString());
			}
			log.info("");

			// fetch an individual Student by ID
			Student Student = repository.findOne(1L);
			log.info("Student found with findOne(1L):");
			log.info("--------------------------------");
			log.info(Student.toString());
			log.info("");

			// fetch Students by last name
			log.info("Student found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Student bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

}
