package AdminRestControllerTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import com.bullish.assignment1v3.Assignment1v3Application;
import com.bullish.assignment1v3.controller.AdminController;
import com.bullish.assignment1v3.model.store.Product;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class AdminProductCRUDTest {

    @Autowired
    private AdminController adminController;


    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(adminController);
    }

    // CRUD Operations for Products

    // Create Product and return Product
    @Test
    @DirtiesContext
    void testAddProductReturnProduct() {
        // Arrange
        Product product = new Product("product199", 100d, 0.1d, 2);

        // Act and Assert
        given()
            .contentType("application/json")
            .body(product)
        .when()
            .post("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(4))
            .body("name", equalTo("product199"))
            .body("price", equalTo(100f))
            .body("discount", equalTo(0.1f))
            .body("total", equalTo(2));
    }

    // Read Product and return No Product Exists
    @Test
    @DirtiesContext
    void testReadProductReturnNoProductExist() {
        // Act and Assert
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Update Product and return Product
    @Test
    @DirtiesContext
    void testUpdateProductReturnProduct() {
        // Arrange
        Product productInit = new Product("product199", 100d, 0.1d, 2);
        Product productUpdated = new Product("product199", 130d, 0.5d, 100);

        given()
            .contentType("application/json")
            .body(productInit)
        .when()
            .post("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(4))
            .body("name", equalTo("product199"))
            .body("price", equalTo(100f))
            .body("discount", equalTo(0.1f))
            .body("total", equalTo(2));

        given()
            .contentType("application/json")
        .when()
            .get("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(4))
            .body("name", equalTo("product199"))
            .body("price", equalTo(100f))
            .body("discount", equalTo(0.1f))
            .body("total", equalTo(2));

        // Act and Assert - Updating the product
        given()
            .contentType("application/json")
            .body(productUpdated)
        .when()
            .put("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(4))
            .body("name", equalTo("product199"))
            .body("price", equalTo(130f))
            .body("discount", equalTo(0.5f))
            .body("total", equalTo(100));

        // checking that the product was updated by requesting the updated product and checking it against the expected attributes
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(4))
            .body("name", equalTo("product199"))
            .body("price", equalTo(130f))
            .body("discount", equalTo(0.5f))
            .body("total", equalTo(100));
    }

    // Update Product and return No Product Exists
    @Test
    @DirtiesContext
    void testUpdateProductReturnNoProductExist() {
        // Arrange
        Product productUpdated = new Product("product199", 130d, 0.5d, 100);

        // Act and Assert - Updating the product
        given()
            .contentType("application/json")
            .body(productUpdated)
        .when()
            .put("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Delete Product and return Product
    @Test
    @DirtiesContext
    void testDeleteProductReturnProduct() {
        // Arrange
        Product productInit = new Product("product199", 100d, 0.1d, 2);

        given()
            .contentType("application/json")
            .body(productInit)
        .when()
            .post("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(4))
            .body("name", equalTo("product199"))
            .body("price", equalTo(100f))
            .body("discount", equalTo(0.1f))
            .body("total", equalTo(2));

        given()
            .contentType("application/json")
        .when()
            .get("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(4))
            .body("name", equalTo("product199"))
            .body("price", equalTo(100f))
            .body("discount", equalTo(0.1f))
            .body("total", equalTo(2));

        // Act and Assert - Deleting the product
        given()
            .contentType("application/json")
            .body(productInit)
        .when()
            .delete("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(4))
            .body("name", equalTo("product199"))
            .body("price", equalTo(100f))
            .body("discount", equalTo(0.1f))
            .body("total", equalTo(2));

        // checking that the product was deleted by trying to request the deleted product
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Delete Product and return No Product Exists
    @Test
    @DirtiesContext
    void testDeleteProductReturnNoProductExist() {
        // Arrange
        Product productInit = new Product("product199", 100d, 0.1d, 2);

        // Act and Assert - Deleting the product
        given()
            .contentType("application/json")
            .body(productInit)
        .when()
            .delete("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
