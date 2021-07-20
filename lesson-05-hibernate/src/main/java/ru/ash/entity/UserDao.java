package ru.ash.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ash.MyEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class UserDao {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDao(MyEntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory.getEntityManagerFactory();
    }

    public User findById(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            return em.find(User.class,id);
        } finally {
            em.close();
        }
    }

    public List<User> findAll(){
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select u from User u", User.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void saveOrUpdate(User user){
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(user);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void insert(User user){
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void delete(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public User findUserById(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            return em.createQuery("SELECT c from User c join fetch c.products where c.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
