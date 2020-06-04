package com.training.spring.web.app.controllers;

import com.training.spring.web.app.entities.Order;
import com.training.spring.web.app.entities.OrderItem;
import com.training.spring.web.app.services.OrderService;
import com.training.spring.web.app.services.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.print.PSPrinterJob;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ShoppingCart cart;
    private OrderService orderService;

    @Autowired
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("items", cart.getItems());
        model.addAttribute("list", cart.getSetOfItems());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(Model model, @PathVariable("id") Long id) {
        cart.addProductById(id);
        return "redirect:/shop";
    }

    @GetMapping("/remove_from_cart/{id}")
    public String removeFromCart(Model model, @PathVariable("id") Long id) {
        cart.deleteProductById(id);
        System.out.println("Удалено на уровне контроллера");
        model.addAttribute("items", cart.getItems());
        model.addAttribute("list", cart.getSetOfItems());
        return "cart";
    }

    @GetMapping("/create_order")
    public String createOrder(Principal principal) {
        Order order = new Order();
        order.setItems(new ArrayList<>());
        order.setUsername(principal.getName());
//        cart.getItems().stream().forEach(i -> {
//            order.getItems().add(i);
//            i.setOrder(order);
//        });
//        cart.getItems().clear();
//        orderService.saveOrder(order);
        return "redirect:/shop";
    }
}
