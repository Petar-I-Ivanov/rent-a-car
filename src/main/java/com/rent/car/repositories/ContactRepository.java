package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	@Query("SELECT c FROM Contact c"
		+ " WHERE c.firstName LIKE %?1%"
		+ " OR c.lastName LIKE %?1%"
		+ " OR c.email LIKE %?1%"
		+ " OR c.phone LIKE %?1%"
		+ " OR c.mobile LIKE %?1%"
		+ " OR c.remarks LIKE %?1%")
	public List<Contact> search(String keyword);
}
