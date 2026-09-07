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

public class PatchRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testPatchRequestStatusCode() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("status", "active");

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPatchRequestBodyFields() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("status", "active");

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertNotNull(response.jsonPath().get("data"));
        assertNotNull(response.jsonPath().get("headers"));
        assertNotNull(response.jsonPath().get("url"));
        assertNotNull(response.jsonPath().get("json"));

        assertEquals("active", response.jsonPath().get("json.status"));
    }

    @Test
    public void testPatchRequestAllFieldsWithHamcrest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("status", "active");

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.status", equalTo("active"))
                .body("$", hasKey("data"))
                .body("$", hasKey("headers"))
                .body("$", hasKey("url"))
                .body("$", hasKey("json"));
    }
}
