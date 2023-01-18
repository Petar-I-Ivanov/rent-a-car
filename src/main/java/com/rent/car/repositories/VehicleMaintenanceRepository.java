package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.VehicleMaintenance;

@Repository
public interface VehicleMaintenanceRepository extends JpaRepository<VehicleMaintenance, Integer> {

	@Query("SELECT v FROM VehicleMaintenance v"
		+ " WHERE CONCAT(v.startDate, '') LIKE %?1%"
		+ " OR CONCAT(v.endDate, '') LIKE %?1%"
		+ " OR v.remarks LIKE %?1%")
	public List<VehicleMaintenance> search(String keyword);
}
