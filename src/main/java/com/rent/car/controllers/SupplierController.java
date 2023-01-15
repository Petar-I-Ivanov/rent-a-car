package com.rent.car.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.Supplier;
import com.rent.car.services.CountryService;
import com.rent.car.services.SupplierService;
import com.rent.car.services.StateService;

@Controller
public class SupplierController {

	@Autowired private SupplierService supplierService;
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;

	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@GetMapping("/suppliers")
	public String getSuppliers(Supplier supplier, Model model) {
		
		model = setModel(model);
		return "/people/supplier";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@PostMapping("/suppliers/addNew")
	public String addNew(@Valid Supplier supplier, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/people/supplier";
		}
		
		supplierService.save(supplier);
		return "redirect:/suppliers";
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/suppliers/findById")
	@ResponseBody
	public Optional<Supplier> findById(int id) {
		return supplierService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Human Resource', 'Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/suppliers/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid Supplier supplier, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/people/supplier";
		}
		
		supplierService.save(supplier);
		return "redirect:/suppliers";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/suppliers/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		supplierService.delete(id);
		return "redirect:/suppliers";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("suppliers", supplierService.getSuppliers());
		model.addAttribute("states", stateService.getStates());
		model.addAttribute("countries", countryService.getCountries());
		
		return model;
	}
}
