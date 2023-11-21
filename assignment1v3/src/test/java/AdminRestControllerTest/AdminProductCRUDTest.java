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

    // _C_RUD for product #1
    @Test
    @DirtiesContext
    void testAddProductReturnProduct(){
        //Arrange
        Product product = new Product("product1", 100f, 0.1f, 2);

		given()
		.contentType("application/json")
		.body(product)
		.when().post("admin_access/products/product").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

    }

    // _C_RUD for product #2
    @Test
    @DirtiesContext
    void testAddProductReturnProductAlreadyExist(){

        // Arrange
        Product product = new Product("product1", 100f, 0.1f, 2);
    
        given()
		.contentType("application/json")
		.body(product)
		.when().post("admin_access/products/product").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

        // Act and Assert
        given()
            .contentType("application/json")
            .body(product)
        .when()
            .post("admin_access/products/product")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    // C_R_UD for product #1
    @Test
    @DirtiesContext
    void testReadProductReturnProduct(){
        // Arrange
        Product product = new Product("product1", 100f, 0.1f, 2);

        given()
		.contentType("application/json")
		.body(product)
		.when().post("admin_access/products/product")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));


        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

    }

    // C_R_UD for product #2
    @Test
    @DirtiesContext
    void testReadProductReturnNoProductExist(){

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CR_U_D for product #1
    @Test
    @DirtiesContext
    void testUpdateProductReturnProduct(){

        Product productInit = new Product("product1", 100f, 0.1f, 2);

        Product productUpdated = new Product("product1", 130f, 0.5f, 100);

        // Arrange
        given()
		.contentType("application/json")
		.body(productInit)
		.when()
        .post("admin_access/products/product")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

        // Act and Assert - Updating the product
        given()
		.contentType("application/json")
		.body(productUpdated)
		.when()
        .put("admin_access/products/product")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(130f))
        .body("discount", equalTo(0.5f))
        .body("totalAvailable", equalTo(100));

        // checking that the product was updated by requesting the updated protect and checking it against the expected attributes
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(130f))
        .body("discount", equalTo(0.5f))
        .body("totalAvailable", equalTo(100));

    }

    // CR_U_D for product #2
    @Test
    @DirtiesContext
    void testUpdateProductReturnNoProductExist(){

        // Arrange
        Product productUpdated = new Product("product1", 130f, 0.5f, 100);

        // Act and Assert - Updating the product
        given()
		.contentType("application/json")
		.body(productUpdated)
		.when()
        .put("admin_access/products/product")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CRU_D_ for product #1
    @Test
    @DirtiesContext
    void testDeleteProductReturnProduct(){

        Product productInit = new Product("product1", 100f, 0.1f, 2);

        // Arrange
        given()
		.contentType("application/json")
		.body(productInit)
		.when()
        .post("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

        // Act and Assert - Updating the product
        given()
		.contentType("application/json")
		.body(productInit)
		.when()
        .delete("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

        // checking that the product was updated by requesting the updated protect and checking it against the expected attributes
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CRU_D_ for product #2
    @Test
    @DirtiesContext
    void testDeleteProductReturnNoProductExist(){

        // Arrange
        Product productInit = new Product("product1", 100f, 0.1f, 2);

        // Act and Assert - Updating the product
        given()
		.contentType("application/json")
		.body(productInit)
		.when()
        .delete("admin_access/products/product1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }
}
