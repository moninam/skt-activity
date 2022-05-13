package com.encora.management.service;

import com.encora.commons.constants.ErrorConstants;
import com.encora.commons.constants.QueueConstants;
import com.encora.commons.dto.Product;
import com.encora.commons.enums.ProductType;
import com.encora.commons.serializer.ProductSerializer;
import com.encora.management.exception.OperationErrorException;
import com.encora.management.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {


    private RabbitTemplate rabbitTemplate;

    private RabbitTemplate rabbitTemplateError;
    private ProductSerializer productSerializer;

    private ProductSerializer productSerializerError;

    private DirectExchange directExchange;

    private ProductServiceImpl productService;

    private ProductServiceImpl productServiceError;

    private String listJson;

    private String jsonProduct;

    private String jsonProductError;
    private List<Product> productList = new ArrayList<>();

    private Product productTest;

    private Product productTestError;

    @Before
    public void setUp(){
        initComponents();
        initMocks();
        productService = new ProductServiceImpl(productSerializer,rabbitTemplate,directExchange);
        productServiceError = new ProductServiceImpl(productSerializerError,rabbitTemplateError,directExchange);
    }
    private void initComponents(){
        listJson = "[" +
                "{" +
                "\"id\":1," +
                "\"name\":\"Samsung\"," +
                "\"type\":\"CELL_PHONE\"," +
                "\"description\":\"Samsung\"" +
                "}," +
                "{" +
                "\"id\":2," +
                "\"name\":\"Chips\"," +
                "\"type\":\"FOOD\"," +
                "\"description\":\"Chips\"" +
                "}]";
        jsonProduct = "{\"id\":6,\"name\":\"Coca Cola\",\"type\":\"FOOD\",\"description\":\"Coca Cola\"}";
        jsonProduct = "{\"id\":0,\"name\":\"Coca Cola\",\"type\":\"FOOD\",\"description\":\"Coca Cola\"}";
        productTest = new Product(6,"Coca Cola",ProductType.FOOD,"Coca Cola");
        productTestError = new Product(0,"Coca Cola",ProductType.FOOD,"Coca Cola");
        productList.add(new Product(1,"Samsung", ProductType.CELL_PHONE,"Samsung"));
        productList.add( new Product(2,"Chips",ProductType.FOOD,"Chips"));

    }
    private void initMocks(){
        Object obj = listJson;
        rabbitTemplate = mock(RabbitTemplate.class);
        rabbitTemplateError = mock(RabbitTemplate.class);
        productSerializer = mock(ProductSerializer.class);
        productSerializerError = mock(ProductSerializer.class);
        directExchange = mock(DirectExchange.class);
        when(
                rabbitTemplate.convertSendAndReceive(
                        QueueConstants.exchangeName,
                        QueueConstants.requestRK,QueueConstants.messageQueue)
        ).thenReturn(obj);
        when(
                rabbitTemplate.convertSendAndReceive(
                        QueueConstants.exchangeName,
                        QueueConstants.storedRK,
                        jsonProduct
                )
        ).thenReturn(productTest);
        when(
                rabbitTemplateError.convertSendAndReceive(
                        QueueConstants.exchangeName,
                        QueueConstants.requestRK,QueueConstants.messageQueue)
        ).thenReturn(null);
        when(
                rabbitTemplateError.convertSendAndReceive(
                        QueueConstants.exchangeName,
                        QueueConstants.storedRK,jsonProductError)
        ).thenReturn(productTestError);
        when(
                rabbitTemplateError.convertSendAndReceive(
                        QueueConstants.exchangeName,
                        QueueConstants.storedRK,jsonProduct)
        ).thenReturn(null);

        when(productSerializer.deserializeList(obj.toString())).thenReturn(productList);
        when(productSerializer.deserializeObject(productTest.toString())).thenReturn(productTest);
        when(productSerializer.serializeObject(productTest)).thenReturn(jsonProduct);

        when(productSerializerError.serializeObject(productTestError)).thenReturn(jsonProductError);
        when(productSerializerError.serializeObject(productTest)).thenReturn(jsonProduct);
        when(productSerializerError.deserializeObject(productTestError.toString())).thenReturn(productTestError);
        when(directExchange.getName()).thenReturn(QueueConstants.exchangeName);
    }
    @Test

    public void testGetProduct(){
        List<Product> products = new ArrayList<>();
        try{
            products = productService.getProducts();
        }catch (OperationErrorException e){

        }
        String responseString = products.toString();
        String productString = productList.toString();

        assertThat(responseString).isEqualTo(productString);
    }
    @Test()
    public void testGetProductError(){
        assertThatThrownBy(
                ()->productServiceError.getProducts())
                .isInstanceOf(OperationErrorException.class)
                .hasMessageContaining(ErrorConstants.ERROR_OPERATION);
    }
    @Test
    public void addProductTest(){
        Product product = null;
        try {
            product = productService.addProduct(productTest);
        }catch (OperationErrorException e){

        }
        assertThat(product).isEqualToComparingFieldByField(productTest);
    }
    @Test
    public void addProductTestError(){
        assertThatThrownBy(
                ()->productServiceError.addProduct(productTest))
                .isInstanceOf(OperationErrorException.class)
                .hasMessageContaining(ErrorConstants.ERROR_ADD);
    }
    @Test
    public void addProductTestError2(){
        assertThatThrownBy(
                ()->productServiceError.addProduct(productTestError))
                .isInstanceOf(OperationErrorException.class)
                .hasMessageContaining(ErrorConstants.ERROR_ADD);
    }
}
