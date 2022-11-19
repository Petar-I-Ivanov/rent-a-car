package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rent.car.models.Actor;
import com.rent.car.repositories.ActorRepository;

@Service
public class ActorService {
	
	@Autowired private BCryptPasswordEncoder encoder;
	@Autowired private ActorRepository actorRepository;
	
	public List<Actor> getActors() {
		return actorRepository.findAll();
	}
	
	public void save(Actor actor) {
		actor.setPassword(encoder.encode(actor.getPassword()));
		actorRepository.save(actor);
	}
	
	public Optional<Actor> findById(int id) {
		return actorRepository.findById(id);
	}
	
	public void delete(int id) {
		actorRepository.deleteById(id);
	}
}
