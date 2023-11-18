package org.estudos.nizshime.camada_de_apresentação;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.estudos.nizshime.camada_de_dominio.dto.CreateUserRequest;
import org.estudos.nizshime.camada_de_dominio.model.User;
import org.estudos.nizshime.camada_de_infraestrutura.repository.UserRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceController {

    private UserRepository repository;

    @Inject
    public UserResourceController(UserRepository repository) {
        this.repository = repository;
    }

    @POST // Post nao é idempotente, sempre retornará um novo dado
    @Transactional // Apenas para metodos que farão persistencia ou escrita
    public Response createUser(CreateUserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        // nao foi necessario setar id pois este é auto-incrementado

        // Entidade pronta para persistir
        repository.persist(user);

        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers() {
        // PanacheQuery<User> query = User.findAll();
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Transactional // Apenas para metodos que farão persistencia ou escrita
    @Path("{id}")
    // /users/1
    public Response deleteUser(@PathParam("id") Long id) {

        User user = repository.findById(id);

        if (user != null) {
            repository.delete(user);
            ;
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Path("{id}") // atualiza alguma entidade
    @Transactional // Apenas para metodos que farão persistencia ou escrita
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData) {

        // User user = User.findById(id);
        User user = repository.findById(id);

        if (user != null) {
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            repository.persist(user); // usar ou nao usar -> testar
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado com o ID: " + id).build();

    }
}
