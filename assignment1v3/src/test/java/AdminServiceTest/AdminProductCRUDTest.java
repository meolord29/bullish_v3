package AdminServiceTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bullish.assignment1v3.Assignment1v3Application;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.service.AdminService;

@SpringBootTest(classes = Assignment1v3Application.class)
public class AdminProductCRUDTest {

    @Autowired
    private AdminService adminService;

    @Test
    void AddProductTest(){

        // Arrange
        String name = "Product1";
        Float price = 100f;
        Float discount = 0.1f;
        Integer totalAvailable = 10;

        Product product1 = new Product(name, price, discount, totalAvailable);
        adminService.addProduct(product1);

        // Act
        Product retrievedProduct = adminService.readProduct(name).get();

        // Assert
            // Check that the root admin exists in the database
        assertThat(product1.getName()).isEqualTo(retrievedProduct.getName());
        assertThat(product1.getPrice()).isEqualTo(retrievedProduct.getPrice());
        assertThat(product1.getDiscount()).isEqualTo(retrievedProduct.getDiscount());
        assertThat(product1.getTotalAvailable()).isEqualTo(retrievedProduct.getTotalAvailable());
    }

    @Test
    void updateProductTest(){

        // Arrange
        String name = "Product1";
        Float price = 110f;
        Float discount = 0.5f;
        Integer totalAvailable = 50;

        adminService.addProduct(new Product(name, price, discount, totalAvailable););

        Product productUpdated = new Product(name, price, discount, totalAvailable);

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
