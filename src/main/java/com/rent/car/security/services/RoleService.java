package com.rent.car.security.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.Actor;
import com.rent.car.repositories.ActorRepository;
import com.rent.car.security.models.Role;
import com.rent.car.security.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired private RoleRepository roleRepository;
	@Autowired private ActorRepository actorRepository;
	
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}
	
	public Optional<Role> findById(int id) {
		return roleRepository.findById(id);
	}
	
	public void delete (int id) {
		roleRepository.deleteById(id);
	}
	
	public void save (Role role) {
		roleRepository.save(role);
	}
	
	public void assignActorRole (int actorId, int roleId) {
		
		Actor actor = actorRepository.findById(actorId).orElse(null);
		Role role = roleRepository.findById(roleId).orElse(null);
		
		Set<Role> userRoles = actor.getRoles();
		userRoles.add(role);
		actor.setRoles(userRoles);
		
		actorRepository.save(actor);
	}
	
	public void unassigActorRole (int actorId, int roleId) {
		
		Actor actor = actorRepository.findById(actorId).orElse(null);
		Set<Role> userRoles = actor.getRoles();
		userRoles.removeIf(x -> x.getId() == roleId);
		actorRepository.save(actor);
	}
	
	public Set<Role> getActorRoles(Actor actor) {
		return actor.getRoles();
	}
	
	public Set<Role> getActorNotRoles(Actor actor) {
		return roleRepository.getActorNotRoles(actor.getId());
	}
}


















