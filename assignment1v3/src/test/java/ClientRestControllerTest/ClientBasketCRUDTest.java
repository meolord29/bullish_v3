package ClientRestControllerTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import com.bullish.assignment1v3.Assignment1v3Application;
import com.bullish.assignment1v3.controller.ClientController;
import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.repository.ProductRepository;

import io.restassured.module.mockmvc.RestAssuredMockMvc;


@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class ClientBasketCRUDTest {

    @Autowired
    private ClientController clientController;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(clientController);
    }

    // _C_RUD for Admin #1
    @Test
    @DirtiesContext
    void testAddBasketReturnBasket(){
        // Arrange
        Product product1 = new Product("product1", 100f, 0.1f, 10);
        productRepository.save(product1);
        Client client1 = new Client("client1", "password123");
        clientRepository.save(client1);

        given()
        .contentType("application/json")
        .body(Map.of("client", client1, "product", product1))  // Assuming addToBasket expects an object with 'client' and 'product' properties
        .when()
        .post("client_access/basket")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100));  // Assuming the price is an integer, adjust it based on your actual implementation
    }

    // _C_RUD for Admin #2
    @Test
    @DirtiesContext
    void testAddAdminReturnAdminAlreadyExist(){

        // Arrange
        Admin admin = new Admin("admin1", "password123");

        given()
        .contentType("application/json")
        .body(admin)
        .when().post("admin_access/admins/admin").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Act and Assert
        given()
            .contentType("application/json")
            .body(admin)
        .when()
            .post("admin_access/admins/admin")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DirtiesContext
    void testAddAdminsReturnAdmins(){
        // Arrange
        Admin admin = new Admin("admin1", "password123");

        given()
        .contentType("application/json")
        .body(admin)
        .when()
        .post("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Assert that the returned list contains both product1 and product2
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/admins")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(Arrays.asList(1, 2)))
        .body("username", equalTo(Arrays.asList("RootAdmin", "admin1")))
        .body("password", equalTo(Arrays.asList("password", "password123")));

    }

    // C_R_UD for Admin #1
    @Test
    @DirtiesContext
    void testReadAdminReturnAdmin(){
        // Arrange
        Admin admin = new Admin("admin1", "password123");

        given()
        .contentType("application/json")
        .body(admin)
        .when()
        .post("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

    }

    // C_R_UD for Admin #2
    @Test
    @DirtiesContext
    void testReadAdminReturnNoAdminExist(){

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CR_U_D for Admin #1
    @Test
    @DirtiesContext
    void testUpdateAdminReturnAdmin(){

        Admin adminInit = new Admin("admin1", "password123");

        Admin adminUpdated = new Admin("admin1", "newPassword456");

        // Arrange
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .post("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        given()
        .contentType("application/json")
        .when()
        .get("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminUpdated)
        .when()
        .put("admin_access/admins/admin")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("newPassword456"));

        // checking that the admin was updated by requesting the updated admin and checking it against the expected attributes
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("newPassword456"));

    }

    // CR_U_D for Admin #2
    @Test
    @DirtiesContext
    void testUpdateAdminReturnNoAdminExist(){

        // Arrange
        Admin adminUpdated = new Admin("admin1", "newPassword456");

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminUpdated)
        .when()
        .put("admin_access/admins/admin")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CRU_D_ for Admin #1
    @Test
    @DirtiesContext
    void testDeleteAdminReturnAdmin(){

        Admin adminInit = new Admin("admin1", "password123");

        // Arrange
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .post("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        given()
        .contentType("application/json")
        .when()
        .get("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .delete("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(2))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // checking that the admin was deleted by trying to request the deleted admin
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CRU_D_ for Admin #2
    @Test
    @DirtiesContext
    void testDeleteAdminReturnBadRequest(){
        // Arrange
        Admin adminInit = new Admin("admin1", "password123");

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .delete("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DirtiesContext
    void testDeleteRootAdminReturnBadRequest(){
        // Arrange
        Admin adminInit = new Admin("RootAdmin", "password");

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .delete("admin_access/admins/admin1")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());

    }
}

