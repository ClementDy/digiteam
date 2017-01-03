package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.Mission;

public interface MissionRepository extends CrudRepository<Mission, Long>{

}
