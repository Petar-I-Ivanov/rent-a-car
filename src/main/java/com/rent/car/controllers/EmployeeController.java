package com.rent.car.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.Country;
import com.rent.car.models.Employee;
import com.rent.car.models.EmployeeType;
import com.rent.car.models.JobTitle;
import com.rent.car.models.State;
import com.rent.car.services.CountryService;
import com.rent.car.services.EmployeeService;
import com.rent.car.services.EmployeeTypeService;
import com.rent.car.services.JobTitleService;
import com.rent.car.services.StateService;

@Controller
public class EmployeeController {

	@Autowired private EmployeeService employeeService;
	@Autowired private EmployeeTypeService employeeTypeService;
	@Autowired private JobTitleService jobTitleService;
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;

	@GetMapping("/employees")
	public String getEmployees(Employee employee, Model model) {
		
		List<Employee> employeeList = employeeService.getEmployees();
		model.addAttribute("employees", employeeList);
		
		List<EmployeeType> employeeTypeList = employeeTypeService.getEmployeeTypes();
		model.addAttribute("employeeTypes", employeeTypeList);
		
		List<JobTitle> jobTitleList = jobTitleService.getJobTitles();
		model.addAttribute("jobTitles", jobTitleList);
		
		List<State> stateList = stateService.getStates();
		model.addAttribute("states", stateList);
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		return "/people/employee";
	}
	
	@PostMapping("/employees/addNew")
	public String addNew(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}
	
	@RequestMapping("/employees/findById")
	@ResponseBody
	public Optional<Employee> findById(int id) {
		return employeeService.findById(id);
	}
	
	@RequestMapping(value="/employees/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}
	
	@RequestMapping(value="/employees/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		employeeService.delete(id);
		return "redirect:/employees";
	}
	
	@RequestMapping(value = "/employees/assignUsername")
	public String assignUsername(int id) {
		employeeService.assignUsername(id);
		return "redirect:/employees";
	}
}
