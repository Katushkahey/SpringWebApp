package com.training.spring.web.app.controllers;

import com.training.spring.web.app.entities.Product;
import com.training.spring.web.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class  MainController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/index")  //будем перехватывать get-запросы, заканчивающиеся на "/index"
    public String homePage() {
        return "index";  // вернули имя html-страницы
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
        List<Product> listOfProducts = productService.getListOfProducts();
        model.addAttribute("products", listOfProducts);
        return "shop";
    }

    @GetMapping("/details/{id}")
    public String detailsPage(Model model, @PathVariable("id") Long id) {
        Product selectedProduct = productService.getProductById(id);
        model.addAttribute("selectedProduct", selectedProduct);
        return "details";
    }

    @GetMapping("/products/delete/{id}")
    public String removeProductById(@PathVariable("id") Long id) {
        productService.removeProductById(id);
        return "redirect:/shop";
    }
}
