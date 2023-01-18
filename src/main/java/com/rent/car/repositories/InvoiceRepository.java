package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query("SELECT i FROM Invoice i"
		+ " WHERE CONCAT(i.invoiceDate, '') LIKE %?1%"
		+ " OR i.remarks LIKE %?1%")
	public List<Invoice> search(String keyword);
}
