package com.mardeveloper.cadastro.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mardeveloper.cadastro.dto.MenuDto;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_menu")
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)	
	private Restaurant restaurant;
	
	public static Menu create(MenuDto menuDto) {
		return new ModelMapper().map(menuDto, Menu.class);		
	}	

}
