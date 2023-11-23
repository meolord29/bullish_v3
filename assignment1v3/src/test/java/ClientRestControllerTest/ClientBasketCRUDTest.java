package ClientRestControllerTest;

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
import com.bullish.assignment1v3.controller.ClientController;
import com.bullish.assignment1v3.model.store.Basket;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class ClientBasketCRUDTest {

    @Autowired
    private ClientController clientController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(clientController);
    }

    // CRUD Operations for Client Baskets

    // Create Basket and return Basket
    @Test
    @DirtiesContext
    void testAddBasketReturnBasket() {
        // Arrange
        Basket basket1 = new Basket("client1", "product3", 20);

        // Act and Assert
        given()
            .contentType("application/json")
            .body(basket1)
        .when()
            .post("client_access/basket")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(6))
            .body("username", equalTo("client1"))
            .body("productName", equalTo("product3"))
            .body("total", equalTo(20));
    }

    // Update Basket and return Updated Basket
    @Test
    @DirtiesContext
    void testAddBasketReturnUpdatedBasket() {
        // Arrange
        Basket basket1 = new Basket("client1", "product1", 20);

        // Act and Assert
        given()
            .contentType("application/json")
            .body(basket1)
        .when()
            .post("client_access/basket")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(1))
            .body("username", equalTo("client1"))
            .body("productName", equalTo("product1"))
            .body("total", equalTo(30));
    }

    // Read Baskets and return Baskets
    @Test
    @DirtiesContext
    void testReadBasketReturnBaskets() {
        // Assert that the returned list contains both product1 and product2
        given()
            .contentType("application/json")
        .when()
            .get("client_access/basket/client1/all")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(Arrays.asList(1, 2)))
            .body("username", equalTo(Arrays.asList("client1", "client1")))
            .body("productName", equalTo(Arrays.asList("product1", "product2")))
            .body("total", equalTo(Arrays.asList(10, 20)));
    }

    // Read Baskets and return No Basket Exists
    @Test
    @DirtiesContext
    void testReadBasketReturnNoBasketExist() {
        // Act and Assert
        given()
            .contentType("application/json")
        .when()
            .get("client_access/basket/client2/all")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Delete Partial Basket and return Basket
    @Test
    @DirtiesContext
    void testDeletePartialBasketReturnBasket() {
        // Arrange
        Basket basket = new Basket("client1", "product2", 10);

        // Act
        given()
            .contentType("application/json")
            .body(basket)
        .when()
            .delete("client_access/basket")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(2))
            .body("username", equalTo("client1"))
            .body("productName", equalTo("product2"))
            .body("total", equalTo(10));

        // Assert
        given()
            .contentType("application/json")
        .when()
            .get("client_access/basket/client1/all")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(Arrays.asList(1, 2)))
            .body("username", equalTo(Arrays.asList("client1", "client1")))
            .body("productName", equalTo(Arrays.asList("product1", "product2")))
            .body("total", equalTo(Arrays.asList(10, 10)));
    }

    // Delete All Baskets and return Basket
    @Test
    @DirtiesContext
    void testDeleteAllBasketReturnBasket() {
        // Arrange
        Basket basket = new Basket("client1", "product2", 20);

        // Act
        given()
            .contentType("application/json")
            .body(basket)
        .when()
            .delete("client_access/basket")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", equalTo(2))
            .body("username", equalTo("client1"))
            .body("productName", equalTo("product2"))
            .body("total", equalTo(20));

        // Assert
        given()
            .contentType("application/json")
        .when()
            .get("client_access/basket/client1/all")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(Arrays.asList(1)))
            .body("username", equalTo(Arrays.asList("client1")))
            .body("productName", equalTo(Arrays.asList("product1")))
            .body("total", equalTo(Arrays.asList(10)));
    }

    // Delete Basket and return Bad Request
    @Test
    @DirtiesContext
    void testDeleteBasketReturnBadRequest() {
        // Arrange
        Basket basket = new Basket("client1", "product2", 30);

        // Act and Assert
        given()
            .contentType("application/json")
            .body(basket)
        .when()
            .delete("client_access/basket")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}

