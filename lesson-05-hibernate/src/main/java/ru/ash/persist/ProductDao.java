package ru.ash.persist;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
public class ProductDao {

     EntityManagerFactory entityManagerFactory = new Configuration().
        configure("hibernate.cfg.xml").buildSessionFactory();

    public Optional<Product> findById(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        Product product = em.find(Product.class,id);
        em.close();
        return Optional.ofNullable(product);
    }

    public List<Product> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Product> select_u_from_product_u = em.createQuery("select u from Product u", Product.class).getResultList();
        em.close();
        return select_u_from_product_u;
    }

    public void saveOrUpdate(Product product){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    public void insert(Product product) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Product where id = :id").setParameter("id",id).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
