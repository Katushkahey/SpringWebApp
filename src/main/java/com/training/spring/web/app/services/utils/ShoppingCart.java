package com.training.spring.web.app.services.utils;

import com.training.spring.web.app.entities.OrderItem;
import com.training.spring.web.app.entities.Product;
import com.training.spring.web.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component // ShoppingCart является бином
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS) //отвечает за видимость (каждая корзина будет привязана к своей сессии)
public class ShoppingCart {

    private List<OrderItem> items;

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addProductById(Long id) {
        Product product = productService.getProductById(id);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        items.add(orderItem);
    }

    public void deleteProductById(Long id) {
        for (OrderItem orderItem:items) {
            System.out.println(orderItem.getProduct().getTitle());
            if (orderItem.getProduct().getId() == id) {
                items.remove(orderItem);
                break;
            }
        }
    }

}
