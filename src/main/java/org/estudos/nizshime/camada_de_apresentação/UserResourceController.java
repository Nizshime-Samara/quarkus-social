package org.estudos.nizshime.camada_de_apresentação;

import java.util.List;

import org.estudos.nizshime.camada_de_dominio.dto.CreateUserRequest;
import org.estudos.nizshime.camada_de_dominio.model.User;
import org.estudos.nizshime.camada_de_infraestrutura.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceController {

    private UserRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResourceController.class);

    @Inject // jakarta
    public UserResourceController(UserRepository repository) {
        this.repository = repository; // para os campos de idade e nome
    }

    /*
     * O Metodo Post nao é idempotente, sempre retornará um novo dado.
     * A anotação @Valid ao parâmetro do método createUser, aciona a validação
     * automática
     * que irá automaticamente validar o userRequest de acordo com as anotações de
     * validação
     * (@NotBlank, @NotNull, etc.) definidas na classe CreateUserRequest
     */
    @POST
    @Transactional

    public Response createUser(@Valid CreateUserRequest userRequest) {
        try {
            User user = new User();
            user.setName(userRequest.getName());
            user.setAge(userRequest.getAge());
            repository.persist(user);
            LOGGER.info("Usuário ID: {} foi persistido com sucesso.", user.getId());
            return Response.status(Response.Status.CREATED)
                    .entity(user)
                    .build();
        } catch (Exception e) {
            LOGGER.error("Erro ao criar usuário: {}", e.getMessage());
            throw e; // Re-lança a exceção para o manipulador padrão de exceções do JAX-RS
        }
    }

    @GET
    public Response listAllUsers() {
        // PanacheQuery<User> query = User.findAll();
        List<User> users = repository.listAll();

        if (users.isEmpty()) {
            LOGGER.info("Nenhum usuário foi encontrado na base de dados.");
            return Response.status(Response.Status.OK)
                    .entity("Nenhum usuário encontrado na base de dados.")
                    .build();
        }

        LOGGER.info("Foram recuperados um total de {} usuários com sucesso.", users.size());

        return Response.status(Response.Status.OK)
                .entity(users)
                .build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {

        User user = repository.findById(id);
        if (user != null) {
            repository.delete(user);
            LOGGER.info("Usuário com ID: {} foi excluído com sucesso.", id);
            return Response.status(Response.Status.OK)
                    .entity("Usuário com ID: {} foi excluído com sucesso." + id)
                    .build();
        } else {
            LOGGER.error("Usuário não encontrado com o ID: {}", id);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado com o ID: " + id)
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData) {

        try {
            User user = repository.findById(id);
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            repository.persist(user); // usar ou nao usar -> testar
            LOGGER.info("Usuário com ID: {} foi atualizado com sucesso.", id);

            return Response.status(Response.Status.OK)
                    .entity(user)
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado com o ID: " + id)
                    .build(); // Re-lança a exceção para o manipulador padrão de exceções do JAX-RS
        }
    }
}
