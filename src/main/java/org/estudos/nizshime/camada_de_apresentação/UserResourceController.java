package org.estudos.nizshime.camada_de_apresentação;

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

import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceController {

    @POST // Post nao é idempotente, sempre retornará um novo dado
    @Transactional // Apenas para metodos que farão persistencia ou escrita
    public Response createUser(CreateUserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        // nao foi necessario setar id pois este é auto-incrementado

        // Entidade pronta para persistir
        user.persist(); // post -> persiste na tabela

        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers() {
        PanacheQuery<User> query = User.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Transactional // Apenas para metodos que farão persistencia ou escrita
    @Path("{id}")
    // /users/1
    public Response deleteUser(@PathParam("id") int id) {
        User user = User.findById(id);

        if (user != null) {
            user.delete();
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Transactional // Apenas para metodos que farão persistencia ou escrita
    @Path("{id}") // atualiza alguma entidade
    public Response updateUser(@PathParam("id") int id, CreateUserRequest userData) {
        User user = User.findById(id);

        if (user != null) {
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            user.persist();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }
}
