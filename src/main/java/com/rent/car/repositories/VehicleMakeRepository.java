package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.VehicleMake;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Integer> {

	@Query("SELECT v FROM VehicleMake v"
		+ " WHERE v.description LIKE %?1%")
	public List<VehicleMake> search(String keyword);
}
