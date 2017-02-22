package glp.digiteam.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import glp.digiteam.entity.student.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findByNip(Integer nip);
    
    List<Student> findByLastName(String lastName);
    
    @Query("select s from Student s where s.firstName=:param1 and s.statut='published' ")
    List<Student> findByFirstNamePublish(@Param("param1") String param1);
    
    @Query("select s from Student s where s.lastName=:param1 and s.statut='published' ")
    List<Student> findByLastNamePublish(@Param("param1") String param1);
    
    
	@Query("select s from Student s where ( s.firstName =:param1 and s.lastName=:param2 and s.statut='published')")
    List<Student> findWithFirstNameLastName(@Param("param1") String param1,@Param("param2") String param2);
    
	@Query("select s from Student s where s.statut='published' ")
    List<Student> findPublishedCandidature();
}
