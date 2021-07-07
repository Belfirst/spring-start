package ru.ash.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ash.MyEntityManagerFactory;
import ru.ash.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class ProductDao {

     private final EntityManagerFactory entityManagerFactory;

     @Autowired
     public ProductDao(MyEntityManagerFactory entityManagerFactory){
         this.entityManagerFactory = entityManagerFactory.getEntityManagerFactory();
     }

    public Product findById(long id){
        return executeForEntityManager(em -> em.find(Product.class,id));
    }

    public List<Product> findAll() {
        return executeForEntityManager(em ->em.createQuery("select u from Product u", Product.class)
                .getResultList());
    }

    public void saveOrUpdate(Product product){
        executeInTransaction(em -> em.merge(product));
    }

    public void insert(Product product) {
        executeInTransaction(em -> em.persist(product));
    }

    public void delete(long id){
        executeInTransaction(em -> em.createQuery("delete from Product where id = :id")
                .setParameter("id",id).executeUpdate());
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function ){
         EntityManager em = entityManagerFactory.createEntityManager();
         try{
             return function.apply(em);
         } finally {
             em.close();
         }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer){
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Product getProductById(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery("SELECT c from Product c join fetch c.user where c.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
