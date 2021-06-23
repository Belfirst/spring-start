package ru.ash;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.ash.persist.ProductRepository;

@Configuration
@ComponentScan("ru.ash")
public class AppConfig {

    @Bean("productRepository")
    public ProductRepository productRepository(){
        return new ProductRepository();
    }
    @Bean("cart")
    @Scope("prototype")
    public Cart cart(){
        return new Cart(productRepository());
    }

}
