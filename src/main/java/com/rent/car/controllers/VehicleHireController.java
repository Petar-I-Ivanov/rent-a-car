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

import com.rent.car.models.Client;
import com.rent.car.models.Location;
import com.rent.car.models.Vehicle;
import com.rent.car.models.VehicleHire;
import com.rent.car.services.ClientService;
import com.rent.car.services.LocationService;
import com.rent.car.services.VehicleHireService;
import com.rent.car.services.VehicleService;

@Controller
public class VehicleHireController {

	@Autowired private VehicleHireService vehicleHireService;
	@Autowired private VehicleService vehicleService;
	@Autowired private ClientService clientService;
	@Autowired private LocationService locationService;

	@GetMapping("/vehicleHires")
	public String getVehicleHires(Model model) {
		
		List<VehicleHire> vehicleHireList = vehicleHireService.getVehicleHires();
		model.addAttribute("vehicleHires", vehicleHireList);
		
		List<Vehicle> vehicleList = vehicleService.getVehicles();
		model.addAttribute("vehicles", vehicleList);
		
		List<Client> clientList = clientService.getClients();
		model.addAttribute("clients", clientList);
		
		List<Location> locationList = locationService.getLocations();
		model.addAttribute("locations", locationList);
		
		return "/vehicles/vehicleHire";
	}
	
	@PostMapping("/vehicleHires/addNew")
	public String addNew(VehicleHire vehicleHire) {
		vehicleHireService.save(vehicleHire);
		return "redirect:/vehicleHires";
	}
	
	@RequestMapping("/vehicleHires/findById")
	@ResponseBody
	public Optional<VehicleHire> findById(int id) {
		return vehicleHireService.findById(id);
	}
	
	@RequestMapping(value="/vehicleHires/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleHire vehicleHire) {
		vehicleHireService.save(vehicleHire);
		return "redirect:/vehicleHires";
	}
	
	@RequestMapping(value="/vehicleHires/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleHireService.delete(id);
		return "redirect:/vehicleHires";
	}
}
