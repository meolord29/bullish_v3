package ClientRestControllerTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bullish.assignment1v3.Assignment1v3Application;
import com.bullish.assignment1v3.controller.AdminController;
import com.bullish.assignment1v3.controller.ClientController;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;
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

    @Test
    @DirtiesContext
    void testReadProductReturnProduct(){
        // Arrange
        Product product = new Product("product1", 100f, 0.1f, 2);
        productRepository.save(product);
        
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

    @Test
    @DirtiesContext
    void testReadProductsReturnProducts(){
        // Arrange
        Product product1 = new Product("product1", 100f, 0.1f, 2);
        Product product2 = new Product("product2", 100f, 0.1f, 2);
        productRepository.save(product1);
        productRepository.save(product2);
        
        // Act and Assert
        // Retrieve a list of products (assuming your endpoint returns a list)
        given()
        .contentType("application/json")
        .when()
        .get("client_access/products")
        .then()
        .statusCode(HttpStatus.OK.value())
        .extract()
        .jsonPath()
        .getList(".", Product.class);

        // Assert that the returned list contains both product1 and product2
        given()
        .contentType("application/json")
        .when()
        .get("client_access/products")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(Arrays.asList(1, 2)))
        .body("name", equalTo(Arrays.asList("product1", "product2")))
        .body("price", equalTo(Arrays.asList(100f, 100f)))
        .body("discount", equalTo(Arrays.asList(0.1f, 0.1f)))
        .body("totalAvailable", equalTo(Arrays.asList(2, 2)));
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
