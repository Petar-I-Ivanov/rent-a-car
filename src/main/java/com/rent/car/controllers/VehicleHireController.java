package com.rent.car.controllers;

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
	public String getVehicleHires(VehicleHire vehicleHire, Model model) {
		model = setModel(model);
		return "/vehicles/vehicleHire";
	}
	
	@PostMapping("/vehicleHires/addNew")
	public String addNew(@Valid VehicleHire vehicleHire, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/vehicles/vehicleHire";
		}
		
		vehicleHireService.save(vehicleHire);
		return "redirect:/vehicleHires";
	}
	
	@RequestMapping("/vehicleHires/findById")
	@ResponseBody
	public Optional<VehicleHire> findById(int id) {
		return vehicleHireService.findById(id);
	}
	
	@RequestMapping(value="/vehicleHires/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid VehicleHire vehicleHire, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/vehicles/vehicleHire";
		}
		
		vehicleHireService.save(vehicleHire);
		return "redirect:/vehicleHires";
	}
	
	@RequestMapping(value="/vehicleHires/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleHireService.delete(id);
		return "redirect:/vehicleHires";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("vehicleHires", vehicleHireService.getVehicleHires());
		model.addAttribute("vehicles", vehicleService.getVehicles());
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("locations", locationService.getLocations());
		
		return model;
	}
}
