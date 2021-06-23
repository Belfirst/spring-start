package ru.ash;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ash.persist.Product;
import ru.ash.persist.ProductRepository;

public class ConsoleApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Cart cart = context.getBean("cart",Cart.class);
        cart.add(0L);
        cart.add(1L);
        cart.add(2L);
    }
}
