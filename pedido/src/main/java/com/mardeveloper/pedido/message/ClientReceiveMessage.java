package com.mardeveloper.pedido.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.mardeveloper.pedido.dto.ClientOrderDto;
import com.mardeveloper.pedido.entity.Client;
import com.mardeveloper.pedido.repository.ClientRepository;

@Component
public class ClientReceiveMessage {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientReceiveMessage(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @RabbitListener(queues = {"${cadastro.client.rabbitmq.queue}"})
    public void receive(@Payload ClientOrderDto clientOrderDto) {
        System.out.println(clientOrderDto);
        clientRepository.save(Client.create(clientOrderDto));
    }

}
