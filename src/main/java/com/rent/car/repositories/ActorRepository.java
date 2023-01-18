package com.rent.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	Actor findByUsername(String username);
	Actor findByFirstNameAndLastName(String firstName, String lastName);
}
