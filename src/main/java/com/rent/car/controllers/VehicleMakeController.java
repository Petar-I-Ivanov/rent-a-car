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

import com.rent.car.models.VehicleMake;
import com.rent.car.services.VehicleMakeService;

@Controller
public class VehicleMakeController {

	@Autowired private VehicleMakeService vehicleMakeService;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/vehicleMakes")
	public String getVehicleMakes(@Param("keyword") String keyword, VehicleMake vehicleMake, Model model) {
		
		model.addAttribute("vehicleMakes", vehicleMakeService.getVehicleMake(keyword));
		return "/parameters/vehicleMake";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/vehicleMakes/addNew")
	public String addNew(@Valid VehicleMake vehicleMake, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("vehicleMakes", vehicleMakeService.getVehicleMake());
			return "/parameters/vehicleMake";
		}
		
		vehicleMakeService.save(vehicleMake);
		return "redirect:/vehicleMakes";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/vehicleMakes/findById")
	@ResponseBody
	public Optional<VehicleMake> findById(int id) {
		return vehicleMakeService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleMakes/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid VehicleMake vehicleMake, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("vehicleMakes", vehicleMakeService.getVehicleMake());
			return "/parameters/vehicleMake";
		}
		
		vehicleMakeService.save(vehicleMake);
		return "redirect:/vehicleMakes";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleMakes/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleMakeService.delete(id);
		return "redirect:/vehicleMakes";
	}
}
