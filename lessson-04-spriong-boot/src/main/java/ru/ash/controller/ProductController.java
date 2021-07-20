package ru.ash.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ash.persist.Product;
import ru.ash.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
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
//    @GetMapping
//    public String listPage(Model model,
//                           @RequestParam("productFilter") Optional<String> productFilter,
//                           @RequestParam("minCost") Optional<Integer> minCost,
//                           @RequestParam("maxCost") Optional<Integer> maxCost,
//                           @RequestParam("page") Optional<Integer> page,
//                           @RequestParam("size") Optional<Integer> size,
//                           @RequestParam(name = "sortBy",defaultValue = "id", required = false) String sortBy,
//                           @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir){
//        logger.info("Product list page");
//        Specification<Product> spec = Specification.where(null);
//        if(productFilter.isPresent() && !productFilter.get().isBlank()) {
//            spec = spec.and(ProductSpecifications.productPrefix(productFilter.get()));
//        }
//        if(minCost.isPresent()) {
//            spec = spec.and(ProductSpecifications.minCost(minCost.get()));
//        }
//
//        if(maxCost.isPresent()) {
//            spec = spec.and(ProductSpecifications.maxCost(maxCost.get()));
//        }
//
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//        model.addAttribute("boolSort" , sortDir.equals("desc"));
//
//        model.addAttribute("products", productRepository.findAll(spec,
//                PageRequest.of(page.orElse(1) - 1, size.orElse(3), sort)));
//        return "products";
//    }

    @GetMapping
    public String listPage(Model model, ProductListParams productListParams){
        model.addAttribute("reverseSortDir", productListParams.getSortDir().equals("asc") ? "desc" : "asc");
        model.addAttribute("products", productService.findWithFilter(productListParams));
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
        model.addAttribute("product",productService.findById(id).
                orElseThrow(() -> new NotFoundException("User not found")));
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        logger.info("Edit product");
        productService.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String update(@Valid Product product, BindingResult result){
        logger.info("Saving product");
        if(result.hasErrors()){
            return "product_form";
        }
        productService.save(product);
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
