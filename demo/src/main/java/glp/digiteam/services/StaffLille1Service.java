package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.repository.StaffLille1Repository;

@Service
public class StaffLille1Service {

	@Autowired
	StaffLille1Repository staffLille1Repository;
	
	public StaffLille1 findByEmail(String email) {
		return staffLille1Repository.findByEmail(email);
	}
	
	public StaffLille1 saveStaffLille1(StaffLille1 staffLille1){
		return staffLille1Repository.save(staffLille1);
	}
}
