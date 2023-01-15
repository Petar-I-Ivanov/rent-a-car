package com.rent.car.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.Location;
import com.rent.car.models.State;
import com.rent.car.services.CountryService;
import com.rent.car.services.LocationService;
import com.rent.car.services.StateService;

@Controller
public class LocationController {
	
	private Location lastGivenLocation;
	
	@Autowired private LocationService locationService;
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;

	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/locations")
	public String getLocations(Location location, Model model) {
		
		model = setModel(model);
		return "/globals/location";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/locations/addNew")
	public String addNew(@Valid Location location, BindingResult bindingResult, Model model) {
		
		if (location.getId() != 0) {
			bindingResult.addError(new FieldError("location", "id", "Don't change id's value."));
		}
		
		if (!isStateAndCountryMatch(location)) {
			bindingResult.addError(new FieldError("location", "state", "State doesn't match with country."));
		}
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/globals/location";
		}
		
		locationService.save(location);
		return "redirect:/locations";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/locations/findById")
	@ResponseBody
	public Optional<Location> findById(int id) {
		
		lastGivenLocation = locationService.findById(id).orElse(null);
		return locationService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/locations/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid Location location, BindingResult bindingResult, Model model) {
		
		if (location.getId() != lastGivenLocation.getId()) {
			bindingResult.addError(new FieldError("location", "id", "Don't change id's value."));
		}
		
		if (!isStateAndCountryMatch(location)) {
			bindingResult.addError(new FieldError("location", "state", "State doesn't match with country."));
		}
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/globals/location";
		}
		
		locationService.save(location);
		return "redirect:/locations";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/locations/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		locationService.delete(id);
		return "redirect:/locations";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("locations", locationService.getLocations());
		model.addAttribute("states", stateService.getStates());
		model.addAttribute("countries", countryService.getCountries());
		
		return model;
	}
	
	private boolean isStateAndCountryMatch(Location location) {
		
		for (State s : stateService.getStates()) {
			if (s.getId() == location.getStateId()) {
				return s.getCountryId() == location.getCountryId();
			}
		}
		
		return false;
	}
}
