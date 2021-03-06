package com.encora.management.controller;

import com.encora.commons.constants.Routes;
import com.encora.commons.constants.ViewNames;
import com.encora.commons.dto.Product;
import com.encora.commons.enums.ProductType;
import com.encora.management.exception.OperationErrorException;
import com.encora.management.service.ProductService;
import org.apache.coyote.http2.ConnectionException;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String home(){
        return ViewNames.WELCOME.getName();
    }
    @GetMapping("/list")
    public String viewProducts(Model model){
        try{
            model.addAttribute(ViewNames.PRODUCT_ATTR.getName(),productService.getProducts());
            return ViewNames.PRODUCT_LIST.getName();
        }catch (OperationErrorException exc){
            List<String> errors = new ArrayList<>();
            errors.add(exc.getMessage());
                model.addAttribute(ViewNames.ERROR_ATTR.getName(),errors);
            model.addAttribute(ViewNames.ERROR_HAPPENED.getName(),true);
            return ViewNames.PRODUCT_LIST.getName();
        }

    }
    @ExceptionHandler({ConnectionException.class, AmqpConnectException.class})
    public String handleException() {
        return ViewNames.ERROR_VIEW.getName();
    }
    @GetMapping("/add")
    public String addProductView(Model model){
        List<ProductType> enumsValues = Arrays.asList(ProductType.values());

        model.addAttribute(ViewNames.TYPE_ATTR.getName(),enumsValues);
        model.addAttribute(ViewNames.PRODUCT_ATTR.getName(), new Product());
        return ViewNames.ADD_PRODUCT.getName();
    }
    @PostMapping("/add")
    public RedirectView addProduct(@ModelAttribute("product")Product product, RedirectAttributes redirectAttributes,
    BindingResult bindingResult){
        final RedirectView redirectView = new RedirectView(Routes.ADD_ROUTE.getName(),true);

        if(bindingResult.hasErrors()){
            redirectAttributes.addAttribute(ViewNames.ERROR_ATTR.getName(),bindingResult.getAllErrors());
            redirectAttributes.addAttribute(ViewNames.ERROR_HAPPENED.getName(),true);
            return redirectView;
        }

        try{
            Product savedProduct = productService.addProduct(product);
            redirectAttributes.addFlashAttribute(ViewNames.SAVED_PRODUCT_ATTR.getName(),savedProduct);
            redirectAttributes.addFlashAttribute(ViewNames.SUCCESS_ACTION_ATTR.getName(),true);
        }catch (OperationErrorException e){
            redirectAttributes.addFlashAttribute(ViewNames.ERROR_ACTION_ATTR.getName(),true);
        }

        return redirectView;
    }

}
