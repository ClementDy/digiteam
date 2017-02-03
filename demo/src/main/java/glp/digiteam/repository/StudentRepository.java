package glp.digiteam.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import glp.digiteam.entity.student.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByNip(Integer nip);
    
    List<Student> findByLastName(String lastName);
}
