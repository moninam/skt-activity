package com.encora.management.controller;

import com.encora.management.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String home(){
        return "welcome";
    }
    @GetMapping("/list")
    public String viewProducts(Model model){
        return "list-products";
    }
    @GetMapping("/add")
    public String addProducViewt(Model model){
        return "new-product";
    }


}
