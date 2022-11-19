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

import com.rent.car.models.Country;
import com.rent.car.models.Location;
import com.rent.car.models.State;
import com.rent.car.services.CountryService;
import com.rent.car.services.LocationService;
import com.rent.car.services.StateService;

@Controller
public class LocationController {
	
	@Autowired private LocationService locationService;
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;

	@GetMapping("/locations")
	public String getLocations(Model model) {
		
		List<Location> locationList = locationService.getLocations();
		model.addAttribute("locations", locationList);
		
		List<State> stateList = stateService.getStates();
		model.addAttribute("states", stateList);
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		return "/globals/location";
	}
	
	@PostMapping("/locations/addNew")
	public String addNew(Location location) {
		locationService.save(location);
		return "redirect:/locations";
	}
	
	@RequestMapping("/locations/findById")
	@ResponseBody
	public Optional<Location> findById(int id) {
		return locationService.findById(id);
	}
	
	@RequestMapping(value="/locations/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Location location) {
		locationService.save(location);
		return "redirect:/locations";
	}
	
	@RequestMapping(value="/locations/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		locationService.delete(id);
		return "redirect:/locations";
	}
}
