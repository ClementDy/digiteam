package glp.digiteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glp.digiteam.entity.offer.Contract;
import glp.digiteam.repository.ContractRepository;


@Service
public class ContractService {
	
	@Autowired
	ContractRepository contractRepository;
	
	
	public Contract saveContract(Contract contract){
		return contractRepository.save( contract);
	}
	
	public Contract getContractByID(Long id){
		return contractRepository.findOne(id);
	}
	
	public void validateContract(Contract contract){
		contract.setStatus(true);
		contractRepository.save( contract);
	}
	
}
