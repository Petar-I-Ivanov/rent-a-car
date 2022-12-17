package com.rent.car.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.ContinentEnum;
import com.rent.car.models.Country;
import com.rent.car.services.CountryService;
import com.rent.car.test.UniqueCheck;

@Controller
public class CountryController {
	
//	Autowire the service into the controller
	@Autowired private CountryService countryService;

	@GetMapping("/countries")
	public String getCountries(Model model) {
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		model.addAttribute("country", new Country());
		
		model.addAttribute("continents", getContinentList());
		
		return "/globals/country";
	}
	
	@PostMapping("/countries/addNew")
	public String addNew(@Valid Country country, BindingResult bindingResult) {
		
		country.setId(0);
		checks(countryService.getCountries(), country, bindingResult);
		
		return save(country, bindingResult);
	}
	
	@RequestMapping("countries/findById")
	@ResponseBody
	public Optional<Country> findById(int id) {
		return countryService.findById(id);
	}
	
	@RequestMapping(value="/countries/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid Country country, BindingResult bindingResult) {
		return save(country, bindingResult);
	}
	
	@RequestMapping(value="/countries/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		countryService.delete(id);
		return "redirect:/countries";
	}
	
	private String save(Country country, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/globals/country";
		}
		
		countryService.save(country);
		return "redirect:/countries";
	}
	
	private List<String> getContinentList() {
		
		return Stream.of(ContinentEnum.values())
                .map(ContinentEnum::name)
                .collect(Collectors.toList());
	}
	
	private void checks(List<Country> countries, Country country, BindingResult bindingResult) {
		
		UniqueCheck<Country> check = new UniqueCheck<Country>();
		
		if (!check.isValueUniqueForItsField(countries, country.getCode(), "Code")) {
			bindingResult.addError(new FieldError("country", "code", "Code should be unique."));
		}
		
		if (!check.isValueUniqueForItsField(countries, country.getDescription(), "Description")) {
			bindingResult.addError(new FieldError("country", "description", "Description should be unique."));
		}
		
		if (!check.isValueUniqueForItsField(countries, country.getCapital(), "Capital")) {
			bindingResult.addError(new FieldError("country", "capital", "Capital should be unique."));
		}
		
		if (!check.isValueUniqueForItsField(countries, country.getNationality(), "Nationality")) {
			bindingResult.addError(new FieldError("country", "nationality", "Nationality should be unique."));
		}
	}	
}
