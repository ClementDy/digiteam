package com.example.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByLastName(String lastName);

}
