package ru.ash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ash.controller.ProductListParams;
import ru.ash.persist.Product;
import ru.ash.persist.ProductRepository;
import ru.ash.persist.ProductSpecifications;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository userRepository) {
        this.productRepository = userRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findWithFilter(ProductListParams productListParams) {
        Specification<Product> spec = Specification.where(null);

        if(productListParams.getProductFilter() != null && !productListParams.getProductFilter().isBlank()) {
            spec = spec.and(ProductSpecifications.productPrefix(productListParams.getProductFilter()));
        }
        if(productListParams.getMinCost() != null) {
            spec = spec.and(ProductSpecifications.minCost(productListParams.getMinCost()));
        }

        if(productListParams.getMaxCost() != null) {
            spec = spec.and(ProductSpecifications.maxCost(productListParams.getMaxCost()));
        }

        Sort sort;

        if(!productListParams.getSortDir().isBlank()) {
            sort = productListParams.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                    Sort.by(productListParams.getSortBy()).ascending() :
                    Sort.by(productListParams.getSortBy()).descending();
        } else {
            sort = Sort.by("id").ascending();
        }
        return productRepository.findAll(spec,
                PageRequest.of(Optional.ofNullable(productListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(productListParams.getSize()).orElse(3),
                        sort));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
