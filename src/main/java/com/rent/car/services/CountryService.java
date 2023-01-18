package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.Country;
import com.rent.car.repositories.CountryRepository;

@Service
public class CountryService {

//	autowire bean into the service
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> getCountries() {
		return countryRepository.findAll();
	}
	
	public List<Country> getCountries(String keyword) {
		
		return (keyword == null)
				? countryRepository.findAll()
				: countryRepository.search(keyword);
	}
	
	public void save(Country country) {
		countryRepository.save(country);
	}
	
	public Optional<Country> findById(int id) {
		return countryRepository.findById(id);
	}
	
	public void delete(int id) {
		countryRepository.deleteById(id);
	}
}
