package ru.ash.task.persist;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    void save(Product product);

    Product findById(long id);

    void delete(long id);
}
