package org.estudos.nizshime.camada_de_apresentacao.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.net.URL;

import javax.transaction.Transactional;

import org.estudos.nizshime.camada_de_dominio.dto.CreateUserRequest;
import org.estudos.nizshime.camada_de_infraestrutura.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // indica ao JUnit 5 que você deseja uma única instância da classe de
                                                // teste para todos os métodos de teste na classe.
public class UserResourceControllerTest {

    @Inject
    UserRepository userRepository;

    /*
     * faz a limpeza dos dados armazenados nas tabelas do H2 utilizadas para
     * executar os
     * testes desta classe ao final da execução dos testes
     */
    @AfterAll
    @Transactional
    public void cleanUpDatabase() {
        userRepository.deleteAll();
    }

    @TestHTTPResource("/users")
    URL apiURL;

    @Test
    @Order(1)
    void testCreateUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("User Test");
        request.setAge(30);

        var response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body(is(equalTo("Usuário com ID 1 foi persistido com sucesso.")));
    }

    @Test
    @Order(3)
    void testDeleteUser() {
        var response = given()
                .when()
                .delete("/users/1")
                .then()
                .statusCode(200)
                .body(is(equalTo("Usuário com ID 1 foi excluído com sucesso.")));
    }

    @Test
    @Order(2)
    void testListAllUsers() {
        var response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    void testUpdateUser() {

    }

    @Test
    @Order(4)
    void testCreateUserValidationErrorTest() {
        CreateUserRequest user = new CreateUserRequest();
        user.setName(""); // Dado inválido
        user.setAge(30);

        var response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(apiURL)
                .then()
                .log().all() // Para depuração
                .statusCode(400) // Espera-se um status 400 devido ao erro de validação
                .body(containsString("Campo Name obrigatório"));

    }

}
