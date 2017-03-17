package glp.digiteam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.repository.StaffLille1Repository;

@Service
public class StaffLille1Service {

	@Autowired
	private StaffLille1Repository staffLille1Repository;
	
	public Iterable<StaffLille1> findAll() {
		return staffLille1Repository.findAll();
	}
	
	public StaffLille1 findByEmail(String email) {
		return staffLille1Repository.findByEmail(email);
	}
	
	public StaffLille1 saveStaffLille1(StaffLille1 staffLille1){
		return staffLille1Repository.save(staffLille1);
	}
	
	public List<StaffLille1> findByService(String service){
		return staffLille1Repository.findByService(service);
	}
}
