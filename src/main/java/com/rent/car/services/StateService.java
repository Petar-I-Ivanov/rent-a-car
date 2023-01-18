package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.State;
import com.rent.car.repositories.StateRepository;

@Service
public class StateService {

//	autowire bean into the service
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> getStates() {
		return stateRepository.findAll();
	}
	
	public List<State> getStates(String keyword) {
		
		return (keyword == null)
				? stateRepository.findAll()
				: stateRepository.search(keyword);
	}
	
	public void save(State state) {
		stateRepository.save(state);
	}
	
	public Optional<State> findById(int id) {
		return stateRepository.findById(id);
	}
	
	public void delete(int id) {
		stateRepository.deleteById(id);
	}
}
