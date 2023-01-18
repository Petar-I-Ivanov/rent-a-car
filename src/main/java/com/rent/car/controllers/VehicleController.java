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

import com.rent.car.models.Vehicle;
import com.rent.car.services.EmployeeService;
import com.rent.car.services.LocationService;
import com.rent.car.services.VehicleMakeService;
import com.rent.car.services.VehicleModelService;
import com.rent.car.services.VehicleService;
import com.rent.car.services.VehicleStatusService;
import com.rent.car.services.VehicleTypeService;

@Controller
public class VehicleController {

	@Autowired private VehicleService vehicleService;
	@Autowired private VehicleTypeService vehicleTypeService;
	@Autowired private VehicleMakeService vehicleMakeService;
	@Autowired private VehicleStatusService vehicleStatusService;
	@Autowired private VehicleModelService vehicleModelService;
	@Autowired private EmployeeService employeeService;
	@Autowired private LocationService locationService;

	@GetMapping("/vehicles")
	public String getVehicle(@Param("keyword") String keyword, Vehicle vehicle, Model model) {
		
		model = setModel(model);
		model.addAttribute("vehicles", vehicleService.getVehicles(keyword));
		return "/vehicles/vehicle";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/vehicles/addNew")
	public String addNew(@Valid Vehicle vehicle, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/vehicles/vehicle";
		}
		
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@RequestMapping("/vehicles/findById")
	@ResponseBody
	public Optional<Vehicle> findById(int id) {
		return vehicleService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/vehicles/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/vehicles/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleService.delete(id);
		return "redirect:/vehicles";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("vehicles", vehicleService.getVehicles());
		model.addAttribute("vehicleTypes", vehicleTypeService.getVehicleTypes());
		model.addAttribute("vehicleMakes", vehicleMakeService.getVehicleMake());
		model.addAttribute("vehicleStatuses", vehicleStatusService.getVehicleStatuses());
		model.addAttribute("vehicleModels", vehicleModelService.getVehicleModels());
		model.addAttribute("employees", employeeService.getEmployees());
		model.addAttribute("locations", locationService.getLocations());
		
		return model;
	}
}
