package ru.ash.task;

import ru.ash.task.persist.Product;
import ru.ash.task.persist.ProductRepository;
import ru.ash.task.persist.ProductRepositoryImp;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        ProductRepository productRepositoryImp = new ProductRepositoryImp();
        productRepositoryImp.save(new Product(1L,"Oranges", 160));
        productRepositoryImp.save(new Product(2L,"Cherry", 100));
        productRepositoryImp.save(new Product(3L,"Blueberry", 200));
        productRepositoryImp.save(new Product(4L,"Watermelons", 150));
        productRepositoryImp.save(new Product(5L,"Peaches", 120));
        productRepositoryImp.save(new Product(6L,"Tomato", 300));
        productRepositoryImp.save(new Product(7L,"Cucumbers", 70));
        productRepositoryImp.save(new Product(8L,"Cabbage", 80));
        productRepositoryImp.save(new Product(9L,"Meat", 280));
        productRepositoryImp.save(new Product(10L,"Water", 10));

        sc.setAttribute("productRepository", productRepositoryImp);

    }
}
