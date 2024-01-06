package org.estudos.nizshime.camada_de_apresentacao;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.containsString;

import org.estudos.nizshime.camada_de_dominio.dto.CreateUserRequest;
import org.estudos.nizshime.camada_de_dominio.model.User;
import org.estudos.nizshime.camada_de_infraestrutura.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class UserResourceControllerTest {

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {

    }

    @Test
    void testDeleteUser() {

    }

    @Test
    void testListAllUsers() {

    }

    @Test
    void testUpdateUser() {

    }

}
