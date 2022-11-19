package com.rent.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
