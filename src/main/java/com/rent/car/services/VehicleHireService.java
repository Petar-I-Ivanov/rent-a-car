package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.VehicleHire;
import com.rent.car.repositories.VehicleHireRepository;

@Service
public class VehicleHireService {

	@Autowired
	private VehicleHireRepository vehicleHireRepository;
	
	public List<VehicleHire> getVehicleHires() {
		return vehicleHireRepository.findAll();
	}
	
	public void save(VehicleHire vehicleHire) {
		vehicleHireRepository.save(vehicleHire);
	}
	
	public Optional<VehicleHire> findById(int id) {
		return vehicleHireRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleHireRepository.deleteById(id);
	}
}
