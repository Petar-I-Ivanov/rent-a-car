package com.rent.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.car.models.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
