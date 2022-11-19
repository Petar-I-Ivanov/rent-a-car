package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.EmployeeType;
import com.rent.car.repositories.EmployeeTypeRepository;

@Service
public class EmployeeTypeService {

	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	public List<EmployeeType> getEmployeeTypes() {
		return employeeTypeRepository.findAll();
	}
	
	public void save(EmployeeType employeeType) {
		employeeTypeRepository.save(employeeType);
	}
	
	public Optional<EmployeeType> findById(int id) {
		return employeeTypeRepository.findById(id);
	}
	
	public void delete(int id) {
		employeeTypeRepository.deleteById(id);
	}
}
