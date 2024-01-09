package org.estudos.nizshime.camada_de_infraestrutura.repository;

import javax.enterprise.context.ApplicationScoped;

import org.estudos.nizshime.camada_de_dominio.model.Post;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

/*Logica de manipulação por repositório*/
@ApplicationScoped // Jakarta enterprise context CDI container (Singleton)
public class PostRepository implements PanacheRepository<Post> {

}