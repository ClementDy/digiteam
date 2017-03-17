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
		
	
	public Iterable<AbstractOffer> findAll() {
		return offerRepository.findAll();
	}
	
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
			if(offer.getReferent()==ref && offer.getStatus().equals("Validated") ){
				offer.setStatus("Expired");
				offerRepository.save(offer);	
			}
		}
	}
	
	public void removeOffer(long id, StaffLille1 ref){
		AbstractOffer offer = offerRepository.findById(id);
		if(offer!=null){
			if(offer.getReferent()==ref && offer.getStatus().equals("Refused")){
				offerRepository.delete(offer);
			}
		}
	}
	
	public List<AbstractOffer> findLastOffers(Pageable pageable){
		return  offerRepository.findLastOffers(pageable);
	}
	
	public List<AbstractOffer> searchOffers(String libelle,String mission){
		List<AbstractOffer> offers=null;
		List<AbstractOffer>returnedOffers=new ArrayList<AbstractOffer>();
		if(libelle.isEmpty()&&mission.isEmpty()){
			return offerRepository.findLastOffers(new PageRequest(0, 40));
		}
		
		if(!libelle.isEmpty()&&mission.isEmpty()){
			return offerRepository.findOfferWithLib(libelle.toLowerCase());
		}
		if(libelle.isEmpty()&&!mission.isEmpty()){
			offers=offerRepository.findLastOffers(new PageRequest(0, 40));
			for (AbstractOffer abstractOffer : offers) {
				if(abstractOffer.getType().equals(mission)){
					returnedOffers.add(abstractOffer);
				}
			}
			return returnedOffers;
		}
		if(!libelle.isEmpty()&&!mission.isEmpty()){
			offers=offerRepository.findOfferWithLib(libelle.toLowerCase());
			for (AbstractOffer abstractOffer : offers) {
				if(abstractOffer.getType().equals(mission)){
					returnedOffers.add(abstractOffer);
				}
			}
			return returnedOffers;
		}
		
		return null;
	}
	
	public static boolean isNumeric(String str) {
	  return str.matches("-?\\d+(\\.\\d+)?");
	}
}
