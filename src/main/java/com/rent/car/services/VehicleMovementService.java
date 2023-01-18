package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.VehicleMovement;
import com.rent.car.repositories.VehicleMovementRepository;

@Service
public class VehicleMovementService {

	@Autowired
	private VehicleMovementRepository vehicleMovementRepository;
	
	public List<VehicleMovement> getVehicleMovements() {
		return vehicleMovementRepository.findAll();
	}
	
	public List<VehicleMovement> getVehicleMovements(String keyword) {
		
		return (keyword == null)
				? vehicleMovementRepository.findAll()
				: vehicleMovementRepository.search(keyword);
	}
	
	public void save(VehicleMovement vehicleMovement) {
		vehicleMovementRepository.save(vehicleMovement);
	}
	
	public Optional<VehicleMovement> findById(int id) {
		return vehicleMovementRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleMovementRepository.deleteById(id);
	}
}
