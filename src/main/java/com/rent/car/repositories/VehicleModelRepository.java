package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.VehicleModel;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {

	@Query("SELECT v FROM VehicleModel v"
		+ " WHERE v.description LIKE %?1%")
	public List<VehicleModel> search(String keyword);
}
