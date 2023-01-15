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

import com.rent.car.models.VehicleMovement;
import com.rent.car.services.LocationService;
import com.rent.car.services.VehicleMovementService;
import com.rent.car.services.VehicleService;

@Controller
public class VehicleMovementController {

	@Autowired private VehicleMovementService vehicleMovementService;
	@Autowired private VehicleService vehicleService;
	@Autowired private LocationService locationService;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/vehicleMovements")
	public String getVehicleMovements(VehicleMovement vehicleMovement, Model model) {
		model = setModel(model);
		return "/vehicles/vehicleMovement";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/vehicleMovements/addNew")
	public String addNew(@Valid VehicleMovement vehicleMovement, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/vehicles/vehicleMovement";
		}
		
		vehicleMovementService.save(vehicleMovement);
		return "redirect:/vehicleMovements";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/vehicleMovements/findById")
	@ResponseBody
	public Optional<VehicleMovement> findById(int id) {
		return vehicleMovementService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleMovements/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid VehicleMovement vehicleMovement, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/vehicles/vehicleMovement";
		}
		
		vehicleMovementService.save(vehicleMovement);
		return "redirect:/vehicleMovements";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleMovements/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleMovementService.delete(id);
		return "redirect:/vehicleMovements";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("vehicleMovements", vehicleMovementService.getVehicleMovements());
		model.addAttribute("vehicles", vehicleService.getVehicles());
		model.addAttribute("locations", locationService.getLocations());
		
		return model;
	}
}
