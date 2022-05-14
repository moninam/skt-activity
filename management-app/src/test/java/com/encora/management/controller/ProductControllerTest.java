package com.encora.management.controller;

import com.encora.commons.constants.Routes;
import com.encora.commons.constants.ViewNames;
import com.encora.commons.dto.Product;
import com.encora.commons.enums.ProductType;
import com.encora.management.exception.OperationErrorException;
import com.encora.management.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {
    public MockMvc mockMvc;
    private ProductController controller;
    private ProductService productService;

    private Product productTest;

    private final List<Product> productList = new ArrayList<>();

    @Before
    public void setUp(){
        productService = mock(ProductService.class);
        productTest = new Product(1,"Pepsi", ProductType.FOOD,"Pepsi");
        try {
            when(productService.getProducts()).thenReturn(productList);
            when(productService.addProduct(productTest)).thenReturn(productTest);
        } catch (OperationErrorException e) {

        }

        controller = new ProductController(productService);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }
    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.WELCOME.getName()));
    }
    @Test
    public void testViewProducts() throws Exception {
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.PRODUCT_LIST.getName()));
    }
    @Test
    public void testViewAdd() throws Exception {
        mockMvc.perform(get("/product/add"))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.ADD_PRODUCT.getName()));
    }
    @Test
    public void testAddProduct() throws Exception {
        this.mockMvc.perform(post("/product/add")
                        .flashAttr("product",productTest)
                        .flashAttr(ViewNames.SAVED_PRODUCT_ATTR.getName(),productTest)
                        .flashAttr(ViewNames.SUCCESS_ACTION_ATTR.getName(),true)
                ).andExpect(status().isFound())
                .andExpect(redirectedUrl(Routes.ADD_ROUTE.getName()));
    }


}
