package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.business.logic.BudgetAction;
import com.rent.car.models.VehicleHire;
import com.rent.car.repositories.VehicleHireRepository;

@Service
public class VehicleHireService {
	
	@Autowired private VehicleHireRepository vehicleHireRepository;
	@Autowired private BudgetAction budgetAction;
	
	public List<VehicleHire> getVehicleHires() {
		return vehicleHireRepository.findAll();
	}
	
	public List<VehicleHire> getVehicleHires(String keyword) {
		
		return (keyword == null)
				? vehicleHireRepository.findAll()
				: vehicleHireRepository.search(keyword);
	}
	
	// 	first saves vehicleHire
	//	after that it is used as parameter of makeHireAction
	//	(because if its new record the id is 0 and after it is saved it can get the right ID for FK)
	public void save(VehicleHire vehicleHire) {
		budgetAction.makeHireAction(vehicleHireRepository.save(vehicleHire));
	}
	
	public Optional<VehicleHire> findById(int id) {
		return vehicleHireRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleHireRepository.deleteById(id);
	}
}
