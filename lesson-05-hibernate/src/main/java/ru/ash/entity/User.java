package ru.ash.entity;

import ru.ash.persist.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")// указываем название таблицы
// можно объявлять запросы прямо сдесь и вызывать готовые в main
@NamedQueries({
        @NamedQuery(name= "allUsers", query = "select u from User u"),
        @NamedQuery(name= "userWithAgeBetween", query = "select u from User u where u.age between  : min and :max"),
        @NamedQuery(name= "countUsers", query = "select count(u) from User u")
})
public class User {
    @Id  // указываем поле являющеся первичным ключом
    @GeneratedValue(strategy = GenerationType.IDENTITY) // для автомотической генерации id
    @Column(length = 512, nullable = false) // помечаем колонки и можно указывать параметры калонок (можно не указывать)
    private Long id;
    @Column
    private String username;
    @Column
    private Integer age;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public User() {
    }

    public User(String username, Integer age) {
        this.id = null;
        this.username = username;
        this.age = age;
    }

    public User(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        product.setUser(this);
        products.add(product);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
