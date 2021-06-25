package ru.ash.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init(){
        this.insert(new Product(1L,"Oranges", 160));
        this.insert(new Product(2L,"Cherry", 100));
        this.insert(new Product(3L,"Blueberry", 200));
        this.insert(new Product(4L,"Watermelons", 150));
        this.insert(new Product(5L,"Peaches", 120));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public void insert(Product product){
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    public Product findById(long id){
        return productMap.get(id);
    }

    public void delete(long id){
        productMap.remove(id);
    }
}
