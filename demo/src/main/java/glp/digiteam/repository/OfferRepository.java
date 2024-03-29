package glp.digiteam.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import glp.digiteam.entity.offer.AbstractOffer;

public interface OfferRepository extends CrudRepository<AbstractOffer, String>{

	@Query("select o from AbstractOffer o where o.status = 'Validated' order by o.moderationDate desc")
	List<AbstractOffer> findLastOffers(Pageable pageable);

	@Query("select o from AbstractOffer o where o.status = 'Validated' and o.title  LIKE CONCAT('%',:param1,'%') "
			+ "order by o.moderationDate desc")
	List<AbstractOffer> findOfferWithLib(@Param("param1") String param1);

	@Query("select o from AbstractOffer o where o.status = 'Validated' and o.title  "
			+ "LIKE CONCAT('%',:param1,'%') and o.id=:param2 order by o.moderationDate desc")
	List<AbstractOffer> findOfferWithLibOffer (@Param("param1") String param1,@Param("param2") Long  param2);

	@Query(" select o from AbstractOffer o where o.status = 'Validated' and o.title  "
			+ "LIKE CONCAT('%',:param1,'%') and o.id=:param2 order by o.moderationDate desc")
	List<AbstractOffer> findOfferWithAllParam(@Param("param1") String param1,
			@Param("param2") Long  param2);

	@Query(" select o from AbstractOffer o where o.status = 'Validated' and o.title  "
			+ "LIKE CONCAT('%',:param1,'%') order by o.moderationDate desc")
	List<AbstractOffer> findOfferWithResLib(@Param("param1") String param1);


	@Query(" select o from AbstractOffer o where o.status = 'Validated' and o.id=:param2 order by o.moderationDate desc")
	List<AbstractOffer> findOfferWithResOffer( 
			@Param("param2") Long  param2);
	
	@Query(" select count(o) from AbstractOffer o where o.status = 'Validated'")
	int getNbOffersPublished();
	
	@Query(" select count(o) from AbstractOffer o where o.status = 'Refused'")
	int getNbOffersRefused();
	
	@Query(" select count(o) from AbstractOffer o where o.status = 'Expired'")
	int getNbOffersPassed();
	
	@Query(" select count(o) from AbstractOffer o where o.status = 'Waiting'")
	int getNbOffersAttente();
	
	@Query(" select count(o) from AbstractOffer o")
	int getNbOffers();
	
	AbstractOffer findById(long id);
}
