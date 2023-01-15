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

import com.rent.car.models.VehicleMaintenance;
import com.rent.car.services.SupplierService;
import com.rent.car.services.VehicleMaintenanceService;
import com.rent.car.services.VehicleService;

@Controller
public class VehicleMaintenanceController {
	
	@Autowired private VehicleMaintenanceService vehicleMaintenanceService;
	@Autowired private VehicleService vehicleService;
	@Autowired private SupplierService supplierService;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/vehicleMaintenances")
	public String getVehicleMaintenances(VehicleMaintenance vehicleMaintenance, Model model) {
		model = setModel(model);
		return "/vehicles/vehicleMaintenance";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/vehicleMaintenances/addNew")
	public String addNew(@Valid VehicleMaintenance vehicleMaintenance, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/vehicles/vehicleMaintenance";
		}
		
		vehicleMaintenanceService.save(vehicleMaintenance);
		return "redirect:/vehicleMaintenances";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/vehicleMaintenances/findById")
	@ResponseBody
	public Optional<VehicleMaintenance> findById(int id) {
		return vehicleMaintenanceService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleMaintenances/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid VehicleMaintenance vehicleMaintenance, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/vehicles/vehicleMaintenance";
		}
		
		vehicleMaintenanceService.save(vehicleMaintenance);
		return "redirect:/vehicleMaintenances";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleMaintenances/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleMaintenanceService.delete(id);
		return "redirect:/vehicleMaintenances";
	}
	
	private Model setModel (Model model) {
		
		model.addAttribute("vehicleMaintenances", vehicleMaintenanceService.getVehicleMaintenances());
		model.addAttribute("vehicles", vehicleService.getVehicles());
		model.addAttribute("suppliers", supplierService.getSuppliers());
		
		return model;
	}
}
