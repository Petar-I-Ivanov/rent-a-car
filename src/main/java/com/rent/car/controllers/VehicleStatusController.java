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

import com.rent.car.models.VehicleStatus;
import com.rent.car.services.VehicleStatusService;

@Controller
public class VehicleStatusController {

	@Autowired private VehicleStatusService vehicleStatusService;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/vehicleStatuses")
	public String getVehicleStatuses(VehicleStatus vehicleStatus, Model model) {
		
		model.addAttribute("vehicleStatuses", vehicleStatusService.getVehicleStatuses());
		return "/parameters/vehicleStatus";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/vehicleStatuses/addNew")
	public String addNew(@Valid VehicleStatus vehicleStatus, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("vehicleStatuses", vehicleStatusService.getVehicleStatuses());
			return "/parameters/vehicleStatus";
		}
		
		vehicleStatusService.save(vehicleStatus);
		return "redirect:/vehicleStatuses";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/vehicleStatuses/findById")
	@ResponseBody
	public Optional<VehicleStatus> findById(int id) {
		return vehicleStatusService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleStatuses/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid VehicleStatus vehicleStatus, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("vehicleStatuses", vehicleStatusService.getVehicleStatuses());
			return "/parameters/vehicleStatus";
		}
		
		vehicleStatusService.save(vehicleStatus);
		return "redirect:/vehicleStatuses";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleStatuses/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleStatusService.delete(id);
		return "redirect:/vehicleStatuses";
	}
}
