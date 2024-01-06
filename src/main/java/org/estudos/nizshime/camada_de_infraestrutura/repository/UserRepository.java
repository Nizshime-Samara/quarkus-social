package org.estudos.nizshime.camada_de_infraestrutura.repository;

import org.estudos.nizshime.camada_de_dominio.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/*Logica de manipulação por repositório*/
@ApplicationScoped // Jakarta enterprise context CDI container (Singleton)
public class UserRepository implements PanacheRepository<User> {

}