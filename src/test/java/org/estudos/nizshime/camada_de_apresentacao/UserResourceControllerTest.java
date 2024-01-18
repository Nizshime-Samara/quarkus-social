package org.estudos.nizshime.camada_de_apresentacao;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.net.URL;

import org.estudos.nizshime.camada_de_dominio.dto.CreateUserRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserResourceControllerTest {
    /*
     * @TestHTTPResource("/users")
     * URL apiURL;
     * 
     * @Test
     * 
     * @Order(1)
     * public void testCreateUser() {
     * CreateUserRequest request = new CreateUserRequest();
     * request.setName("User Test");
     * request.setAge(30);
     * 
     * var response = given()
     * .contentType(ContentType.JSON)
     * .body(request)
     * .when()
     * .post("/users")
     * .then()
     * .statusCode(201)
     * .body(is(equalTo("Usuário com ID 1 foi persistido com sucesso.")));
     * 
     * // assertEquals(201, response.statusCode());
     * }
     * 
     * @Test
     * 
     * @Order(2)
     * void testListAllUsers() {
     * var response = given()
     * .contentType(ContentType.JSON)
     * .when()
     * .get(apiURL)
     * .then()
     * .log().all()
     * .statusCode(200)
     * .body("size()", is(1));
     * 
     * }
     * 
     * @Test
     * 
     * @Order(3)
     * void testDeleteUser() {
     * var response = given()
     * .when()
     * .delete("/users/1")
     * .then()
     * .statusCode(200)
     * .body(is(equalTo("Usuário com ID 1 foi excluído com sucesso.")));
     * }
     * 
     * @Test
     * 
     * @Order(4)
     * void testCreateUserValidationErrorTest() {
     * CreateUserRequest user = new CreateUserRequest();
     * user.setName(""); // Dado inválido
     * user.setAge(30);
     * 
     * var response = given()
     * .contentType(ContentType.JSON)
     * .body(user)
     * .when()
     * .post(apiURL)
     * .then()
     * .log().all() // Para depuração
     * .statusCode(400) // Espera-se um status 400 devido ao erro de validação
     * .body(containsString("Campo Name obrigatório"));
     * 
     * }
     */
}
