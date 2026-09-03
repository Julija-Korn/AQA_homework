package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testDeleteRequestStatusCode() {
        given()
                .queryParam("id", 123)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteRequestBodyFields() {
        Response response = given()
                .queryParam("id", 123)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertNotNull(response.jsonPath().get("data"));
        assertNotNull(response.jsonPath().get("headers"));
        assertNotNull(response.jsonPath().get("url"));

        assertEquals("123", response.jsonPath().get("args.id"));
    }

    @Test
    public void testDeleteRequestAllFieldsWithHamcrest() {
        given()
                .queryParam("id", 123)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("args.id", equalTo("123"))
                .body("$", hasKey("data"))
                .body("$", hasKey("headers"))
                .body("$", hasKey("url"));
    }
}
