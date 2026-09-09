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

public class PutRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testPutRequestStatusCode() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("name", "Updated User");

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPutRequestBodyFields() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("name", "Updated User");

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertNotNull(response.jsonPath().get("data"));
        assertNotNull(response.jsonPath().get("headers"));
        assertNotNull(response.jsonPath().get("url"));
        assertNotNull(response.jsonPath().get("json"));

        assertEquals(1, response.jsonPath().getInt("json.id"));
        assertEquals("Updated User", response.jsonPath().get("json.name"));
    }

    @Test
    public void testPutRequestAllFieldsWithHamcrest() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("name", "Updated User");

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.id", equalTo(1))
                .body("json.name", equalTo("Updated User"))
                .body("$", hasKey("data"))
                .body("$", hasKey("headers"))
                .body("$", hasKey("url"))
                .body("$", hasKey("json"));
    }
}
