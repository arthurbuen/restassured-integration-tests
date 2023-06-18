package com.jsonplaceholder.general;

import com.jsonplaceholder.BaseAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;

public class PostsHealthCheckTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = "/health";
    }

    @Test
    @Disabled("Method not implemented")
    @DisplayName("Should be able to hit the health endpoint")
    void healthCheck() {
        when()
            .get()
        .then()
            .statusCode(SC_OK)
            .body("status", is("UP"));
    }
}
