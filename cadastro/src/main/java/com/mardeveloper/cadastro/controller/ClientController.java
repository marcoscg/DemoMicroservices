package com.mardeveloper.cadastro.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mardeveloper.cadastro.dto.ClientDto;
import com.mardeveloper.cadastro.entity.Client;
import com.mardeveloper.cadastro.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("")
	public ResponseEntity<Object> findAll() {
		
		List<Client> list = clientService.findAll();
		
		return  ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
		
		Optional<Client> objClient = clientService.findById(id);
		
		return  objClient.isPresent() ? ResponseEntity.ok(objClient.get()) : ResponseEntity.notFound().build();
		
	}	

	@PostMapping("")
	public ResponseEntity<Object> insertClient(@RequestBody ClientDto clientDto) {

		try {
			Client client = clientService.insertClient(Client.create(clientDto));
			return ResponseEntity.ok().body(client);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateClient(@PathVariable("id") Long id, @RequestBody ClientDto clientDto) {

		Client client = Client.create(clientDto);

		client.setId(id);

		Client updateClient = clientService.updateClient(client);

		return Objects.nonNull(updateClient) ? ResponseEntity.ok().body(updateClient)
				: ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable("id") Long id) {
		
		return  clientService.deleteCliente(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
		
	}	


}
