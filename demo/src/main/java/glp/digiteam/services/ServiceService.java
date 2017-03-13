package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.ServiceEntity;
import glp.digiteam.repository.ServiceRepository;

@Service
public class ServiceService {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	
	public Iterable<ServiceEntity> findAll() {
		return serviceRepository.findAll();
	}
	
	public ServiceEntity saveService(ServiceEntity service){
		return serviceRepository.save(service);
	}
}
