package AdminServiceTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;
import com.bullish.assignment1v3.repository.ProductRepository;
import com.bullish.assignment1v3.service.AdminService;

import jakarta.persistence.Column;

@SpringBootTest
public class AdminProductCRUDTest {
    

    @Autowired
    private AdminService adminService;

    @Autowired ProductRepository productRepository;

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
        
        Product retrievedAdmin = adminService.readProduct(name).get();


        // Assert
            // Check that the root admin exists in the database
        assertThat(product1.getName()).isEqualTo(retrievedAdmin.getName());
        assertThat(product1.getPrice()).isEqualTo(retrievedAdmin.getPrice());
    }
}
