package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.VehicleHire;

@Repository
public interface VehicleHireRepository extends JpaRepository<VehicleHire, Integer> {

	@Query("SELECT v FROM VehicleHire v"
		+ " WHERE CONCAT(v.dateIn, '') LIKE %?1%"
		+ " OR CONCAT(v.dateOut, '') LIKE %?1%"
		+ " OR v.remarks LIKE %?1%")
	public List<VehicleHire> search(String keyword);
}
