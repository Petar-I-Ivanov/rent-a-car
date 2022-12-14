package com.rent.car.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.ContinentEnum;
import com.rent.car.models.Country;
import com.rent.car.services.CountryService;

@Controller
public class CountryController {
	
//	Autowire the service into the controller
	@Autowired
	private CountryService countryService;

	@GetMapping("/countries")
	public String getCountries(Model model) {
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		model.addAttribute("country", new Country());
		
		return "/globals/country";
	}
	
	@PostMapping("/countries/addNew")
	public String addNew(@Valid Country country, BindingResult bindingResult) {
		
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> processValidationError(MethodArgumentNotValidException ex) {
	    HashMap<String, Object> result = new HashMap<>();
	    
	    BindingResult bindingResult = ex.getBindingResult();
	    FieldError fieldError = bindingResult.getFieldError();
	    
	    result.put("field", fieldError.getField());
	    result.put("message", fieldError.getDefaultMessage());
	    result.put("data", bindingResult.getTarget());

	    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	private String save(Country country, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/globals/country";
		}
		
		countryService.save(country);
		return "redirect:/countries";
	}
}
