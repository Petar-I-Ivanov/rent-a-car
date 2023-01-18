package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	@Query("SELECT s FROM Supplier s"
		+ " WHERE s.address LIKE %?1%"
		+ " OR s.city LIKE %?1%"
		+ " OR s.email LIKE %?1%"
		+ " OR s.phone LIKE %?1%"
		+ " OR s.mobile LIKE %?1%"
		+ " OR s.name LIKE %?1%"
		+ " OR s.website LIKE %?1%")
	public List<Supplier> search(String keyword);
}
