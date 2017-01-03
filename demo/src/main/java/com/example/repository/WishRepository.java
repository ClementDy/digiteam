package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Wish;


public interface WishRepository extends CrudRepository<Wish, Long>{

}
