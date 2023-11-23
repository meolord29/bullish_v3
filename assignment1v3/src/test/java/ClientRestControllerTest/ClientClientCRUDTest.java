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

    // _C_RUD for Client #1
    @Test
    @DirtiesContext
    void testAddClientReturnClient() {
        // Arrange
        Client client = new Client("client7", "password123");

        given()
        .contentType("application/json")
        .body(client)
        .when().post("client_access/clients/client7")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(3))
        .body("username", equalTo("client7"))
        .body("password", equalTo("password123"));
    }

    // _C_RUD for Client #2
    @Test
    @DirtiesContext
    void testAddClientReturnClientAlreadyExist() {

        // Arrange
        Client client = new Client("client7", "password123");

        given()
        .contentType("application/json")
        .body(client)
        .when().post("client_access/clients/client7").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(3))
        .body("username", equalTo("client7"))
        .body("password", equalTo("password123"));

        // Act and Assert
        given()
        .contentType("application/json")
        .body(client)
        .when()
        .post("client_access/clients/client7")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // C_R_UD for Client #1
    @Test
    @DirtiesContext
    void testReadClientReturnClient() {
        // Arrange
        Client client = new Client("client10", "password123");

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

        // Act and Assert
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

    // C_R_UD for Client #2
    @Test
    @DirtiesContext
    void testReadClientReturnNoClientExist() {

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("client_access/clients/client10")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // CR_U_D for Client #1
    @Test
    @DirtiesContext
    void testUpdateClientReturnClient() {

        Client clientInit = new Client("client6", "password123");

        Client clientUpdated = new Client("client6", "newPassword456");

        // Arrange0
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

        // Act and Assert - Updating the client
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

        // checking that the client was updated by requesting the updated client and checking it against the expected attributes
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

    // CR_U_D for Client #2
    @Test
    @DirtiesContext
    void testUpdateClientReturnNoClientExist() {

        // Arrange
        Client clientUpdated = new Client("client1", "newPassword456");

        // Act and Assert - Updating the client
        given()
        .contentType("application/json")
        .body(clientUpdated)
        .when()
        .put("client_access/clients/client1")
        .then()
        .statusCode(HttpStatus.OK.value());
    }

    // CRU_D_ for Client #1
    @Test
    @DirtiesContext
    void testDeleteClientReturnClient() {

        Client clientInit = new Client("client11", "password123");

        // Arrange
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

        // Act and Assert - Updating the client
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

        // checking that the client was deleted by trying to request the deleted client
        given()
        .contentType("application/json")
        .when()
        .get("client_access/clients/client11")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // CRU_D_ for Client #2
    @Test
    @DirtiesContext
    void testDeleteClientReturnBadRequest(){
        // Arrange
        Client clientInit = new Client("client50", "password123");

        // Act and Assert - Updating the client
        given()
        .contentType("application/json")
        .body(clientInit)
        .when()
        .delete("client_access/clients/client50")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
    }
}

