package ru.ash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ash.persist.Product;
import ru.ash.persist.ProductRepository;

import java.util.List;
@Component
public class Cart {
    ProductRepository pr;
    private List<Product> products;

    @Autowired
    public Cart() {
        this.pr = pr;
//        products = new ArrayList<>();
    }

    public void add(Long id){
        products.add(pr.findById(id));
    }

    public void delete(Long id){
        products.remove(pr.findById(id));
    }
}
