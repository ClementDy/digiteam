package glp.digiteam.repository;

import org.springframework.data.repository.CrudRepository;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.student.Address;

public interface OfferRepository extends CrudRepository<AbstractOffer, String>{
	
	
}
