package ru.ash.entity;

import javax.persistence.*;

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

    public User() {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
