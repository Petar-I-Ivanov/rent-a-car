package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.Location;
import com.rent.car.repositories.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	public List<Location> getLocations() {
		return locationRepository.findAll();
	}
	
	public List<Location> getLocations(String keyword) {
		
		return (keyword == null)
				? locationRepository.findAll()
				: locationRepository.search(keyword);
	}
	
	public void save(Location location) {
		locationRepository.save(location);
	}
	
	public Optional<Location> findById(int id) {
		return locationRepository.findById(id);
	}
	
	public void delete(int id) {
		locationRepository.deleteById(id);
	}
}
