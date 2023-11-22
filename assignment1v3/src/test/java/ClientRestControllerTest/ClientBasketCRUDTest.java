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
import com.bullish.assignment1v3.repository.BasketRepository;
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

    @Autowired
    private BasketRepository basketRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(clientController);
    }

    // _C_RUD for Admin #1
    @Test
    @DirtiesContext
    void testAddBasketReturnBasket(){
        // Arrange
        Basket basket1 = new Basket("client1", "product3", 20);

        given()
        .contentType("application/json")
        .body(basket1)  // Assuming addToBasket expects an object with 'client' and 'product' properties
        .when()
        .post("client_access/basket")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(3))
        .body("username", equalTo("client1"))
        .body("productName", equalTo("product3"))
        .body("total", equalTo(20));  // Assuming the price is an integer, adjust it based on your actual implementation
    }

    // _C_RUD for Admin #2
    @Test
    @DirtiesContext
    void testAddBasketReturnUpdatedBasket(){

        // Arrange
        Basket basket1 = new Basket("client1", "product1", 20);

        // Act and Assert
        given()
        .contentType("application/json")
        .body(basket1)  // Assuming addToBasket expects an object with 'client' and 'product' properties
        .when()
        .post("client_access/basket")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("username", equalTo("client1"))
        .body("productName", equalTo("product1"))
        .body("total", equalTo(30)); 
    }

    // C_R_UD for Admin #1
    @Test
    @DirtiesContext
    void testReadBasketReturnBaskets(){

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

    

    // C_R_UD for Admin #2
    @Test
    @DirtiesContext
    void testReadBasketReturnNoBasketExist(){

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("client_access/basket/client2/all")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }


    // CRU_D_ for Admin #1
    @Test
    @DirtiesContext
    void testDeletePartialBasketReturnBasket(){

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

        //Assert
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


    // CRU_D_ for Admin #2
    @Test
    @DirtiesContext
    void testDeleteAllBasketReturnBasket(){
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

        //Assert
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

    @Test
    @DirtiesContext
    void testDeleteBasketReturnBadRequest(){
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

