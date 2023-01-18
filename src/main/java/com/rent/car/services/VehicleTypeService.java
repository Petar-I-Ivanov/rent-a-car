package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.VehicleType;
import com.rent.car.repositories.VehicleTypeRepository;

@Service
public class VehicleTypeService {

	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	public List<VehicleType> getVehicleTypes() {
		return vehicleTypeRepository.findAll();
	}
	
	public List<VehicleType> getVehicleTypes(String keyword) {
		
		return (keyword == null)
				? vehicleTypeRepository.findAll()
				: vehicleTypeRepository.search(keyword);
	}
	
	public void save(VehicleType vehicleType) {
		vehicleTypeRepository.save(vehicleType);
	}
	
	public Optional<VehicleType> findById(int id) {
		return vehicleTypeRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleTypeRepository.deleteById(id);
	}
}
