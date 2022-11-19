package com.rent.car.security.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.security.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(
		value  = "SELECT * FROM ROLE "
				+ "WHERE ID NOT IN "
				+ "(SELECT ROLE_ID FROM REGISTERED_USER_ROLE "
				+ "WHERE REGISTERED_USER_ID = ?)",
				nativeQuery = true
	)
	Set<Role> getActorNotRoles(int userId);
}
