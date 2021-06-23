package ru.ash;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ash.persist.Product;
import ru.ash.persist.ProductRepository;

public class ConsoleApp {
    public static void main(String[] args) {
        Product product;
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = context.getBean("productRepository",ProductRepository.class);
        productRepository.save(new Product(1L,"Oranges", 160));
        productRepository.save(new Product(2L,"Cherry", 100));
        productRepository.save(new Product(3L,"Blueberry", 200));
        productRepository.save(new Product(4L,"Watermelons", 150));
        productRepository.save(new Product(5L,"Peaches", 120));
        System.out.println("Cart");
        Cart cart = context.getBean("cart",Cart.class);
        cart.add(1L);
        cart.add(2L);
        cart.showCart();
        cart.delete(2L);
        cart.showCart();
        cart.add(3l);
        cart.showCart();
        System.out.println("Cart1");
        Cart cart1 = context.getBean("cart",Cart.class);
        cart1.add(1L);
        cart1.add(2L);
        cart1.showCart();
        cart1.delete(2L);
        cart1.showCart();
        cart1.add(3l);
        cart1.showCart();
    }
}
