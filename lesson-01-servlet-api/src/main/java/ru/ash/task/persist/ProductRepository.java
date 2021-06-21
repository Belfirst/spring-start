package ru.ash.task.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {
    private Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private AtomicLong identity = new AtomicLong(0);
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }
    public void save(Product product){
        if(product.getId() == null){
            long id = identity.incrementAndGet();
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    public Product findById(long id){
        return productMap.get(id);
    }

    public void delete(long id){
        productMap.remove(id);
    }
}
