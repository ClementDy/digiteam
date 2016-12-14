package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findByStreet(String street);

}
