package glp.digiteam.repository;

import org.springframework.data.repository.CrudRepository;

import glp.digiteam.entity.offer.Administrator;
import glp.digiteam.entity.student.Student;

public interface AdministratorRepository extends CrudRepository<Administrator, String>{

    Administrator findByName(String name);
    

}
