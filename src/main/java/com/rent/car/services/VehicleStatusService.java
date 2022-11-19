package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.VehicleStatus;
import com.rent.car.repositories.VehicleStatusRepository;

@Service
public class VehicleStatusService {

	@Autowired
	private VehicleStatusRepository vehicleStatusRepository;
	
	public List<VehicleStatus> getVehicleStatuses() {
		return vehicleStatusRepository.findAll();
	}
	
	public void save(VehicleStatus vehicleStatus) {
		vehicleStatusRepository.save(vehicleStatus);
	}
	
	public Optional<VehicleStatus> findById(int id) {
		return vehicleStatusRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleStatusRepository.deleteById(id);
	}
}
