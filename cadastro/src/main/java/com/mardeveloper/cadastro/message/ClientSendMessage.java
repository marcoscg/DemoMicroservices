package com.mardeveloper.cadastro.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mardeveloper.cadastro.dto.ClientOrderDto;

@Component
public class ClientSendMessage {

    @Value("${cadastro.rabbitmq.exchange}")
    String exchange;

    @Value("${cadastro.client.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void sendMessage(ClientOrderDto client) {
        System.out.println(client);
        System.out.println(exchange);
        System.out.println(routingkey);
        rabbitTemplate.convertAndSend(exchange, routingkey, client);
    }

}