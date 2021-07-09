package ru.ash.persist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCostBefore(Integer max);
    List<Product> findProductsByCostAfter(Integer min);
    List<Product> findProductsByCostAfterAndCostBefore(Integer min, Integer max);
}
