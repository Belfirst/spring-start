package ru.ash;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForProduct {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Service service = context.getBean("service", Service.class);
        System.out.println(service.getProductsForUser(1L));
//        service.getListUser(4L);

//        User user1 = new User("Phil", 30);
//        user1.addProduct(new Product("Oranges", 160, user1));
//        user1.addProduct(new Product("Cherry", 100,user1));
//        user1.addProduct(new Product("Blueberry", 200, user1));
//        service.insertUser(user1);
//
//        User user2 = new User("Phil", 30);
//        user2.addProduct(new Product("Watermelons", 150, user2));
//        user2.addProduct(new Product("Peaches", 120, user2));
//        service.insertUser(user2);



    }

}
