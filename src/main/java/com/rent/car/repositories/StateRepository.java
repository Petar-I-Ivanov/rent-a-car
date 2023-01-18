package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	@Query("SELECT s FROM State s"
		+ " WHERE s.capital LIKE %?1%"
		+ " OR s.code LIKE %?1%"
		+ " OR s.name LIKE %?1%"
		+ " OR s.details LIKE %?1%")
	public List<State> search(String keyword);
}
