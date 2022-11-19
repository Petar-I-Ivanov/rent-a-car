package com.rent.car.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.models.Client;
import com.rent.car.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public List<Client> getClients() {
		return clientRepository.findAll();
	}
	
	public void save(Client client) {
		clientRepository.save(client);
	}
	
	public Optional<Client> findById(int id) {
		return clientRepository.findById(id);
	}
	
	public void delete(int id) {
		clientRepository.deleteById(id);
	}
}
