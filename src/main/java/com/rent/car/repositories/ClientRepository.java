package com.rent.car.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	public Optional<Client> findByActorId(int actorId);
	
	@Query("SELECT c FROM Client c"
		+ " WHERE c.address LIKE %?1%"
		+ " OR c.city LIKE %?1%"
		+ " OR c.email LIKE %?1%"
		+ " OR c.phone LIKE %?1%"
		+ " OR c.mobile LIKE %?1%"
		+ " OR c.name LIKE %?1%"
		+ " OR c.website LIKE %?1%")
	public List<Client> search(String keyword);
}
