package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.student.Mission;
import glp.digiteam.repository.MissionRepository;

@Service
public class MissionService {

	@Autowired
	MissionRepository missionRepository;
	
	public Iterable<Mission> findAll() {
		return missionRepository.findAll();
	}
}
