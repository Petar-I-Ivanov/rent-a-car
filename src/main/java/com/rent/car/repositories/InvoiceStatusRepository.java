package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.InvoiceStatus;

@Repository
public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Integer> {

	@Query("SELECT i FROM InvoiceStatus i"
		+ " WHERE i.description LIKE %?1%")
	public List<InvoiceStatus> search(String keyword);
}
