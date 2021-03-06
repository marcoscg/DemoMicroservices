package com.mardeveloper.cadastro.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import com.mardeveloper.cadastro.entity.Client;

@Getter
@Setter
@ToString
public class ClientOrderDto {

	private Long idClient;
	private String name;    

    public static ClientOrderDto create(Client client) {
        return new ModelMapper().map(client, ClientOrderDto.class);
    }

}
