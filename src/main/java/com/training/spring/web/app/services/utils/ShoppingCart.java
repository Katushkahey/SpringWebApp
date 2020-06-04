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
import java.util.*;

@Component // ShoppingCart является бином
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//отвечает за видимость (каждая корзина будет привязана к своей сессии)
public class ShoppingCart {

    private Set<OrderItem> setOfItems;
    private HashMap<OrderItem, Integer> items;

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        setOfItems = new HashSet<>();
        items = new HashMap<>();
    }

    public HashMap<OrderItem, Integer> getItems() {
        return items;
    }

    public Set<OrderItem> getSetOfItems() {
        return setOfItems;
    }

    public void addProductById(Long id) {
        Product product = productService.getProductById(id);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        items.put(orderItem, items.getOrDefault(orderItem, 0) + 1);
        setOfItems.add(orderItem);
    }

    public void deleteProductById(Long id) {
        Product product = productService.getProductById(id);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        Iterator it = items.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry mapItem = (Map.Entry) it.next();
            if (mapItem.getKey().equals(orderItem)) {
                it.remove();
            }
        }
        System.out.println("Удалено в мэпе");

        Iterator value = setOfItems.iterator();
        while (value.hasNext()) {
            OrderItem setItem = (OrderItem) value.next();
            if (setItem.equals(orderItem)) {
                value.remove();
            }
        }
        System.out.println("Удалено в сэте");
    }
}
