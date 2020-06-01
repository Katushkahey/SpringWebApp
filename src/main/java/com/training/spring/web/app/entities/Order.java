package com.training.spring.web.app.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "username")
    private String username;

    public Long getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Order() {

    }
}
