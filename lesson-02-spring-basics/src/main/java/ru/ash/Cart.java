package ru.ash;

import ru.ash.persist.Product;
import ru.ash.persist.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    ProductRepository pr;
    private List<Product> products;

    public Cart(ProductRepository pr) {
        this.pr = pr;
        products = new ArrayList<>();
    }

    public void add(Long id){
        products.add(pr.findById(id));
    }

    public void delete(Long id){
        products.remove(pr.findById(id));
    }

    public void showCart(){
        System.out.println(products);

    }
}
