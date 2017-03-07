package glp.digiteam.services;

import static org.mockito.Mockito.ignoreStubs;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.GenericOffer;
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
	
	public void dispublish(long id, Referent ref){
		AbstractOffer offer = offerRepository.findById(id);
		if(offer!=null){
			if(offer.getResponsible().getReferent()==ref){
				offer.setStatus("Expired");
				offerRepository.save(offer);	
			}
		}
	}
	
	public void removeOffer(long id, Referent ref){
		AbstractOffer offer = offerRepository.findById(id);
		if(offer!=null){
			if(offer.getResponsible().getReferent()==ref){
				offerRepository.delete(offer);
			}
		}
	}
	
	public List<AbstractOffer> findLastOffers(Pageable pageable){
		return  offerRepository.findLastOffers(pageable);
	}
	
	public List<AbstractOffer> searchOffers(String libelle,String num_offer,String responsive){
		
		if(libelle.isEmpty() && num_offer.isEmpty() && responsive.isEmpty()){
			return offerRepository.findLastOffers(new PageRequest(0, 40));
		}
		
		if(!libelle.isEmpty() && num_offer.isEmpty() && responsive.isEmpty()){
			return offerRepository.findOfferWithLib(libelle.toLowerCase());
		}
		
		if(!libelle.isEmpty() &&  !num_offer.isEmpty() && responsive.isEmpty() && isNumeric(num_offer)){
			return offerRepository.findOfferWithLibOffer(libelle.toLowerCase(),Long.parseLong(num_offer));
		}
		
		if(!libelle.isEmpty() &&  !num_offer.isEmpty() && !responsive.isEmpty() && isNumeric(num_offer)){
			String[] splited = responsive.split("\\s+");
			List<Responsible> res = null;
			
			if (splited.length > 1) {
				res =responsibleRepository.findByNameWithParam(splited[0],splited[1]);
			}
			else if (splited.length == 1) {
				res =responsibleRepository.findByNameWithParam(splited[0]);
			}
			
			if(!res.isEmpty()){
				return offerRepository.findOfferWithAllParam(libelle.toLowerCase(),Long.parseLong(num_offer),res);
			}
		}
		
		if(libelle.isEmpty() && num_offer.isEmpty() && !responsive.isEmpty()){
			
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
		
		if(!libelle.isEmpty() &&  num_offer.isEmpty() && !responsive.isEmpty()){
			String[] splited = responsive.split("\\s+");
			List<Responsible> res = null;
			
			if (splited.length > 1) {
				res =responsibleRepository.findByNameWithParam(splited[0],splited[1]);
			}
			else if (splited.length == 1) {
				res =responsibleRepository.findByNameWithParam(splited[0]);
			}
			
			if(!res.isEmpty()){
				return offerRepository.findOfferWithResLib(libelle.toLowerCase(),res);
			}
		}
		
		if(libelle.isEmpty() &&  !num_offer.isEmpty() && !responsive.isEmpty() && isNumeric(num_offer)){
			String[] splited = responsive.split("\\s+");
			List<Responsible> res = null;
			
			if (splited.length > 1) {
				res =responsibleRepository.findByNameWithParam(splited[0],splited[1]);
			}
			else if (splited.length == 1) {
				res =responsibleRepository.findByNameWithParam(splited[0]);
			}
			
			if(!res.isEmpty()){
				return offerRepository.findOfferWithResOffer(Long.parseLong(num_offer),res);
			}
		}
		
		if(libelle.isEmpty() && !num_offer.isEmpty() && responsive.isEmpty() && isNumeric(num_offer)){
			List<AbstractOffer> offer = new ArrayList<>();
			AbstractOffer offerFind = offerRepository.findById(Long.parseLong(num_offer));
			if(offerFind!=null){
				offer.add(offerFind);
			}
			return (offer);
		}
		return null;
	}
	
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");
	}
}
