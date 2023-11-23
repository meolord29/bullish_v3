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
import com.bullish.assignment1v3.repository.ProductRepository;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class AdminProductCRUDTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(adminController);
    }

    // _C_RUD for product #1
    @Test
    @DirtiesContext
    void testAddProductReturnProduct(){
        //Arrange
        Product product = new Product("product199", 100d, 0.1d, 2);

		given()
		.contentType("application/json")
		.body(product)
		.when().post("admin_access/products/product199").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(4))
        .body("name", equalTo("product199"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("total", equalTo(2));

    }

    // _C_RUD for product #2
    @Test
    @DirtiesContext
    void testAddProductReturnProductAlreadyExist(){

        // Arrange
        Product product = new Product("product199", 100d, 0.1d, 2);
    
        given()
		.contentType("application/json")
		.body(product)
		.when().post("admin_access/products/product199").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(4))
        .body("name", equalTo("product199"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("total", equalTo(2));

        // Act and Assert
        given()
            .contentType("application/json")
            .body(product)
        .when()
            .post("admin_access/products/product199")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    // C_R_UD for product #1
    @Test
    @DirtiesContext
    void testReadProductReturnProduct(){
        // Arrange
        Product product = new Product("product199", 100d, 0.1d, 2);

        given()
		.contentType("application/json")
		.body(product)
		.when().post("admin_access/products/product199")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(4))
        .body("name", equalTo("product199"))
        .body("price", equalTo(100f))
        .body("discount", equalTo(0.1f))
        .body("total", equalTo(2));


        // Act and Assert
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

    }

    @Test
    @DirtiesContext
    void testAdd2ProductsReturnProductList(){
        // Arrange
        
        // Act and Assert
        // Retrieve a list of products (assuming your endpoint returns a list)
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products")
        .then()
        .statusCode(HttpStatus.OK.value())
        .extract()
        .jsonPath()
        .getList(".", Product.class);

        // Assert that the returned list contains both product1 and product2
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(Arrays.asList(1, 2, 3)))
        .body("name", equalTo(Arrays.asList("product1", "product7", "product8")))
        .body("price", equalTo(Arrays.asList(100.0f, 100.0f, 500.0f)))
        .body("discount", equalTo(Arrays.asList(0.1f, 0.1f, 0.1f)))
        .body("total", equalTo(Arrays.asList(10, 10, 10)));

    }

    // C_R_UD for product #2
    @Test
    @DirtiesContext
    void testReadProductReturnNoProductExist(){

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products/product199")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CR_U_D for product #1
    @Test
    @DirtiesContext
    void testUpdateProductReturnProduct(){

        Product productInit = new Product("product199", 100d, 0.1d, 2);

        Product productUpdated = new Product("product199", 130d, 0.5d, 100);

        // Arrange
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

        // checking that the product was updated by requesting the updated protect and checking it against the expected attributes
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

    // CR_U_D for product #2
    @Test
    @DirtiesContext
    void testUpdateProductReturnNoProductExist(){

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

    // CRU_D_ for product #1
    @Test
    @DirtiesContext
    void testDeleteProductReturnProduct(){

        Product productInit = new Product("product199", 100d, 0.1d, 2);

        // Arrange
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

        // checking that the product was updated by requesting the updated protect and checking it against the expected attributes
        given()
        .contentType("application/json")
        .when()
        .get("admin_access/products/product199")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CRU_D_ for product #2
    @Test
    @DirtiesContext
    void testDeleteProductReturnNoProductExist(){

        // Arrange
        Product productInit = new Product("product199", 100d, 0.1d, 2);

        // Act and Assert - Updating the product
        given()
		.contentType("application/json")
		.body(productInit)
		.when()
        .delete("admin_access/products/product199")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }
}
