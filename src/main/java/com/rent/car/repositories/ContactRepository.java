package com.rent.car.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
