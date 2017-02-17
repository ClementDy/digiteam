package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.Moderator;
import glp.digiteam.repository.ModeratorRepository;

@Service
public class ModeratorService {

	@Autowired
	ModeratorRepository moderatorRepository;
	
	public Moderator saveModerator(Moderator moderator){
		return moderatorRepository.save(moderator);
	}
}
