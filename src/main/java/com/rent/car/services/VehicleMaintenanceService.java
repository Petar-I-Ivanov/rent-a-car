package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.business.logic.BudgetAction;
import com.rent.car.models.VehicleMaintenance;
import com.rent.car.repositories.VehicleMaintenanceRepository;

@Service
public class VehicleMaintenanceService {

	@Autowired private VehicleMaintenanceRepository vehicleMaintenanceRepository;
	@Autowired private BudgetAction budgetAction;
	
	public List<VehicleMaintenance> getVehicleMaintenances() {
		return vehicleMaintenanceRepository.findAll();
	}
	
	public void save(VehicleMaintenance vehicleMaintenance) {
		budgetAction.makeMaintenanceAction(vehicleMaintenanceRepository.save(vehicleMaintenance));
	}
	
	public Optional<VehicleMaintenance> findById(int id) {
		return vehicleMaintenanceRepository.findById(id);
	}
	
	public void delete(int id) {
		vehicleMaintenanceRepository.deleteById(id);
	}
}
