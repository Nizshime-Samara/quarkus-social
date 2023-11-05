package org.estudos.nizshime.camada_de_apresentação;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.estudos.nizshime.camada_de_dominio.dto.CreateUserRequest;

@Path("/users")
public class UserResourceController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserRequest CreateUserRequest) {
        return Response.ok().build();

    }

}
