package com.encora.microservice.service;

import com.encora.commons.dto.MessageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;



@Component
public class QueueReceiverService implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(QueueReceiverService.class);
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(MessageList messageList) {
        logger.info("Message received is.. " + messageList);
    }
}
