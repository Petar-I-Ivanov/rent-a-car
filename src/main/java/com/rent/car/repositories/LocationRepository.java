package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

	@Query("SELECT l FROM Location l"
		+ " WHERE l.address LIKE %?1%"
		+ " OR l.city LIKE %?1%"
		+ " OR l.description LIKE %?1%"
		+ " OR l.details LIKE %?1%")
	public List<Location> search(String keyword);
}
