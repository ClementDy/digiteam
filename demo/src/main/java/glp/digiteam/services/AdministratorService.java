package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.Administrator;
import glp.digiteam.repository.AdministratorRepository;

@Service
public class AdministratorService {

	
	@Autowired
	AdministratorRepository administratorRepository;
	
	public Administrator saveAdministrator(Administrator administrator){
		return administratorRepository.save(administrator);
	}
}
