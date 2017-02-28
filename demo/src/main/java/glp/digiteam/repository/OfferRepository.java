package glp.digiteam.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import glp.digiteam.entity.offer.AbstractOffer;

public interface OfferRepository extends CrudRepository<AbstractOffer, String>{
	
	  @Query("select o from AbstractOffer o where o.status = 'Validated' order by o.moderationDate desc")
	  List<AbstractOffer> findLastOffers(Pageable pageable);

	  AbstractOffer findById(long id);
}
