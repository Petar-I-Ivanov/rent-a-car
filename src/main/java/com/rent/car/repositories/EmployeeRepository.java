package com.rent.car.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee findByUsername(String username);
	public Optional<Employee> findByActorId(int actorId);
	
	@Query("SELECT e FROM Employee e"
		+ " WHERE e.address LIKE %?1%"
		+ " OR e.city LIKE %?1%"
		+ " OR e.email LIKE %?1%"
		+ " OR e.firstName LIKE %?1%"
		+ " OR e.lastName LIKE %?1%"
		+ " OR e.otherName LIKE %?1%"
		+ " OR e.username LIKE %?1%"
		+ " OR e.gender LIKE %?1%"
		+ " OR e.initials LIKE %?1%"
		+ " OR e.maritalStatus LIKE %?1%"
		+ " OR e.mobile LIKE %?1%"
		+ " OR e.phone LIKE %?1%"
		+ " OR e.socialSecurityNumber LIKE %?1%")
	public List<Employee> search(String keyword);
}
