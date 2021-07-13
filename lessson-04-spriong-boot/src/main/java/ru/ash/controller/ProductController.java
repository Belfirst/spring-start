package ru.ash.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ash.persist.Product;
import ru.ash.persist.ProductRepository;
import ru.ash.persist.ProductSpecifications;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Способ с использованием методов spring date
//    @GetMapping
//    public String listPage(Model model,
//                           @RequestParam("minCost") Optional<Integer> minCost,
//                           @RequestParam("maxCost") Optional<Integer> maxCost){
//        logger.info("Product list page");
//        List<Product> products;
//        if (maxCost.isPresent() && minCost.isPresent())
//            products = productRepository.findProductsByCostAfterAndCostBefore(minCost.get(),maxCost.get());
//        else if(maxCost.isPresent())
//            products = productRepository.findProductsByCostBefore(maxCost.get());
//        else if(minCost.isPresent())
//            products = productRepository.findProductsByCostAfter(minCost.get());
//        else {
//            products = productRepository.findAll();
//        }
//        model.addAttribute("products", products);
//        return "products";
//    }

        // способ с использование критерий
    @GetMapping
    public String listPage(Model model,
                           @RequestParam("productFilter") Optional<String> productFilter,
                           @RequestParam("minCost") Optional<Integer> minCost,
                           @RequestParam("maxCost") Optional<Integer> maxCost,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam(name = "sortBy",defaultValue = "id", required = false) String sortBy){
        logger.info("Product list page");
        Specification<Product> spec = Specification.where(null);
        if(productFilter.isPresent() && !productFilter.get().isBlank()) {
            spec = spec.and(ProductSpecifications.productPrefix(productFilter.get()));
        }
        if(minCost.isPresent()) {
            spec = spec.and(ProductSpecifications.minCost(minCost.get()));
        }

        if(maxCost.isPresent()) {
            spec = spec.and(ProductSpecifications.maxCost(maxCost.get()));
        }

        model.addAttribute("products", productRepository.findAll(spec,
                PageRequest.of(page.orElse(1) - 1, size.orElse(3), Sort.by(sortBy))));
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model){
        logger.info("New product page requested");
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model){
        logger.info("Edit product");
        model.addAttribute("product",productRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User not found")));
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        logger.info("Edit product");
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String update(@Valid Product product, BindingResult result){
        logger.info("Saving product");
        if(result.hasErrors()){
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex){
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message",ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
