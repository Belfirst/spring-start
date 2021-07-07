package ru.ash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ash.entity.User;
import ru.ash.entity.UserDao;
import ru.ash.persist.Product;
import ru.ash.persist.ProductDao;

import java.util.List;
@Component
public class Service {

    UserDao userDao;
    ProductDao productDao;
    @Autowired
    public Service(UserDao userDao, ProductDao productDao) {
        this.userDao = userDao;
        this.productDao = productDao;
    }

    public User findUserById(Long id){
        return userDao.findById(id);
    }

    public Product findProductById(Long id){
        return productDao.findById(id);
    }

    public List<Product> getProductsForUser(Long id){
        return userDao.findUserById(id).getProducts();
    }

    public void getListUser(Long id){
        // не смог я сооборазить как зделать отношение один ко многим.
        // как сделать могие ко многим здесь слишком очивидно, по этому оставлю тут место
        // чтобы вписать когда вы раскажете это интересное решение.
        System.out.println(productDao.getProductById(id).getUser());
    }

    public void deleteProductById(Long id){
        productDao.delete(id);
    }

    public void deleteUserById(Long id){
        userDao.delete(id);
    }

    public void insertUser(User user){
        userDao.insert(user);
    }

}
