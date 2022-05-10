package com.encora.microservice.listener;

import com.encora.commons.serializer.ProductSerializer;
import com.encora.microservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

public class QueueListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(QueueListener.class);
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSerializer productSerializer;

    @RabbitHandler
    @RabbitListener(queues = "${rabbit.product.request.queue}")
    public String listenRequest(String requestMessage, Message message) {
        LOGGER.debug("Request received");
        return productSerializer.serializeList(this.productService.getProducts());
    }

    @RabbitHandler
    @RabbitListener(queues = "${rabbit.product.stored.queue}")
    public String subscribeTostoreQueue(String productMessage, Message message) {

        LOGGER.debug("Product received  {}", productMessage);
        return productSerializer.serializeObject(this.productService.saveProduct(productSerializer.deserialize(productMessage)));
    }
}
