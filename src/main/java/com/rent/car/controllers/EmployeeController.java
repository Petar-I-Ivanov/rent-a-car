package com.rent.car.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.Actor;
import com.rent.car.models.Employee;
import com.rent.car.services.ActorService;
import com.rent.car.services.CountryService;
import com.rent.car.services.EmployeeService;
import com.rent.car.services.EmployeeTypeService;
import com.rent.car.services.JobTitleService;
import com.rent.car.services.StateService;

@Controller
public class EmployeeController {

	@Autowired private EmployeeService employeeService;
	@Autowired private ActorService actorService;
	
	@Autowired private EmployeeTypeService employeeTypeService;
	@Autowired private JobTitleService jobTitleService;
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;

	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@GetMapping("/employees")
	public String getEmployees(@Param("keyword") String keyword, Employee employee, Model model) {
		model = setModel(model);
		model.addAttribute("employees", employeeService.getEmployees(keyword));
		return "/people/employee";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@PostMapping("/employees/addNew")
	public String addNew(@Valid Employee employee, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/people/employee";
		}
		
		employee.setActorId(4);
		employeeService.save(employee);
		
		return "redirect:/employees";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/employees/findById")
	@ResponseBody
	public Optional<Employee> findById(int id) {
		return employeeService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/employees/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid Employee employee, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/people/employee";
		}
		
		for (Actor actor : actorService.getActors()) {
			if (actor.getEmployee() != null && actor.getEmployee().getId() == employee.getId()) {
				employee.setActorId(actor.getId());
			}
		}
		
		employeeService.save(employee);
		
		return "redirect:/employees";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/employees/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {		
		employeeService.delete(id);
		return "redirect:/employees";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value = "/employees/assignEmployee")
	public String assignUsername(String username) {
		employeeService.assignUsername(username);
		return "redirect:/employees";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("employees", employeeService.getEmployees());
		model.addAttribute("employeeTypes", employeeTypeService.getEmployeeTypes());
		model.addAttribute("jobTitles", jobTitleService.getJobTitles());
		model.addAttribute("states", stateService.getStates());
		model.addAttribute("countries", countryService.getCountries());
		
		return model;
	}
}
