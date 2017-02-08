package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.Referent;
import glp.digiteam.repository.OfferRepository;
import glp.digiteam.repository.ReferentRepository;

@Service
public class ReferentService {

	
	@Autowired
	ReferentRepository referentRepository;
	
	
	public Referent saveReferent(Referent referent){
		return referentRepository.save(referent);
	}
}
