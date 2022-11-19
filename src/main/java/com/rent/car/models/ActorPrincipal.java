package com.rent.car.models;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rent.car.security.models.Role;

public class ActorPrincipal implements UserDetails {

	/**
	 * 1L
	 */
	private static final long serialVersionUID = 1L;
	private Actor actor;
	
	public ActorPrincipal(Actor actor) {
		this.actor = actor;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
//		return Collections.singleton(new SimpleGrantedAuthority("USER"));
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role : actor.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getDescription()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return actor.getPassword();
	}

	@Override
	public String getUsername() {
		return actor.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
