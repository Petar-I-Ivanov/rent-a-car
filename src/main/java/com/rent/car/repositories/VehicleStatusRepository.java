package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.VehicleStatus;

@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, Integer> {

	@Query("SELECT v FROM VehicleStatus v"
		+ " WHERE v.description LIKE %?1%")
	public List<VehicleStatus> search(String keyword);
}
