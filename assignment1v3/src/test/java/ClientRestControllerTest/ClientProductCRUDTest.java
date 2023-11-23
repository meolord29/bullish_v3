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
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.repository.ProductRepository;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class ClientProductCRUDTest {

    @Autowired
    private ClientController clientController;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(clientController);
    }

    // Test Cases for Product CRUD Operations

    // Test reading a specific product
    @Test
    @DirtiesContext
    void testReadProductReturnProduct() {
        // Arrange
        Product product = new Product("product100", 100d, 0.1d, 2);
        productRepository.save(product);

        // Act and Assert
        given()
            .contentType("application/json")
        .when()
            .get("client_access/products/product100")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(4))
            .body("name", equalTo("product100"))
            .body("price", equalTo(100f))
            .body("discount", equalTo(0.1f))
            .body("total", equalTo(2));
    }

    // Test reading all products
    @Test
    @DirtiesContext
    void testReadProductsReturnProducts() {
        // Arrange
        Product product2 = new Product("product199", 100d, 0.1d, 2);
        productRepository.save(product2);

        // Act and Assert
        // Retrieve a list of products (assuming your endpoint returns a list)
        given()
            .contentType("application/json")
        .when()
            .get("client_access/products/product199")
        .then()
            .statusCode(HttpStatus.OK.value());

        // Assert that the returned list contains both product1 and product2
        given()
            .contentType("application/json")
        .when()
            .get("client_access/products")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(Arrays.asList(1, 2, 3, 4)))
            .body("name", equalTo(Arrays.asList("product1", "product7", "product8", "product199")))
            .body("price", equalTo(Arrays.asList(100.0f, 100.0f, 500.0f, 100.0f)))
            .body("discount", equalTo(Arrays.asList(0.1f, 0.1f, 0.1f, 0.1f)))
            .body("total", equalTo(Arrays.asList(10, 10, 10, 2)));
    }

    // Test reading a non-existing product
    @Test
    @DirtiesContext
    void testReadProductReturnNoProductExist() {
        // Act and Assert
        given()
            .contentType("application/json")
        .when()
            .get("client_access/products/product40")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
}