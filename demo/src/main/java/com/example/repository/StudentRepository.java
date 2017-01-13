package com.example.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findByNip(long nip);
    
    List<Student> findByLastName(String lastName);
}
