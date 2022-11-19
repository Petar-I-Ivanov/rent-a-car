package com.rent.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
