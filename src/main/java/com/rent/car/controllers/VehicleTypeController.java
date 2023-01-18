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

import com.rent.car.models.VehicleType;
import com.rent.car.services.VehicleTypeService;

@Controller
public class VehicleTypeController {

	@Autowired private VehicleTypeService vehicleTypeService;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/vehicleTypes")
	public String getVehicleTypes(@Param("keyword") String keyword, VehicleType vehicleType, Model model) {
		
		model.addAttribute("vehicleTypes", vehicleTypeService.getVehicleTypes(keyword));
		return "/parameters/vehicleType";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/vehicleTypes/addNew")
	public String addNew(@Valid VehicleType vehicleType, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("vehicleTypes", vehicleTypeService.getVehicleTypes());
			return "/parameters/vehicleType";
		}
		
		vehicleTypeService.save(vehicleType);
		return "redirect:/vehicleTypes";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/vehicleTypes/findById")
	@ResponseBody
	public Optional<VehicleType> findById(int id) {
		return vehicleTypeService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleTypes/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid VehicleType vehicleType, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("vehicleTypes", vehicleTypeService.getVehicleTypes());
			return "/parameters/vehicleType";
		}
		
		vehicleTypeService.save(vehicleType);
		return "redirect:/vehicleTypes";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/vehicleTypes/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		vehicleTypeService.delete(id);
		return "redirect:/vehicleTypes";
	}
}
