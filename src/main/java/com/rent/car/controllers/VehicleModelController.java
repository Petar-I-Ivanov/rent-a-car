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

import com.rent.car.models.VehicleModel;
import com.rent.car.services.VehicleModelService;

@Controller
public class VehicleModelController {

	@Autowired private VehicleModelService vehicleModelService;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/vehicleModels")
	public String getVehicleModels(VehicleModel vehicleModel, Model model) {
		
		model.addAttribute("vehicleModels", vehicleModelService.getVehicleModels());
		return "/parameters/vehicleModel";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/vehicleModels/addNew")
	public String addNew(@Valid VehicleModel vehicleModel, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("vehicleModels", vehicleModelService.getVehicleModels());
			return "/parameters/vehicleModel";
		}
		
		vehicleModelService.save(vehicleModel);
		return "redirect:/vehicleModels";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/vehicleModels/findById")
	@ResponseBody
	public Optional<VehicleModel> findById(int id) {
		return vehicleModelService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleModels/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid VehicleModel vehicleModel, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("vehicleModels", vehicleModelService.getVehicleModels());
			return "/parameters/vehicleModel";
		}
		
		vehicleModelService.save(vehicleModel);
		return "redirect:/vehicleModels";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleModels/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleModelService.delete(id);
		return "redirect:/vehicleModels";
	}
}
