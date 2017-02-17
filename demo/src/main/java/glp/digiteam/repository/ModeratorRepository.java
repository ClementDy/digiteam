package glp.digiteam.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import glp.digiteam.entity.offer.Administrator;
import glp.digiteam.entity.offer.Moderator;

public interface ModeratorRepository extends CrudRepository<Moderator, String> {


    Moderator findByName(String name);
    
    /*@Transactional
    Long deleteByFirstName(String name);
*/
}
