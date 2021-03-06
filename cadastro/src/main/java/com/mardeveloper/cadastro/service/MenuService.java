package com.mardeveloper.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mardeveloper.cadastro.dto.MenuDto;
import com.mardeveloper.cadastro.dto.MenuOrderDto;
import com.mardeveloper.cadastro.entity.Menu;
import com.mardeveloper.cadastro.entity.Restaurant;
import com.mardeveloper.cadastro.message.MenuSendMessage;
import com.mardeveloper.cadastro.repository.MenuRepository;
import com.mardeveloper.cadastro.repository.RestaurantRepository;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;	
	
	@Autowired
	private MenuSendMessage menuSendMessage;
	
	public Menu insertMenu(MenuDto menuDto) {
		
		Optional<Restaurant> restaurant = restaurantRepository.findById(menuDto.getRestaurant());
		
		if (restaurant.isPresent()) {			
			Menu menu = Menu.create(menuDto);
			menu.setRestaurant(restaurant.get());			
			Menu newMenu = menuRepository.save(menu);			
			menuSendMessage.sendMessage(MenuOrderDto.create(newMenu));			
			return newMenu;
		} else {
			return null;
		}
	}
	
	public Menu updateMenu(Menu menu) {
		
		Optional<Menu> objMenu = menuRepository.findById(menu.getId());
		
		if (objMenu.isPresent()) {
			return menuRepository.save(menu);			
		} else {
			return null; 			
		}
	}
	
	public boolean deleteMenu(Long id) {
		
		Optional<Menu> objMenu = menuRepository.findById(id);
		
		if (objMenu.isPresent()) {
			menuRepository.delete(objMenu.get());
			return true;
		} else {
			return false; 			
		}
	}
	
	public Optional<Menu> findById(Long id) {
		
		Optional<Menu> objMenu = menuRepository.findById(id);
		
		return objMenu;
	}
	
	public List<Menu> findAll() {
		
		List<Menu> list = menuRepository.findAll();
		
		return list;
	}	

}
