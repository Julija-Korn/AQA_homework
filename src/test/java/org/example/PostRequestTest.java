package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class PostRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testPostRequestStatusCode() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Test User");
        requestBody.put("email", "test@example.com");

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPostRequestBodyFields() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Test User");
        requestBody.put("email", "test@example.com");

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertNotNull(response.jsonPath().get("data"));
        assertNotNull(response.jsonPath().get("headers"));
        assertNotNull(response.jsonPath().get("url"));
        assertNotNull(response.jsonPath().get("json"));

        assertEquals("Test User", response.jsonPath().get("json.name"));
        assertEquals("test@example.com", response.jsonPath().get("json.email"));
    }

    @Test
    public void testPostRequestAllFieldsWithHamcrest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Test User");
        requestBody.put("email", "test@example.com");

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("Test User"))
                .body("json.email", equalTo("test@example.com"))
                .body("$", hasKey("data"))
                .body("$", hasKey("headers"))
                .body("$", hasKey("url"))
                .body("$", hasKey("json"));
    }
}
