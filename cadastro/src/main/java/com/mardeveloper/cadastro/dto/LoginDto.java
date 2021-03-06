package com.mardeveloper.cadastro.dto;

import org.modelmapper.ModelMapper;

import com.mardeveloper.cadastro.entity.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

	private String email;
	private String password;
	
    public static LoginDto create(Client client) {
        return new ModelMapper().map(client, LoginDto.class);
    }	
	
}
