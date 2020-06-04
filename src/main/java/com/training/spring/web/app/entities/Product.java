package com.training.spring.web.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    public Product() {
    }

    public Product(long id, String name, int price) {
        this.id = id;
        this.title = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        } else if (this == obj) {
            return true;
        }
        return this.id == ((Product) obj).getId();
    }

    @Override
    public String toString() {
        return String.format("Product: [id = %d, title = %s, price = %d]", id, title, price);
    }
}


