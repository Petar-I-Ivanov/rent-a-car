package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rent.car.models.Actor;
import com.rent.car.models.Client;
import com.rent.car.repositories.ActorRepository;
import com.rent.car.security.services.RoleService;

@Service
public class ActorService {
	
	@Autowired private BCryptPasswordEncoder encoder;
	@Autowired private ActorRepository actorRepository;
	@Autowired private RoleService roleService;
	@Autowired private ClientService clientService;
	
	public List<Actor> getActors() {
		return actorRepository.findAll();
	}
	
	public void save(Actor actor) {
		
		actor.setPassword(encoder.encode(actor.getPassword()));
		actor = actorRepository.save(actor);
		
		roleService.assignActorRole(actor.getId(), 1);
		Client client = new Client(0,
				actor.getFirstName() + " " + actor.getLastName(),
				null, null, null, null, null, null, null, 1,
				null, 1, null, null, actor.getId(), null, null);
		clientService.save(client);
	}
	
	public Optional<Actor> findById(int id) {
		return actorRepository.findById(id);
	}
	
	public Actor findByUsername(String username) {
		return actorRepository.findByUsername(username);
	}
	
	public void delete(int id) {
		actorRepository.deleteById(id);
	}
}
