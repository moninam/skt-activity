package com.encora.microservice.service;

import com.encora.commons.dto.Product;
import com.encora.commons.enums.ProductType;
import com.encora.microservice.repository.ProductDAO;
import com.encora.microservice.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO:Implement test for service
public class ProductServiceTest {

    private ProductServiceImpl productService;
    private ProductDAO productDAO;

    private final Collection<Product> products = new ArrayList<>();

    private Product productTestRequest;

    private Product productTestResponse;

    @Before
    public void setUp(){
        productDAO = mock(ProductDAO.class);

        productTestRequest = new Product(0,"Coca Cola",ProductType.FOOD,"Coca Cola");
        productTestResponse = new Product(6,"Coca Cola",ProductType.FOOD,"Coca Cola");
        products.add(new Product(1,"Samsung", ProductType.CELL_PHONE,"Samsung"));
        products.add( new Product(2,"Chips",ProductType.FOOD,"Chips"));

        when(productDAO.findAll()).thenReturn(products);
        when(productDAO.add(productTestRequest)).thenReturn(productTestResponse);

        productService = new ProductServiceImpl();
        productService.setProductDAO(productDAO);
    }
    @Test
    public void getProductsTest(){
        List<Product> products = productService.getProducts();
        String response = products.toString();
        String expected = products.toString();
        assertThat(response).isEqualTo(expected);
    }
    @Test
    public void saveProduct(){
        Product product = productService.saveProduct(productTestRequest);
        assertThat(product).isEqualToComparingFieldByField(productTestResponse);
    }

}
