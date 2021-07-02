package ru.ash;

import org.hibernate.cfg.Configuration;
import ru.ash.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration().
                configure("hibernate.cfg.xml").buildSessionFactory();

        EntityManager em = entityManagerFactory.createEntityManager();

        // INSERT
//        em.getTransaction().begin(); // открываем транзакцию
//        List.of(
//                new User(null,"user2",18),
//                new User(null,"user3",12),
//                new User(null,"user4",30),
//                new User(null,"user5",50),
//                new User(null,"user6",40)
//
//        ).forEach(em::persist);

//        User user = new User(null,"user1",25);
//        em.persist(user);
//        em.getTransaction().commit(); // коммитим транзакцию

        // SELECT
//        User user = em.find(User.class,1L); // можно найти одного по id
//        System.out.println(user);

        // для того чтоб вызвать нескольких нужно сделать запрос на своеобразном языке HQL или JPQL
//        List<User> users = em.createQuery("select u from User u where u.age > 25", User.class).
//                getResultList(); //запрос который возвращает лист user == SQL: "select * from User where age > 25"
        // (если указать User.class класс не придется делать приведение типов)

//        List<User> users = em.createQuery("select u from User u where u.age > :age", User.class)
//                .setParameter("age",25)
//                .getResultList(); // так же можно передавать значение в виде параметра
//        System.out.println(users);

        // делаем запрос с использованием анотаций в классе из которого создаем таблицу
//        Long countUsers = em.createNamedQuery("countUsers", Long.class).getSingleResult();
//        System.out.println(countUsers);

        // молжно писать и на SQL, но могут быть проблем из-за специфичность разных баз данных
//        List<User> users = em.createNativeQuery("select * from users", User.class).getResultList();

        // UPDATE

//        em.getTransaction().begin();
        // 1 способ
//        em.createQuery("update User set age = 22 where id = 1").executeUpdate();

        // 2 способ
//        User user = em.find(User.class, 1L);
//        user.setAge(25);// hibernate подменяет объект наследником и устанавливает ему значение поля через seter

        // 3 способ
//        User user = new User(1L,"user1",38);
//        em.merge(user);

//        em.getTransaction().commit();

        // DELETE
        em.getTransaction().begin();

        // 1 способ
//        em.createQuery("delete User where id = 1").executeUpdate();

        //2 способ
//        User user = em.find(User.class, 7L);
        User user = em.getReference(User.class, 7L);// можно использовать его вроде как более эффективен
        em.remove(user);



        em.getTransaction().commit();
        em.close(); // обязательно закрываем EntityManager
    }
}
