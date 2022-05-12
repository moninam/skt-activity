package com.encora.management.service.impl;

import com.encora.commons.constants.ErrorConstants;
import com.encora.commons.dto.Product;
import com.encora.commons.serializer.ProductSerializer;
import com.encora.management.exception.OperationErrorException;
import com.encora.management.service.ProductService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${rabbit.rk.stored}")
    private String storedRK;

    @Value("${rabbit.rk.request}")
    private String requestRK;

    private RabbitTemplate rabbitTemplate;

    private ProductSerializer productSerializer;

    private DirectExchange directExchange;

    public ProductServiceImpl(ProductSerializer productSerializer,RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.productSerializer = productSerializer;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    @Override
    public List<Product> getProducts() throws OperationErrorException {
        Object obj = rabbitTemplate.convertSendAndReceive(directExchange.getName(), requestRK, "request");
        if(obj == null)
        {
            throw new OperationErrorException(ErrorConstants.ERROR_OPERATION);
        }
        return productSerializer.deserializeList(obj.toString());
    }

    @Override
    public Product addProduct(Product product) throws OperationErrorException {
        Object o = rabbitTemplate.convertSendAndReceive(directExchange.getName(), storedRK, productSerializer.serializeObject(product));
        if(o == null){
            throw new OperationErrorException(ErrorConstants.ERROR_ADD);
        }
        Product result = productSerializer.deserializeObject(o.toString());

        if(result.getId() == 0){
            throw new OperationErrorException(ErrorConstants.ERROR_ADD);
        }
        return result;
    }
}
