package AdminRestControllerTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import com.bullish.assignment1v3.Assignment1v3Application;
import com.bullish.assignment1v3.controller.AdminController;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.ClientRepository;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
public class AdminClientCRUDTest {
    
    @Autowired
    private AdminController adminController;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(adminController);
    }

    // CRUD Operations for Clients

    // Create Client and return Clients
    @Test
    @DirtiesContext
    void testAddClientReturnClients() {
        // Arrange
        Client client2 = new Client("client2", "password123");
        clientRepository.save(client2);

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/clients")
        .then()
        .statusCode(HttpStatus.OK.value())
        .extract()
        .jsonPath()
        .getList(".", Client.class);

        // Validate the returned list contains the added client
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/clients")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(Arrays.asList(1, 2, 3)))
        .body("username", equalTo(Arrays.asList("client1", "client8", "client2")))
        .body("password", equalTo(Arrays.asList("password123", "password123", "password123")));
    }

}
