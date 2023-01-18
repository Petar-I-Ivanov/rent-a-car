package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.VehicleMake;
import com.rent.car.repositories.VehicleMakeRepository;

@Service
public class VehicleMakeService {

	@Autowired
	private VehicleMakeRepository vehicleMakeRepository;
	
	public List<VehicleMake> getVehicleMake() {
		return vehicleMakeRepository.findAll();
	}
	
	public List<VehicleMake> getVehicleMake(String keyword) {
		
		return (keyword == null)
				? vehicleMakeRepository.findAll()
				: vehicleMakeRepository.search(keyword);
	}
	
	public void save(VehicleMake vehicleMake) {
		vehicleMakeRepository.save(vehicleMake);
	}
	
	public Optional<VehicleMake> findById(int id) {
		return vehicleMakeRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleMakeRepository.deleteById(id);
	}
}
