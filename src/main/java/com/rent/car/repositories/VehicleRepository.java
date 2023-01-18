package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	@Query("SELECT v FROM Vehicle v"
		+ " WHERE v.name LIKE %?1%"
		+ " OR v.description LIKE %?1%"
		+ " OR v.remarks LIKE %?1%"
		+ " OR v.vehicleNumber LIKE %?1%")
    public List<Vehicle> search(String keyword);
}
