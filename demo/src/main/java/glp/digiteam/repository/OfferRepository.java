package glp.digiteam.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import glp.digiteam.entity.offer.AbstractOffer;
import glp.digiteam.entity.offer.Responsible;

public interface OfferRepository extends CrudRepository<AbstractOffer, String>{
	
	  @Query("select o from AbstractOffer o where o.status = 'Validated' order by o.moderationDate desc")
	  List<AbstractOffer> findLastOffers(Pageable pageable);

	  @Query("select o from AbstractOffer o where o.status = 'Validated' and o.title  LIKE CONCAT('%',:param1,'%') "
	  		+ "order by o.moderationDate desc")
	  List<AbstractOffer> findOfferWithLib(@Param("param1") String param1);

	  @Query("select o from AbstractOffer o where o.status = 'Validated' and o.responsible in :param1)")
	  List<AbstractOffer> findOfferWithMResponsive(@Param("param1") List<Responsible> param1);
	  
	  @Query("select o from AbstractOffer o where o.status = 'Validated' and o.title  "
	  		+ "LIKE CONCAT('%',:param1,'%') and o.id=:param2 order by o.moderationDate desc")
	  List<AbstractOffer> findOfferWithLibOffer (@Param("param1") String param1,@Param("param2") Long  param2);
	  
	  @Query(" select o from AbstractOffer o where o.status = 'Validated' and o.title  "
	  		+ "LIKE CONCAT('%',:param1,'%') and o.id=:param2 and o.responsible in :param3 order by o.moderationDate desc")
	  List<AbstractOffer> findOfferWithAllParam(@Param("param1") String param1,
			  @Param("param2") Long  param2,
			  @Param("param3") List<Responsible> param3);
	  
	  @Query(" select o from AbstractOffer o where o.status = 'Validated' and o.title  "
		  		+ "LIKE CONCAT('%',:param1,'%') and o.responsible in :param2 order by o.moderationDate desc")
		  List<AbstractOffer> findOfferWithResLib(@Param("param1") String param1, @Param("param2") List<Responsible> param2);
	  
	  
	  @Query(" select o from AbstractOffer o where o.status = 'Validated' and o.id=:param2 and o.responsible in :param3 order by o.moderationDate desc")
		  List<AbstractOffer> findOfferWithResOffer( 
				  @Param("param2") Long  param2,
				  @Param("param3") List<Responsible> param3);
		  
	  AbstractOffer findById(long id);
}
