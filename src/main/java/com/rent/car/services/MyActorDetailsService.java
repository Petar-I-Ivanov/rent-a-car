package com.rent.car.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rent.car.models.Actor;
import com.rent.car.models.ActorPrincipal;
import com.rent.car.repositories.ActorRepository;

@Service
public class MyActorDetailsService implements UserDetailsService {

	@Autowired
	private ActorRepository actorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Actor actor = actorRepository.findByUsername(username);
		
		if (actor == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		
		return new ActorPrincipal(actor);
	}

}
