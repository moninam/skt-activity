package com.encora.management.controller;

import com.encora.commons.dto.Product;
import com.encora.management.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/product")
public class ProductController {
    private static final String PRODUCT_ATRIBUTE = "product";
    private static final String PRODUCT_LIST = "list-products";
    private static final String ADD_PRODUCT = "new-product";
    private static final String WELCOME = "welcome";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String home(){
        return WELCOME;
    }
    @GetMapping("/list")
    public String viewProducts(Model model){
        //TODO: Make Logic to load from queue
        model.addAttribute(PRODUCT_ATRIBUTE,productService.getProducts());
        return PRODUCT_LIST;
    }
    @GetMapping("/add")
    public String addProductView(Model model){
        model.addAttribute("product", new Product());
        return ADD_PRODUCT;
    }
    @PostMapping("/add")
    public RedirectView addProduct(@ModelAttribute("product")Product product, RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView("/product/add",true);
        Product savedProduct = productService.addProduct(product);
        redirectAttributes.addFlashAttribute("savedProduct",savedProduct);
        redirectAttributes.addFlashAttribute("successAction",true);
        return redirectView;
    }

}
