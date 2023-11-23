package AdminRestControllerTest;

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
import com.bullish.assignment1v3.controller.AdminController;
import com.bullish.assignment1v3.model.users.Admin;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(classes = Assignment1v3Application.class)
@AutoConfigureMockMvc
@DirtiesContext
public class AdminAdminCRUDTest {
    
    @Autowired
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(adminController);
    }

    // CRUD Operations for Admin #1

    // Create Admin and return Admin
    @Test
    @DirtiesContext
    void testAddAdminReturnAdmin() {
        // Arrange
        Admin admin = new Admin("admin1", "password123");

        // Act and Assert
        given()
            .contentType("application/json")
            .body(admin)
        .when()
            .post("admin_access/admins/admin")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));
    }

    // Create Admin that already exists, should return BadRequest
    @Test
    @DirtiesContext
    void testAddAdminReturnAdminAlreadyExist() {
        // Arrange
        Admin admin = new Admin("admin1", "password123");

        // Act and Assert
        given()
            .contentType("application/json")
            .body(admin)
        .when()
            .post("admin_access/admins/admin")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));

        given()
            .contentType("application/json")
            .body(admin)
        .when()
            .post("admin_access/admins/admin")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // Read Admin and return Admins
    @Test
    @DirtiesContext
    void testAddAdminsReturnAdmins() {
        // Arrange
        Admin admin = new Admin("admin1", "password123");

        // Create Admin
        given()
            .contentType("application/json")
            .body(admin)
        .when()
            .post("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));

        // Assert that the returned list contains both RootAdmin and admin1
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/admins")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(Arrays.asList(1, 2)))
            .body("username", equalTo(Arrays.asList("RootAdmin", "admin1")))
            .body("password", equalTo(Arrays.asList("password", "password123")));
    }

    // Read Admin and return Admin
    @Test
    @DirtiesContext
    void testReadAdminReturnAdmin() {
        // Arrange
        Admin admin = new Admin("admin1", "password123");

        // Create Admin
        given()
            .contentType("application/json")
            .body(admin)
        .when()
            .post("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));

        // Act and Assert
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));
    }

    // Read Admin that doesn't exist, should return NotFound
    @Test
    @DirtiesContext
    void testReadAdminReturnNoAdminExist() {
        // Act and Assert
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Update Admin and return Admin
    @Test
    @DirtiesContext
    void testUpdateAdminReturnAdmin() {
        // Arrange
        Admin adminInit = new Admin("admin1", "password123");
        Admin adminUpdated = new Admin("admin1", "newPassword456");

        // Create Admin
        given()
            .contentType("application/json")
            .body(adminInit)
        .when()
            .post("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));

        // Read Admin and Assert
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));

        // Act and Assert - Updating the admin
        given()
            .contentType("application/json")
            .body(adminUpdated)
        .when()
            .put("admin_access/admins/admin")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("newPassword456"));

        // Check that the admin was updated
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("newPassword456"));
    }

    // Update Admin that doesn't exist, should return NotFound
    @Test
    @DirtiesContext
    void testUpdateAdminReturnNoAdminExist() {
        // Arrange
        Admin adminUpdated = new Admin("admin1", "newPassword456");

        // Act and Assert - Updating the admin
        given()
            .contentType("application/json")
            .body(adminUpdated)
        .when()
            .put("admin_access/admins/admin")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Delete Admin and return Admin
    @Test
    @DirtiesContext
    void testDeleteAdminReturnAdmin() {
        // Arrange
        Admin adminInit = new Admin("admin1", "password123");

        // Create Admin
        given()
            .contentType("application/json")
            .body(adminInit)
        .when()
            .post("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));

        // Read Admin and Assert
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));

        // Act and Assert - Deleting the admin
        given()
            .contentType("application/json")
            .body(adminInit)
        .when()
            .delete("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(2))
            .body("username", equalTo("admin1"))
            .body("password", equalTo("password123"));

        // Check that the admin was deleted
        given()
            .contentType("application/json")
        .when()
            .get("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    // Delete Admin that doesn't exist, should return BadRequest
    @Test
    @DirtiesContext
    void testDeleteAdminReturnBadRequest() {
        // Arrange
        Admin adminInit = new Admin("admin1", "password123");

        // Act and Assert - Deleting the admin
        given()
            .contentType("application/json")
            .body(adminInit)
        .when()
            .delete("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // Delete RootAdmin, should return BadRequest
    @Test
    @DirtiesContext
    void testDeleteRootAdminReturnBadRequest() {
        // Arrange
        Admin adminInit = new Admin("RootAdmin", "password");

        // Act and Assert - Deleting the admin
        given()
            .contentType("application/json")
            .body(adminInit)
        .when()
            .delete("admin_access/admins/admin1")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}

