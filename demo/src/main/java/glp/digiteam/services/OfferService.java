package glp.digiteam.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.Referent;
import glp.digiteam.entity.offer.Responsible;
import glp.digiteam.repository.OfferRepository;
import glp.digiteam.repository.ResponsibleRepository;



@Service
public class OfferService {

	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	private ResponsibleRepository responsibleRepository;

	
	
	public AbstractOffer saveOffer(AbstractOffer offer){
		return offerRepository.save(offer);
	}
	
	public List<AbstractOffer> findLastOffers(Pageable pageable){
		return  offerRepository.findLastOffers(pageable);
	}
	
	public List<AbstractOffer> searchOffers(String libelle,String num_offer,String responsive,String mission){
		if(libelle.isEmpty() && num_offer.isEmpty() && responsive.isEmpty() && mission.isEmpty()){
			return offerRepository.findLastOffers(new PageRequest(0, 30));
		}
		
		if(!libelle.isEmpty() && num_offer.isEmpty() && responsive.isEmpty() && mission.isEmpty()){
			return offerRepository.findOfferWithLib(libelle.toLowerCase());
		}
		
		if(libelle.isEmpty() && num_offer.isEmpty() && responsive.isEmpty() && !mission.isEmpty()){
			return offerRepository.findOfferWithMission(mission);
		}
		
		if(libelle.isEmpty() && num_offer.isEmpty() && !responsive.isEmpty() && mission.isEmpty()){
			
			String[] splited = responsive.split("\\s+");
			List<Responsible> res = null;
			
			if (splited.length > 1) {
				res =responsibleRepository.findByNameWithParam(splited[0],splited[1]);
			}
			else if (splited.length == 1) {
				res =responsibleRepository.findByNameWithParam(splited[0]);
			}
			
			if(!res.isEmpty()){
				return offerRepository.findOfferWithMResponsive(res);
			}
		}
		
		if(libelle.isEmpty() && !num_offer.isEmpty() && responsive.isEmpty() && mission.isEmpty() && isNumeric(num_offer)){
			List<AbstractOffer> offer = new ArrayList<>();
			offer.add(offerRepository.findById(Long.parseLong(num_offer)));
			return (offer);
		}
		return null;
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");
	}
}
