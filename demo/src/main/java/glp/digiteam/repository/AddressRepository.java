package glp.digiteam.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import glp.digiteam.entity.student.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findByStreet(String street);

}
