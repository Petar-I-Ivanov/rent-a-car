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

import com.rent.car.models.Employee;
import com.rent.car.models.Location;
import com.rent.car.models.Vehicle;
import com.rent.car.models.VehicleMake;
import com.rent.car.models.VehicleModel;
import com.rent.car.models.VehicleStatus;
import com.rent.car.models.VehicleType;
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
	public String getVehicle(Model model) {
		
		List<Vehicle> vehicleList = vehicleService.getVehicles();
		model.addAttribute("vehicles", vehicleList);
		
		List<VehicleType> vehicleTypeList = vehicleTypeService.getVehicleTypes();
		model.addAttribute("vehicleTypes", vehicleTypeList);
		
		List<VehicleMake> vehicleMakeList = vehicleMakeService.getVehicleMake();
		model.addAttribute("vehicleMakes", vehicleMakeList);
		
		List<VehicleStatus> vehicleStatusList = vehicleStatusService.getVehicleStatuses();
		model.addAttribute("vehicleStatuses", vehicleStatusList);
		
		List<VehicleModel> vehicleModelList = vehicleModelService.getVehicleModels();
		model.addAttribute("vehicleModels", vehicleModelList);
		
		List<Employee> employeeList = employeeService.getEmployees();
		model.addAttribute("employees", employeeList);
		
		List<Location> locationList = locationService.getLocations();
		model.addAttribute("locations", locationList);
		
		return "/vehicles/vehicle";
	}
	
	@PostMapping("/vehicles/addNew")
	public String addNew(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@RequestMapping("/vehicles/findById")
	@ResponseBody
	public Optional<Vehicle> findById(int id) {
		return vehicleService.findById(id);
	}
	
	@RequestMapping(value="/vehicles/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@RequestMapping(value="/vehicles/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleService.delete(id);
		return "redirect:/vehicles";
	}
}
