package AdminServiceTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bullish.assignment1v3.Assignment1v3Application;
import com.bullish.assignment1v3.controller.AdminController;
import com.bullish.assignment1v3.controller.Exceptions.ProductAlreadyExistsException;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.service.AdminService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
public class AdminProductCRUDTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private AdminService adminService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(adminController);
    }

    // _C_RUD for product #1
    @Test
    void testAddProductReturnProduct(){
        //Arrange
        Product product = new Product("product1", 100f, 0.1f, 2);

		given()
		.contentType("application/json")
		.body(product)
		.when().post("/products/product").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

    }

    // _C_RUD for product #2
    @Test
    void testAddProductReturnProductAlreadyExist(){

        // Arrange
        Product product = new Product("product1", 100f, 0.1f, 2);
    
        given()
		.contentType("application/json")
		.body(product)
		.when().post("/products/product").then()
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
            .post("/products/product")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    // C_R_UD for product #1
    @Test
    void testReadProductReturnProduct(){
        // Arrange
        Product product = new Product("product1", 100f, 0.1f, 2);

        given()
		.contentType("application/json")
		.body(product)
		.when().post("/products/product").then()
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
        .get("/products/product1")
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
    void testReadProductReturnNoProductExist(){

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("/products/product1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CR_U_D for product #1
    @Test
    void testUpdateProductReturnProduct(){

        Product product = new Product("product1", 100f, 0.1f, 2);

        // Arrange
        given()
		.contentType("application/json")
		.body(product)
		.when().post("/products/product").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));

        given()
        .contentType("application/json")
        .when()
        .get("/products/product1")
        .then()
        .statusCode(HttpStatus.OK.value())
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
        .put("/products/product")
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
        .get("/products/product1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("name", equalTo("product1"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("totalAvailable", equalTo(2));


    }

    // CR_U_D for product #2
    @Test
    void testUpdateProductReturnNoProductExist(){

    }

    // CRU_D_ for product #1
    @Test
    void testDeleteProductReturnProduct(){

    }

    // CRU_D_ for product #2
    @Test
    void testDeleteProductReturnNoProductExist(){

    }
}
