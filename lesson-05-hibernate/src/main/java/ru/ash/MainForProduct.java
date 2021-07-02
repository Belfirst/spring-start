package ru.ash;

import ru.ash.persist.ProductDao;

public class MainForProduct {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
//        productDao.insert(new Product("Oranges", 160));
//        productDao.insert(new Product("Cherry", 100));
//        productDao.insert(new Product("Blueberry", 200));
//        productDao.insert(new Product("Watermelons", 150));
//        productDao.insert(new Product("Peaches", 120));
//
//        System.out.println(productDao.findAll());
//
//        productDao.saveOrUpdate(new Product(1L,"Oranges",50));

//        productDao.delete(1L);
        System.out.println(productDao.findAll());


    }

}
