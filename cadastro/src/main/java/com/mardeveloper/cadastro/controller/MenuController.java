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

import com.mardeveloper.cadastro.dto.MenuDto;
import com.mardeveloper.cadastro.entity.Menu;
import com.mardeveloper.cadastro.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("")
	public ResponseEntity<Object> findAll() {
		
		List<Menu> list = menuService.findAll();
		
		return  ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
		
		Optional<Menu> objMenu = menuService.findById(id);
		
		return  objMenu.isPresent() ? ResponseEntity.ok(objMenu.get()) : ResponseEntity.notFound().build();
		
	}	

	@PostMapping("")
	public ResponseEntity<Object> insertClient(@RequestBody MenuDto menuDto) {

		try {
			Menu menu = menuService.insertMenu(menuDto);
			return ResponseEntity.ok().body(menu);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateClient(@PathVariable("id") Long id, @RequestBody MenuDto menuDto) {

		Menu menu = Menu.create(menuDto);

		menu.setId(id);

		Menu updateMenu = menuService.updateMenu(menu);

		return Objects.nonNull(updateMenu) ? ResponseEntity.ok().body(updateMenu)
				: ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable("id") Long id) {
		
		return  menuService.deleteMenu(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
		
	}	


}
