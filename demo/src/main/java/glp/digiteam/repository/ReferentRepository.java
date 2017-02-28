package glp.digiteam.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import glp.digiteam.entity.offer.Referent;


public interface ReferentRepository extends CrudRepository<Referent,Long>{

	Referent findByName(String name);
	
    @Transactional
    Long deleteByName(String name);
}
