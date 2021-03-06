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

import com.mardeveloper.cadastro.dto.RestaurantDto;
import com.mardeveloper.cadastro.entity.Restaurant;
import com.mardeveloper.cadastro.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("")
	public ResponseEntity<Object> findAll() {
		
		List<Restaurant> list = restaurantService.findAll();
		
		return  ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
		
		Optional<Restaurant> objRestaurant = restaurantService.findById(id);
		
		return  objRestaurant.isPresent() ? ResponseEntity.ok(objRestaurant.get()) : ResponseEntity.notFound().build();
		
	}	

	@PostMapping("")
	public ResponseEntity<Object> insertClient(@RequestBody RestaurantDto restaurantDto) {

		try {
			Restaurant restaurant = restaurantService.insertRepository(Restaurant.create(restaurantDto));
			return ResponseEntity.ok().body(restaurant);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateClient(@PathVariable("id") Long id, @RequestBody RestaurantDto restaurantDto) {

		Restaurant restaurant = Restaurant.create(restaurantDto);

		restaurant.setId(id);

		Restaurant updateRestaurant = restaurantService.updateRepository(restaurant);

		return Objects.nonNull(updateRestaurant) ? ResponseEntity.ok().body(updateRestaurant)
				: ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable("id") Long id) {
		
		return  restaurantService.deleteRepository(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
		
	}	


}
