package com.rent.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Country;

// specify the name of the table that we want
// or the name of the model
// we also specify the primary key type (primary key type in our model CLient)
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
