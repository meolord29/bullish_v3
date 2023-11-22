package com.bullish.assignment1v3.initDatabaseTests;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.bullish.assignment1v3.controller.AdminController;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class checkRootAdminExistWhenInitDatabaseTests {

    @Autowired
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(adminController);
    }

    @Test
    void injectedAdminRetrievableFromAdminRepository(){

        given()
        .when()
            .get("admin_access/admins/RootAdmin")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", is(1))
            .body("username", is("RootAdmin"));
    }

}
