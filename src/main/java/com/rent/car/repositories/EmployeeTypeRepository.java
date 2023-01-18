package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.EmployeeType;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {

	@Query("SELECT e FROM EmployeeType e"
		+ " WHERE e.description LIKE %?1%")
	public List<EmployeeType> search(String keyword);
}
