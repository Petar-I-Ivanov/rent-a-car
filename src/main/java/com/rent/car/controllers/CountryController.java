package com.rent.car.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.rent.car.custom.IdChangeCheck;
import com.rent.car.models.ContinentEnum;
import com.rent.car.models.Country;
import com.rent.car.services.CountryService;

@Controller
public class CountryController {
	
//	Autowire the service into the controller
	@Autowired private CountryService countryService;
	
	private Country lastGivenCountry;
	
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@GetMapping("/countries")
	public String getCountries(@Param("keyword") String keyword, Country country, Model model) {
		
		model = setModel(model);
		model.addAttribute("countries", countryService.getCountries(keyword));
		return "/globals/country";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/countries/addNew")
	public String addNew(@Valid Country country, BindingResult bindingResult, Model model) {
		
		bindingResult = IdChangeCheck.isChanged(country.getId(), 0, bindingResult);
		
//		if (country.getId() != 0) {
//			bindingResult.addError(new FieldError("country", "id", "Don't change id's value."));
//		}
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/globals/country";
		}
		
		countryService.save(country);
		return "redirect:/countries";
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping("/countries/findById")
	@ResponseBody
	public Optional<Country> findById(int id) {
		
		lastGivenCountry = countryService.findById(id).orElse(null);
		return countryService.findById(id);
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@RequestMapping(value="/countries/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid Country country, BindingResult bindingResult, Model model) {
		
		bindingResult = IdChangeCheck.isChanged(country.getId(), lastGivenCountry.getId(), bindingResult);
		
		if (bindingResult.hasErrors()) {
			model = setModel(model);
			return "/globals/country";
		}
		
		countryService.save(country);
		return "redirect:/countries";
	}
	
	@PreAuthorize("hasAnyAuthority('Admin', 'Super Admin')")
	@RequestMapping(value="/countries/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		
		countryService.delete(id);
		return "redirect:/countries";
	}
	
	private Model setModel(Model model) {
		
		model.addAttribute("countries", countryService.getCountries());
		model.addAttribute("continents", getContinentList());
		
		return model;
	}
	
	private List<String> getContinentList() {
		
		return Stream.of(ContinentEnum.values())
                .map(ContinentEnum::name)
                .collect(Collectors.toList());
	}
}
