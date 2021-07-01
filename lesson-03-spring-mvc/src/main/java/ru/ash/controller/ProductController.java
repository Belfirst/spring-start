package ru.ash.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ash.persist.Product;
import ru.ash.persist.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model){
        logger.info("Product list page");
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model){
        logger.info("New product page requested");
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") Long id, Model model){
        logger.info("Edit product");
        model.addAttribute("product",productRepository.findById(id));
        return "edit/edit_product_form";
    }

    @PostMapping
    public String create(Product product){
        logger.info("Saving product");
        productRepository.insert(product);
        return "redirect:/product";
    }

    @PatchMapping("/{id}")
    public String update(Product product){
        productRepository.update(product);
        return "redirect:/product";
    }
}
