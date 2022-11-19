package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.Employee;
import com.rent.car.models.Actor;
import com.rent.car.repositories.EmployeeRepository;
import com.rent.car.repositories.ActorRepository;

@Service
public class EmployeeService {

	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private ActorRepository actorRepository;
	
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public Optional<Employee> findById(int id) {
		return employeeRepository.findById(id);
	}
	
	public Employee findByUsername(String username) {
		return employeeRepository.findByUsername(username);
	}
	
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}
	
//	Set the Username of the employee where firstName and lastName match with RegisteredActor
	public void assignUsername(int id) {
		
		Employee employee = employeeRepository.findById(id).orElse(null);
		
		Actor actor = actorRepository.findByFirstNameAndLastName(
				employee.getFirstName(),
				employee.getLastName()
				);
		
		employee.setUsername(actor.getUsername());
		employeeRepository.save(employee);
	}
}
