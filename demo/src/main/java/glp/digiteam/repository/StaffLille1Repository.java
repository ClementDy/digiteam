package glp.digiteam.repository;

import org.springframework.data.repository.CrudRepository;

import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.entity.student.Mission;

public interface StaffLille1Repository extends CrudRepository<Mission, String>{

	StaffLille1 save(StaffLille1 staffLille1);
	
	StaffLille1 findByEmail(String email);

}
