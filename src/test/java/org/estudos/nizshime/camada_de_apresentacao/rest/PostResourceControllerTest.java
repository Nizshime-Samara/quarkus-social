package org.estudos.nizshime.camada_de_apresentacao.rest;

import java.net.URL;

import javax.inject.Inject;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import org.estudos.nizshime.camada_de_dominio.dto.CreatePostRequest;
import org.estudos.nizshime.camada_de_dominio.model.Post;
import org.estudos.nizshime.camada_de_dominio.model.User;
import org.estudos.nizshime.camada_de_infraestrutura.repository.PostRepository;
import org.estudos.nizshime.camada_de_infraestrutura.repository.UserRepository;
import org.junit.jupiter.api.*;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import javax.ws.rs.core.Response;

@QuarkusTest
@TestHTTPEndpoint(PostResourceController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostResourceControllerTest {

    @Inject
    UserRepository userRepository;

    @Inject
    PostRepository postRepository;

    Long userId;
    Long postId;

    @BeforeEach
    @Transactional
    public void setUP() {
        // usuario padrão dos testes
        var user = new User();
        user.setAge(30);
        user.setName("User Test");
        userRepository.persist(user);
        userId = user.getId();

        // criada a postagem para o usuario
        Post post = new Post();
        post.setText("Hello");
        post.setUser(user);
        postRepository.persist(post);
        postId = post.getId();
    }

    @AfterAll
    @Transactional
    public void cleanUpDatabase() {
        // userRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    public void savePostTest() {
        var postRequest = new CreatePostRequest();
        postRequest.setText("Some text");

        given()
                .contentType(ContentType.JSON)
                .body(postRequest)
                .pathParam("userId", userId)
                .when()
                .post()
                .then()
                .statusCode(201);
    }

    @Test
    public void postForAnInexistentUserTest() {
        var postRequest = new CreatePostRequest();
        postRequest.setText("Some text");

        var inexistentUserId = 999;

        given()
                .contentType(ContentType.JSON)
                .body(postRequest)
                .pathParam("userId", inexistentUserId)
                .when()
                .post()
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("should return 404 when user doesn't exist")
    public void listPostUserNotFoundTest() {
        var inexistentUserId = 999;

        given()
                .pathParam("userId", inexistentUserId)
                .when()
                .get()
                .then()
                .statusCode(500);
    }

    @Test
    void testListPost() {
        given()
                .pathParam("userId", userId)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("size()", Matchers.is(1));
    }

    @Test
    void testDeletePost() {
        given()
                .pathParam("userId", userId)
                .pathParam("postId", postId) // Usar o postId criado no método setUp
                .when()
                .delete("/{postId}")
                .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode())
                .body(equalTo("Post foi excluído com sucesso."));
    }
}
