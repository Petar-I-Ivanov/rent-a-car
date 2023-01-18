package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.Employee;
import com.rent.car.models.Actor;
import com.rent.car.repositories.EmployeeRepository;
import com.rent.car.security.services.RoleService;
import com.rent.car.repositories.ActorRepository;

@Service
public class EmployeeService {

	@Autowired private EmployeeRepository employeeRepository;
	@Autowired private ActorRepository actorRepository;
	@Autowired private RoleService roleService;
	
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	public List<Employee> getEmployees(String keyword) {
		
		return (keyword == null)
				? employeeRepository.findAll()
				: employeeRepository.search(keyword);
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
	
	public Optional<Employee> findByActorId(int actorId) {
		return employeeRepository.findByActorId(actorId);
	}
	
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}
	
	public void assignUsername(String username) {
		
		Employee employee = employeeRepository.findByUsername(username);
		
		for (Actor actor : actorRepository.findAll()) {
			if (actor.getUsername().equals(username)) {
				
				roleService.unassigActorRole(actor.getId(), 1);
				roleService.assignActorRole(actor.getId(), 2);
				
				employee.setActorId(actor.getId());
				employeeRepository.save(employee);
			}
		}
	}
}
