package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.VehicleType;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {

	@Query("SELECT v FROM VehicleType v"
		+ " WHERE v.description LIKE %?1%")
	public List<VehicleType> search(String keyword);
}
