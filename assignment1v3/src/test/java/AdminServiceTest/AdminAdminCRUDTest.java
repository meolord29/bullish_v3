package AdminServiceTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.bullish.assignment1v3.controller.AdminController;
import com.bullish.assignment1v3.model.store.Product;
import com.bullish.assignment1v3.model.users.Admin;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class AdminAdminCRUDTest {
    
    @Autowired
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(adminController);
    }

    // _C_RUD for Admin #1
    @Test
    void testAddAdminReturnAdmin(){
        // Arrange
        Admin admin = new Admin("admin1", "password123");

        given()
        .contentType("application/json")
        .body(admin)
        .when().post("/admins/admin").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

    }

    // _C_RUD for Admin #2
    @Test
    void testAddAdminReturnAdminAlreadyExist(){

        // Arrange
        Admin admin = new Admin("admin1", "password123");

        given()
        .contentType("application/json")
        .body(admin)
        .when().post("/admins/admin").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Act and Assert
        given()
            .contentType("application/json")
            .body(admin)
        .when()
            .post("/admins/admin")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    // C_R_UD for Admin #1
    @Test
    void testReadAdminReturnAdmin(){
        // Arrange
        Admin admin = new Admin("admin1", "password123");

        given()
        .contentType("application/json")
        .body(admin)
        .when().post("/admins/admin").then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

    }

    // C_R_UD for Admin #2
    @Test
    void testReadAdminReturnNoAdminExist(){

        // Act and Assert
        given()
        .contentType("application/json")
        .when()
        .get("/admins/admin1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());

    }

    // CR_U_D for Admin #1
    @Test
    void testUpdateAdminReturnAdmin(){

        Admin adminInit = new Admin("admin1", "password123");

        Admin adminUpdated = new Admin("admin1", "newPassword456");

        // Arrange
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .post("/admins/admin")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        given()
        .contentType("application/json")
        .when()
        .get("/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminUpdated)
        .when()
        .put("/admins/admin")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("newPassword456"));

        // checking that the admin was updated by requesting the updated admin and checking it against the expected attributes
        given()
        .contentType("application/json")
        .when()
        .get("/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("newPassword456"));

    }

    // CR_U_D for Admin #2
    @Test
    void testUpdateAdminReturnNoAdminExist(){

        // Arrange
        Admin adminUpdated = new Admin("admin1", "newPassword456");

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminUpdated)
        .when()
        .put("/admins/admin")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    // CRU_D_ for Admin #1
    @Test
    void testDeleteAdminReturnAdmin(){

        Admin adminInit = new Admin("admin1", "password123");

        // Arrange
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .post("/admins/admin1")
        .then()
        .statusCode(HttpStatus.CREATED.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        given()
        .contentType("application/json")
        .when()
        .get("/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .delete("/admins/admin1")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("id", is(1))
        .body("username", equalTo("admin1"))
        .body("password", equalTo("password123"));

        // checking that the admin was deleted by trying to request the deleted admin
        given()
        .contentType("application/json")
        .when()
        .get("/admins/admin1")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    // CRU_D_ for Admin #2
    @Test
    void testDeleteAdminReturnNoAdminExist(){
        // Arrange
        Admin adminInit = new Admin("admin1", "password123");

        // Act and Assert - Updating the admin
        given()
        .contentType("application/json")
        .body(adminInit)
        .when()
        .delete("/admins/admin1")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());

    }

}
