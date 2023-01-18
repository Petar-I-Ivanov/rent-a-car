package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Country;

// specify the name of the table that we want
// or the name of the model
// we also specify the primary key type (primary key type in our model CLient)
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	@Query("SELECT c FROM Country c"
		+ " WHERE c.capital LIKE %?1%"
		+ " OR c.code LIKE %?1%"
		+ " OR c.continent LIKE %?1%"
		+ " OR c.description LIKE %?1%"
		+ " OR c.nationality LIKE %?1%")
	public List<Country> search(String keyword);
}
