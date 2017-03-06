package glp.digiteam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.offer.Responsible;


public interface ResponsibleRepository extends CrudRepository<Responsible,Long>{
	
    @Query("select r from Responsible r where (r.firstName=:param1 and r.lastName=:param2) or (r.firstName=:param2 and r.lastName=:param1)")
    List<Responsible> findByNameWithParam(@Param("param1") String param1,@Param("param2") String param2);
    
    @Query("select r from Responsible r where r.firstName=:param1 or r.lastName=:param1")
    List<Responsible> findByNameWithParam(@Param("param1") String param1);


	Responsible findByEmail(String email);
	
}
