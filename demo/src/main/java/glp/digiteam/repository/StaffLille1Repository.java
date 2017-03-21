package glp.digiteam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Mission;

public interface StaffLille1Repository extends CrudRepository<StaffLille1, String>{

	StaffLille1 save(StaffLille1 staffLille1);
	
	StaffLille1 findByEmail(String email);
	
	@Query("select s from StaffLille1 s where (s.service.code=:service)")
	List<StaffLille1> findByService(@Param("service") String service);

	@Query("select count(s) from StaffLille1 s ")
	int nbLille1();

	@Query("select count(s) from StaffLille1 s where s.isReferent=1")
	int getNbReferents();

	
	@Query("select count(s) from StaffLille1 s where s.isModerator=1")
	int getNbModerator();

}
