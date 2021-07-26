package ru.ash.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.ash.controller.ProductListParams;
import ru.ash.persist.Product;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {

    List<Product> findAll();

    Page<Product> findWithFilter(ProductListParams productListParams);

    Optional<Product> findById(Long id);

    void save(Product product);

    void deleteById(Long id);
}
