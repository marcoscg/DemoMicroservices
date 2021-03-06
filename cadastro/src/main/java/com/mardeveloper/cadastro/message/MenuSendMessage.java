package com.mardeveloper.cadastro.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mardeveloper.cadastro.dto.MenuOrderDto;

@Component
public class MenuSendMessage {

    @Value("${cadastro.rabbitmq.exchange}")
    String exchange;

    @Value("${cadastro.menu.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void sendMessage(MenuOrderDto menuOrderDto) {
        System.out.println(menuOrderDto);
        System.out.println(exchange);
        System.out.println(routingkey);
        rabbitTemplate.convertAndSend(exchange, routingkey, menuOrderDto);
    }

}