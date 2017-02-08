package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.Responsible;
import glp.digiteam.repository.ResponsibleRepository;

@Service
public class ResponsibleService {

	
	@Autowired
	ResponsibleRepository responsibleRepository;
	
	
	public Responsible saveResponsible(Responsible responsible){
		return responsibleRepository.save(responsible);
	}
}
