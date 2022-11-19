package com.rent.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
