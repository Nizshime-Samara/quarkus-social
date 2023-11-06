package org.estudos.nizshime.camada_de_dominio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "users")
public class User extends PanacheEntity {

    public User() {
    }

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    // public Integer getId() {
    // return this.id;
    // }

    // public void setId(Integer id) {
    // this.id = id;
    // }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // Define uma noção de igualdade de objeto
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    // Retorna um valor de hash para o objeto, que é usado em algumas coleções para
    // encontrar objetos eficientemente
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Representaçao para o estado do objeto
    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", age='" + getAge() + "'" +
                "}";
    }
}