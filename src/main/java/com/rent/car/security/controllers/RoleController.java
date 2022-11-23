package com.rent.car.security.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.Actor;
import com.rent.car.security.models.Role;
import com.rent.car.security.services.RoleService;
import com.rent.car.services.ActorService;

@Controller
public class RoleController {

	@Autowired private RoleService roleService;
	@Autowired private ActorService actorService;

	@GetMapping("/roles")
	public String getRoles(Model model) {
		
		List<Role> roleList = roleService.getRoles();
		model.addAttribute("roles", roleList);
		
		return "role";
	}
	
	@PostMapping("/roles/addNew")
	public String addNew(Role role) {
		roleService.save(role);
		return "redirect:/roles";
	}
	
	@RequestMapping("/roles/findById")
	@ResponseBody
	public Optional<Role> findById(int id) {
		return roleService.findById(id);
	}
	
	@RequestMapping(value="/roles/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Role role) {
		roleService.save(role);
		return "redirect:/roles";
	}
	
	@RequestMapping(value="/roles/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		roleService.delete(id);
		return "redirect:/roles";
	}
	
	@GetMapping("/security/actor/Edit/{id}")
	public String editUser(@PathVariable int id, Model model) {
		
		Actor actor = actorService.findById(id).orElse(null);
		model.addAttribute("actor", actor);
		model.addAttribute("actorRoles", roleService.getActorRoles(actor));
		model.addAttribute("actorNotRoles", roleService.getActorNotRoles(actor));
		
		return "/people/actorEdit";
	}
	
	@RequestMapping("/security/role/assign/{actorId}/{roleId}")
	public String assignUserRole(@PathVariable int actorId,
							 @PathVariable int roleId) {
		
		roleService.assignActorRole(actorId, roleId);
		return "redirect:/security/actor/Edit/" + actorId;
	}
	
	@RequestMapping("/security/role/unassign/{actorId}/{roleId}")
	public String unassignRole(@PathVariable int actorId,
							 @PathVariable int roleId) {
		
		roleService.unassigActorRole(actorId, roleId);
		return "redirect:/security/actor/Edit/" + actorId;
	}
}
