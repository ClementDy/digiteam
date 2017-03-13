package glp.digiteam.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.StaffLille1;
import glp.digiteam.repository.OfferRepository;


@Service
public class OfferService {

	@Autowired
	OfferRepository offerRepository;
		
	
	public AbstractOffer findById(long id) {
		return offerRepository.findById(id);
	}
	
	public List<AbstractOffer> findLastOffers(int offset, int limit) {
		return offerRepository.findLastOffers(new PageRequest(offset, limit));
	}
	
	public AbstractOffer saveOffer(AbstractOffer offer){
		return offerRepository.save(offer);
	}
	
	public void dispublish(long id, StaffLille1 ref){
		AbstractOffer offer = offerRepository.findById(id);
		if(offer!=null){
			if(offer.getReferent()==ref){
				offer.setStatus("Expired");
				offerRepository.save(offer);	
			}
		}
	}
	
	public void removeOffer(long id, StaffLille1 ref){
		AbstractOffer offer = offerRepository.findById(id);
		if(offer!=null){
			if(offer.getReferent()==ref){
				offerRepository.delete(offer);
			}
		}
	}
	
	public List<AbstractOffer> findLastOffers(Pageable pageable){
		return  offerRepository.findLastOffers(pageable);
	}
	
	public List<AbstractOffer> searchOffers(String libelle,String num_offer){
		
		if(libelle.isEmpty() && num_offer.isEmpty()){
			return offerRepository.findLastOffers(new PageRequest(0, 40));
		}
		
		if(!libelle.isEmpty() && num_offer.isEmpty() ){
			return offerRepository.findOfferWithLib(libelle.toLowerCase());
		}
		
		if(!libelle.isEmpty() &&  !num_offer.isEmpty()  && isNumeric(num_offer)){
			return offerRepository.findOfferWithLibOffer(libelle.toLowerCase(),Long.parseLong(num_offer));
		}
		
		if(!libelle.isEmpty() &&  !num_offer.isEmpty()  && isNumeric(num_offer)){
		
			
				return offerRepository.findOfferWithAllParam(libelle.toLowerCase(),Long.parseLong(num_offer));
			
		}
		
		if(libelle.isEmpty() && !num_offer.isEmpty() && isNumeric(num_offer)){
			List<AbstractOffer> offer = new ArrayList<>();
			AbstractOffer offerFind = offerRepository.findById(Long.parseLong(num_offer));
			if(offerFind!=null){
				offer.add(offerFind);
			}
			return (offer);
		}
		
		return null;
	}
	
	public static boolean isNumeric(String str) {
	  return str.matches("-?\\d+(\\.\\d+)?");
	}
}
