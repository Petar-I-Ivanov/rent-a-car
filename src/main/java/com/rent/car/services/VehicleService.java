package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.Vehicle;
import com.rent.car.repositories.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	public List<Vehicle> getVehicles() {
		return vehicleRepository.findAll();
	}
	
	public void save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}
	
	public Optional<Vehicle> findById(int id) {
		return vehicleRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleRepository.deleteById(id);
	}
}
