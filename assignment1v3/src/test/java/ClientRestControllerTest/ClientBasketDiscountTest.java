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
import com.bullish.assignment1v3.controller.ClientController;
import com.bullish.assignment1v3.model.store.Basket;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.BasketRepository;
import com.bullish.assignment1v3.repository.ClientRepository;
import com.bullish.assignment1v3.repository.ProductRepository;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class ClientBasketDiscountTest {
    
    @Autowired
    private ClientController clientController;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(clientController);
    }

    @Test
    @DirtiesContext
    void testDiscountForEverySecondProductPurchased_ThirtyPercentOffSecondProductOnce() {
        // Arrange

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("client_access/basket/client8/priceTotal")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("username", equalTo("client8"))
        .body("priceTotal", equalTo(855f));
    }

    @Test
    @DirtiesContext
    void testDiscountForHavingThreeDifferentProducts_TenPercentOffAllMostPriciest() {
        // Arrange
        
        productRepository.save(new Product("product5", 100d, 0.0d, 10));
        productRepository.save(new Product("product6", 100d, 0.0d, 10));
        productRepository.save(new Product("product7", 100d, 0.0d, 10));
        productRepository.save(new Product("product8", 100d, 0.0d, 10));
        productRepository.save(new Product("product9", 100d, 0.0d, 10));

        clientRepository.save(new Client("client77", "password123"));
        
        basketRepository.save(new Basket("client77", "product5", 1));
        basketRepository.save(new Basket("client77", "product6", 1));
        basketRepository.save(new Basket("client77", "product7", 1));
        basketRepository.save(new Basket("client77", "product8", 1));
        basketRepository.save(new Basket("client77", "product9", 1));


        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("client_access/basket/client77/priceTotal")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("username", equalTo("client8"))
        .body("priceTotal", equalTo(450f));
    }
}
