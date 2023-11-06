package org.estudos.nizshime.camada_de_apresentação;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.estudos.nizshime.camada_de_dominio.dto.CreateUserRequest;
import org.estudos.nizshime.camada_de_dominio.model.User;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceController {

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest) {

        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getIdade());
        // nao foi necessario setar id pois este é auto-incrementado

        // Entidade pronta para persistir
        user.persist(); // post -> persiste na tabela

        return Response.ok().build();
    }

    @GET
    public Response listAllUsers() {
        return Response.ok().build();
    }

}
