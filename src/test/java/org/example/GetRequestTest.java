package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetRequestTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGetRequestStatusCode() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetRequestBodyFields() {
        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertNotNull(response.jsonPath().get("args"));
        assertNotNull(response.jsonPath().get("headers"));
        assertNotNull(response.jsonPath().get("url"));

        assertEquals("bar1", response.jsonPath().get("args.foo1"));
        assertEquals("bar2", response.jsonPath().get("args.foo2"));
    }

    @Test
    public void testGetRequestAllFieldsWithHamcrest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("$", hasKey("args"))
                .body("$", hasKey("headers"))
                .body("$", hasKey("url"));
    }
}
