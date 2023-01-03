package com.rent.car.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.EmployeeType;
import com.rent.car.services.EmployeeTypeService;

@Controller
public class EmployeeTypeController {

	@Autowired private EmployeeTypeService employeeTypeService;

	@GetMapping("/employeeTypes")
	public String getEmployeeTypes(EmployeeType employeeType, Model model) {
		
		List<EmployeeType> employeeTypeList = employeeTypeService.getEmployeeTypes();
		model.addAttribute("employeeTypes", employeeTypeList);
		
		return "/types/employeeType";
	}
	
	@PostMapping("/employeeTypes/addNew")
	public String addNew(@Valid EmployeeType employeeType, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("employeeTypes", employeeTypeService.getEmployeeTypes());
		}
		
		employeeTypeService.save(employeeType);
		return "redirect:/employeeTypes";
	}
	
	@RequestMapping("/employeeTypes/findById")
	@ResponseBody
	public Optional<EmployeeType> findById(int id) {
		return employeeTypeService.findById(id);
	}
	
	@RequestMapping(value="/employeeTypes/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid EmployeeType employeeType, BindingResult bindingResult, Model model) {
		employeeTypeService.save(employeeType);
		return "redirect:/employeeTypes";
	}
	
	@RequestMapping(value="/employeeTypes/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		employeeTypeService.delete(id);
		return "redirect:/employeeTypes";
	}
}
