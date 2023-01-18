package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.VehicleMovement;

@Repository
public interface VehicleMovementRepository extends JpaRepository<VehicleMovement, Integer> {

	@Query("SELECT v FROM VehicleMovement v"
		+ " WHERE CONCAT(v.fromDate, '') LIKE %?1%"
		+ " OR CONCAT(v.toDate, '') LIKE %?1%"
		+ " OR v.remarks LIKE %?1%")
	public List<VehicleMovement> search(String keyword);
}
