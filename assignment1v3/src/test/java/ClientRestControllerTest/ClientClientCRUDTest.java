package ClientRestControllerTest;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import com.bullish.assignment1v3.Assignment1v3Application;
import com.bullish.assignment1v3.controller.ClientController;
import com.bullish.assignment1v3.model.users.Client;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class ClientClientCRUDTest {

    @Autowired
    private ClientController clientController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(clientController);
    }

    // Test Cases for Client CRUD Operations

    // Test adding a new client - positive case
    @Test
    @DirtiesContext
    void testAddClientReturnClient() {
        // Arrange
        Client client = new Client("client7", "password123");

        // Act and Assert
        given()
            .contentType("application/json")
            .body(client)
        .when()
            .post("client_access/clients/client7")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(3))
            .body("username", equalTo("client7"))
            .body("password", equalTo("password123"));
    }

    // Test adding a client that already exists - negative case
    @Test
    @DirtiesContext
    void testAddClientReturnClientAlreadyExist() {
        // Arrange
        Client client = new Client("client7", "password123");

        // Act
        given()
            .contentType("application/json")
            .body(client)
        .when()
            .post("client_access/clients/client7")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(3))
            .body("username", equalTo("client7"))
            .body("password", equalTo("password123"));

        // Assert
        given()
            .contentType("application/json")
            .body(client)
        .when()
            .post("client_access/clients/client7")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // Test reading an existing client - positive case
    @Test
    @DirtiesContext
    void testReadClientReturnClient() {
        // Arrange
        Client client = new Client("client10", "password123");

        // Act
        given()
            .contentType("application/json")
            .body(client)
        .when()
            .post("client_access/clients/client10")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(3))
            .body("username", equalTo("client10"))
            .body("password", equalTo("password123"));

        // Assert
        given()
            .contentType("application/json")
        .when()
            .get("client_access/clients/client10")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(3))
            .body("username", equalTo("client10"))
            .body("password", equalTo("password123"));
    }

    // Test reading a client that doesn't exist - negative case
    @Test
    @DirtiesContext
    void testReadClientReturnNoClientExist() {
        // Assert
        given()
            .contentType("application/json")
        .when()
            .get("client_access/clients/client10")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Test updating an existing client - positive case
    @Test
    @DirtiesContext
    void testUpdateClientReturnClient() {
        // Arrange
        Client clientInit = new Client("client6", "password123");
        Client clientUpdated = new Client("client6", "newPassword456");

        // Act
        given()
            .contentType("application/json")
            .body(clientInit)
        .when()
            .post("client_access/clients/client6")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(3))
            .body("username", equalTo("client6"))
            .body("password", equalTo("password123"));

        given()
            .contentType("application/json")
        .when()
            .get("client_access/clients/client6")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(3))
            .body("username", equalTo("client6"))
            .body("password", equalTo("password123"));

        // Assert
        given()
            .contentType("application/json")
            .body(clientUpdated)
        .when()
            .put("client_access/clients/client6")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(3))
            .body("username", equalTo("client6"))
            .body("password", equalTo("newPassword456"));

        // Checking that the client was updated by requesting the updated client and checking it against the expected attributes
        given()
            .contentType("application/json")
        .when()
            .get("client_access/clients/client6")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(3))
            .body("username", equalTo("client6"))
            .body("password", equalTo("newPassword456"));
    }

    // Test updating a client that doesn't exist - negative case
    @Test
    @DirtiesContext
    void testUpdateClientReturnNoClientExist() {
        // Arrange
        Client clientUpdated = new Client("client1", "newPassword456");

        // Act and Assert
        given()
            .contentType("application/json")
            .body(clientUpdated)
        .when()
            .put("client_access/clients/client1")
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    // Test deleting an existing client - positive case
    @Test
    @DirtiesContext
    void testDeleteClientReturnClient() {
        // Arrange
        Client clientInit = new Client("client11", "password123");

        // Act
        given()
            .contentType("application/json")
            .body(clientInit)
        .when()
            .post("client_access/clients/client11")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(3))
            .body("username", equalTo("client11"))
            .body("password", equalTo("password123"));

        given()
            .contentType("application/json")
        .when()
            .get("client_access/clients/client11")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(3))
            .body("username", equalTo("client11"))
            .body("password", equalTo("password123"));

        // Assert
        given()
            .contentType("application/json")
            .body(clientInit)
        .when()
            .delete("client_access/clients/client11")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(3))
            .body("username", equalTo("client11"))
            .body("password", equalTo("password123"));

        // Checking that the client was deleted by trying to request the deleted client
        given()
            .contentType("application/json")
        .when()
            .get("client_access/clients/client11")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Test deleting a client that doesn't exist - negative case
    @Test
    @DirtiesContext
    void testDeleteClientReturnBadRequest() {
        // Arrange
        Client clientInit = new Client("client50", "password123");

        // Act and Assert
        given()
            .contentType("application/json")
            .body(clientInit)
        .when()
            .delete("client_access/clients/client50")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
}


