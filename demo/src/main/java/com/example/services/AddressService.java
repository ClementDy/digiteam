package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.example.entity.Address;
import com.example.repository.AddressRepository;


public class AddressService {

	@Autowired
	AddressRepository repository;
	
	@Bean
	public void addAddress(Address address){
		System.out.println("ADD");
		System.out.println(address.toString());
		//repository.save(new Address(address.getStreet(), address.getComplement(), address.getPostalCode(), address.getCity()));
		//repository.save(new Address(student.getStreet(),student.getComplement(),student.getPostalCode(),student.getCity(),studentT));
	}
}
