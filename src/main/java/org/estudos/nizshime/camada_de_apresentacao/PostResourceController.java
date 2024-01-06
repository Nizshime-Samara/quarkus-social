package org.estudos.nizshime.camada_de_apresentação;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.estudos.nizshime.camada_de_dominio.dto.CreatePostRequest;
import org.estudos.nizshime.camada_de_dominio.dto.PostResponse;
import org.estudos.nizshime.camada_de_dominio.model.Post;
import org.estudos.nizshime.camada_de_dominio.model.User;
import org.estudos.nizshime.camada_de_infraestrutura.repository.PostRepository;
import org.estudos.nizshime.camada_de_infraestrutura.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users/{userId}/posts") // sub recursos
public class PostResourceController {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PostResourceController.class);

    @Inject
    public PostResourceController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userIdLong, CreatePostRequest postRequest) {

        User userByIdSerarch = userRepository.findById(userIdLong);

        if (userByIdSerarch == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(postRequest.getText());
        post.setUser(userByIdSerarch);
        post.setDateTime(LocalDateTime.now());
        postRepository.persist(post);

        return Response.status(Response.Status.CREATED)
                .entity("Post com ID " + post.getId() + " foi enviado com sucesso.")
                .build();
    }

    @GET
    public Response listPost(@PathParam("userId") Long userId) {
        User user = userRepository.findById(userId);

        if (user == null) {

            LOGGER.error("Usuário ID: {} não encontrado.", user.getId());

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário" + user.getName() + "não encontrado.")
                    .build();

        }

        List<Post> posts = postRepository.list("user", user);

        if (posts.isEmpty()) {
            LOGGER.error("Posts para o usuário ID: {} não foram encontrados.", user.getId());
            return Response.status(Response.Status.OK)
                    .entity("Não foram encontrados posts para este usuário.")
                    .build();
        } else {
            // Se foram encontrados posts, transformá-los em PostResponse e retornar
            List<PostResponse> postResponses = posts.stream()
                    .map(post -> new PostResponse(post.getText(), post.getDateTime(), post.getUser().getName()))
                    .collect(Collectors.toList());

            LOGGER.info("Foram recuperados um total de {} usuários com sucesso.", postResponses.size());

            return Response.status(Response.Status.OK)
                    .entity(postResponses)
                    .build();
        }
    }

    @DELETE
    @Path("{postId}")
    @Transactional
    public Response deletePost(@PathParam("userId") Long userId, @PathParam("postId") Long postId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado.").build();
        }

        Post post = postRepository.find("user = ?1 and id = ?2", user, postId).firstResult();
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Post não encontrado ou não pertence ao usuário.")
                    .build();
        }

        postRepository.delete(post);
        LOGGER.info("Post com ID: {} do usuário com ID: {} foi excluído com sucesso.", postId, userId);
        return Response.status(Response.Status.ACCEPTED)
                .entity("Post foi excluído com sucesso.")
                .build();
    }
}