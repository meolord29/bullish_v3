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
import com.bullish.assignment1v3.controller.AdminController;
import com.bullish.assignment1v3.controller.ClientController;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class ClientProductCRUDTest {

    @Autowired
    private ClientController clientController;

    @Autowired
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(clientController);
        RestAssuredMockMvc.standaloneSetup(adminController);
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
		.when()
        .post("admin_access/products/product1")
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
        .get("client_access/products/product1")
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
        .get("client_access/products/product1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }
}
