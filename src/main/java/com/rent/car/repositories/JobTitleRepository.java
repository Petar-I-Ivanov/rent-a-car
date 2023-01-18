package com.rent.car.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rent.car.models.JobTitle;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {

	@Query("SELECT j FROM JobTitle j"
		+ " WHERE j.description LIKE %?1%")
	public List<JobTitle> search(String keyword);
}
