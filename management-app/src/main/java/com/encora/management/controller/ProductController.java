package com.encora.management.controller;

import com.encora.commons.constants.Routes;
import com.encora.commons.constants.ViewNames;
import com.encora.commons.dto.Product;
import com.encora.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String home(){
        return ViewNames.WELCOME.getName();
    }
    @GetMapping("/list")
    public String viewProducts(Model model){
        //TODO: Make Logic to load from queue
        model.addAttribute(ViewNames.PRODUCT_ATTR.getName(),productService.getProducts());
        return ViewNames.PRODUCT_LIST.getName();
    }
    @GetMapping("/add")
    public String addProductView(Model model){
        model.addAttribute(ViewNames.PRODUCT_ATTR.getName(), new Product());
        return ViewNames.ADD_PRODUCT.getName();
    }
    @PostMapping("/add")
    public RedirectView addProduct(@ModelAttribute("product")Product product, RedirectAttributes redirectAttributes){
        final RedirectView redirectView = new RedirectView(Routes.ADD_ROUTE.getName(),true);
        //TODO: Implement Queue part
        Product savedProduct = productService.addProduct(product);
        redirectAttributes.addFlashAttribute(ViewNames.SAVED_PRODUCT_ATTR.getName(),savedProduct);
        redirectAttributes.addFlashAttribute(ViewNames.SUCCESS_ACTION_ATTR.getName(),true);
        return redirectView;
    }
    /*
    @GetMapping(value = "test")
    public String sendMessage() {
        List<Product> productList = new ArrayList<>(productService.getProducts());
        MessageList messageList = new MessageList(productList);
        queueSender.send(messageList);
        return message;
    }*/

}
