package AdminServiceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.bullish.assignment1v3.Assignment1v3Application;
import com.bullish.assignment1v3.controller.AdminController;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.service.AdminService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
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

    @Test
    void AddProductTest(){

        // Arrange
        List<Product> products = new ArrayList<>();

        products.add(new Product("Product1", 110f, 0.5f, 50));

        adminService.addProduct(new Product("Product1", 110f, 0.5f, 50));

        when(adminService.readAllProducts()).thenReturn(products);

        // Act and Assert
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(1))
                .body("[0].username", is("rootAdmin"));
    }

    @Test
    void AddProductReturnExceptionProductAlreadyExistsTest(){

        // Arrange
        String name = "Product1";
        Float price = 100f;
        Float discount = 0.1f;
        Integer totalAvailable = 10;

        Product product1 = new Product(name, price, discount, totalAvailable);
        adminService.addProduct(product1);

        //Assert
        adminService.addProduct(product1);
    }

    @Test
    void updateProductTest(){

        // Arrange
        List<Product> products = new ArrayList<>();

        products.add(new Product("Product1", 110f, 0.5f, 50));

        adminService.addProduct(new Product("Product1", 110f, 0.5f, 50));

        Product productUpdated = new Product(name, price, discount, totalAvailable);

        when(adminService.readAllProducts()).thenReturn(productUpdated);

        // Act
        adminService.updateProduct(name, productUpdated);

        Product retrievedProduct = adminService.readProduct(name).get();

        // Assert
            // Check that the root admin exists in the database
        assertThat(productUpdated.getName()).isEqualTo(retrievedProduct.getName());
        assertThat(productUpdated.getPrice()).isEqualTo(retrievedProduct.getPrice());
        assertThat(productUpdated.getDiscount()).isEqualTo(retrievedProduct.getDiscount());
        assertThat(productUpdated.getTotalAvailable()).isEqualTo(retrievedProduct.getTotalAvailable());
    }


}
