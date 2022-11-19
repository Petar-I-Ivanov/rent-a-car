package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.JobTitle;
import com.rent.car.repositories.JobTitleRepository;

@Service
public class JobTitleService {

	@Autowired
	private JobTitleRepository jobTitleRepository;
	
	public List<JobTitle> getJobTitles() {
		return jobTitleRepository.findAll();
	}
	
	public void save(JobTitle jobTitle) {
		jobTitleRepository.save(jobTitle);
	}
	
	public Optional<JobTitle> findById(int id) {
		return jobTitleRepository.findById(id);
	}
	
	public void delete(int id) {
		jobTitleRepository.deleteById(id);
	}
}
