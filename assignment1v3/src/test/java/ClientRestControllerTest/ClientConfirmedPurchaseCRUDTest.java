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
import com.bullish.assignment1v3.model.store.ConfirmedPurchase;
import com.bullish.assignment1v3.model.users.Client;
import com.bullish.assignment1v3.repository.ConfirmedPurchaseRepository;
import com.bullish.assignment1v3.repository.ProductRepository;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class ClientConfirmedPurchaseCRUDTest {

    @Autowired
    private ClientController clientController;

    @Autowired
    private ConfirmedPurchaseRepository confirmedPurchaseRepository;


    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(clientController);
    }

    @Test
    @DirtiesContext
    public void testReadConfirmedPurchase(){
        
        // Assert that the returned list contains both product1 and product2

        given()
        .contentType("application/json")
        .when()
        .get("client_access/confirmedPurchase/client44/all")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(Arrays.asList(1)))
        .body("username", equalTo(Arrays.asList("client44")))
        .body("productName", equalTo(Arrays.asList("product90" )))
        .body("total", equalTo(Arrays.asList(2)));
        }

    @Test
    @DirtiesContext
    public void addNewConfirmedPurchase(){
        
        // Assert that the returned list contains both product1 and product2

        ConfirmedPurchase newConfirmedPurchase = new ConfirmedPurchase("client44", "product8", 1);

        given()
        .contentType("application/json")
        .when()
        .get("client_access/confirmedPurchase/client44/all")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(Arrays.asList(1)))
        .body("username", equalTo(Arrays.asList("client44")))
        .body("productName", equalTo(Arrays.asList("product90" )))
        .body("total", equalTo(Arrays.asList(2)));

        given()
        .contentType("application/json")
        .body(newConfirmedPurchase)
        .when()
        .post("client_access/confirmedPurchase/client44")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(2))
        .body("username", equalTo("client44"))
        .body("productName", equalTo("product8" ))
        .body("total", equalTo(1));

        given()
        .contentType("application/json")
        .body(newConfirmedPurchase)
        .when()
        .post("client_access/confirmedPurchase/client44")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(2))
        .body("username", equalTo("client44"))
        .body("productName", equalTo("product8" ))
        .body("total", equalTo(1));
        
        given()
        .contentType("application/json")
        .when()
        .get("client_access/confirmedPurchase/client44/all")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(Arrays.asList(1, 2)))
        .body("username", equalTo(Arrays.asList("client44", "client44")))
        .body("productName", equalTo(Arrays.asList("product90", "product8" )))
        .body("total", equalTo(Arrays.asList(2, 1)));

    }


}






