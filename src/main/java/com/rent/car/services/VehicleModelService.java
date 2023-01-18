package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.VehicleModel;
import com.rent.car.repositories.VehicleModelRepository;

@Service
public class VehicleModelService {
	
	@Autowired
	private VehicleModelRepository vehicleModelRepository;
	
	public List<VehicleModel> getVehicleModels() {
		return vehicleModelRepository.findAll();
	}
	
	public List<VehicleModel> getVehicleModels(String keyword) {
		
		return (keyword == null)
				? vehicleModelRepository.findAll()
				: vehicleModelRepository.search(keyword);
	}
	
	public void save(VehicleModel vehicleModel) {
		vehicleModelRepository.save(vehicleModel);
	}
	
	public Optional<VehicleModel> findById(int id) {
		return vehicleModelRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleModelRepository.deleteById(id);
	}

}
