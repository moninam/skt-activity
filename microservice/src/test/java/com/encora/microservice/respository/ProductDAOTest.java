package com.encora.microservice.respository;

import com.encora.commons.constants.ProceduresNames;
import com.encora.commons.dto.Product;
import com.encora.commons.enums.ProductType;
import com.encora.microservice.repository.impl.ProductDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductDAOTest {

    private JdbcTemplate jdbcTemplate;
    private ProductDAOImpl productDAO;

    private ProductDAOImpl productDAOError;

    private ProductDAOImpl productDAOErrorNotId;

    private SimpleJdbcCall callListProduct;

    private SimpleJdbcCall callAddProduct;

    private SimpleJdbcCall callAddProductError;

    private SimpleJdbcCall callAddProductErrorNotId;

    private SqlParameterSource in;

    private SqlParameterSource inAdd;
    private final Map<String,Object> mapProduct = new HashMap<>();

    private final Map<String,Object> mapAddProduct = new HashMap<>();

    private final Map<String,Object> mapAddProductError = new HashMap<>();

    private Product productRequest;

    private Product productResponse;

    private Product productResponseError;
    private final List<Product> listProduct = new ArrayList<>();
    @Before
    public void setUp(){
        initMocks();
        initComponents();
    }
    private void initMocks(){
        jdbcTemplate = mock(JdbcTemplate.class);
        callListProduct = mock(SimpleJdbcCall.class);
        callAddProduct = mock(SimpleJdbcCall.class);
        callAddProductError = mock(SimpleJdbcCall.class);
        callAddProductErrorNotId = mock(SimpleJdbcCall.class);
        inAdd = mock(MapSqlParameterSource.class);
        in = mock(MapSqlParameterSource.class);

        productRequest= new Product(null,"Pepsi",ProductType.FOOD,"Pepsi");
        productResponse= new Product(7,"Pepsi",ProductType.FOOD,"Pepsi");
        productResponseError= new Product(0,"Pepsi",ProductType.FOOD,"Pepsi");

        listProduct.add(new Product(1,"Pepsi", ProductType.FOOD,"Pepsi"));
        listProduct.add(new Product(2,"Fanta",ProductType.FOOD,"Fanta"));

        mapProduct.put(ProceduresNames.LIST_PRODUCTS_SET,listProduct);
        mapAddProduct.put(ProceduresNames.ID_PARAM, 7);

        when(callListProduct.execute(in)).thenReturn(mapProduct);
        when(callAddProduct.execute(inAdd)).thenReturn(mapAddProduct);
        when(callAddProductError.execute(inAdd)).thenReturn(null);
        when(callAddProductErrorNotId.execute(inAdd)).thenReturn(mapAddProductError);
    }
    private void initComponents(){
        productDAO = new ProductDAOImpl();
        productDAOError = new ProductDAOImpl();
        productDAOErrorNotId = new ProductDAOImpl();

        productDAO.setJdbcTemplate(jdbcTemplate);
        productDAO.setCallListProduct(callListProduct);
        productDAO.setInParameter(in);
        productDAO.setCallAddProduct(callAddProduct);
        productDAO.setInAddParameter(inAdd);

        productDAOError.setJdbcTemplate(jdbcTemplate);
        productDAOError.setCallAddProduct(callAddProductError);
        productDAOError.setInAddParameter(inAdd);

        productDAOErrorNotId.setJdbcTemplate(jdbcTemplate);
        productDAOErrorNotId.setCallAddProduct(callAddProductErrorNotId);
        productDAOErrorNotId.setInAddParameter(inAdd);
    }
    @Test
    public void findAllTest(){
       Collection<Product> products = productDAO.findAll();
       String response = products.toString();
       String expected = listProduct.toString();
       assertThat(response).isEqualTo(expected);
    }
    @Test
    public void addTest(){
        Product product = productDAO.add(productRequest);
        assertThat(product).isEqualToComparingFieldByField(productResponse);
    }

    @Test
    public void addErrorTest(){
        Product product = productDAOError.add(productRequest);
        assertThat(product).isEqualToComparingFieldByField(productResponseError);
    }
    @Test
    public void addErrorNoIdTest(){
        Product product = productDAOErrorNotId.add(productRequest);
        assertThat(product).isEqualToComparingFieldByField(productResponseError);
    }
}
