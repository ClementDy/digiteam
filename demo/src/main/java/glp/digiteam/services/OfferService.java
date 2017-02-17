package glp.digiteam.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	
	public List<AbstractOffer> findLastOffers(Pageable pageable){
		return  offerRepository.findLastOffers(pageable);
	}
}
