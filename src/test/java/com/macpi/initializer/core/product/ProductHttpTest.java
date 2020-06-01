package com.macpi.initializer.core.product;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

//@Integration @todo fixme?
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductHttpTest {
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    void findCorrectProduct() {
        final var articleId = 42;

        given()
            .accept(JSON)
        .when()
            .get("/products/{articleId}", articleId)
        .then()
            .statusCode(200)
            .body("id", equalTo("1896ce35-8213-4060-81a5-dc621456e51d"))
            .body("name", equalTo("spider-man"))
            .body("articleId", equalTo(42))
            .body("lastUpdated", equalTo("2020-06-01T01:00:00Z"));
    }
}
