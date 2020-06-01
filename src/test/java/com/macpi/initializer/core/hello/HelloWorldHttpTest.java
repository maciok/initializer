package com.macpi.initializer.core.hello;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.macpi.initializer.tags.Integration;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@Integration
@SpringBootTest(webEnvironment = RANDOM_PORT)
class HelloWorldHttpTest {

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    void checkHelloWorld() {
        var someName = "John";

        given()
            .accept(APPLICATION_JSON_VALUE)
        .when()
            .get("/hello/{name}", someName)
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .body("message", containsString(someName));
    }
}
