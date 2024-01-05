package org.estudos.nizshime.camada_de_infraestrutura.repository;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import org.estudos.nizshime.camada_de_dominio.model.User;

/*Logica de manipulação por repositório*/
@ApplicationScoped // Jakarta enterprise context CDI container (Singleton)
public class UserRepository implements PanacheRepository<User> {

}