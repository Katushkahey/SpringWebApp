package com.training.spring.web.app.services;

import com.training.spring.web.app.entities.Product;
import com.training.spring.web.app.repoositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getListOfProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public void removeProductById(Long id) {
        productRepository.deleteById(id);
    }
}
