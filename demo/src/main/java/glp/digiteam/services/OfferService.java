package glp.digiteam.services;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<AbstractOffer> findLast5Offers(){
		 List<AbstractOffer> listOffers = offerRepository.findLast5Offers();
		 List<AbstractOffer> list5Offers  = new ArrayList<>();
		 int i=0;
		 while(i<listOffers.size()-1 || list5Offers.size()<5) {
			if(listOffers.get(i).getStatus().equals("Validated")){
				list5Offers.add(listOffers.get(i));
			}
		}
		return listOffers;
	}
}
