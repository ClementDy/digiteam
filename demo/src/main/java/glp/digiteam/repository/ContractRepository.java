package glp.digiteam.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import glp.digiteam.entity.offer.Contract;


public interface ContractRepository extends CrudRepository<Contract, Long>{

	
	@Query(" select count(c) from Contract c")
	int getNbContrats();
}
