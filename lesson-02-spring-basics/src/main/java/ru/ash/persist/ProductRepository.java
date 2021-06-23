package ru.ash.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductRepository {
    private List<Product> products;

    @Autowired
    private Product product1;
    @Autowired
    private Product product2;
    @Autowired
    private Product product3;
    @Autowired
    private Product product4;
    @Autowired
    private Product product5;


    @Autowired
    public ProductRepository(List<Product> products){
        this.products = products;
    }

    public void insert(Product product){
        products.add(product);
    }

    public Product findById(long id){
        return products.get((int)id);
    }

    public List<Product> findAll() {
        return products;
    }
}
