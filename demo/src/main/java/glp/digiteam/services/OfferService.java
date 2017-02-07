package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.repository.OfferRepository;

@Service
public class OfferService {

	
	@Autowired
	OfferRepository offerRepository;
	
	
	public AbstractOffer saveOffer(AbstractOffer offer){
		return offerRepository.save(offer);
	}
}
