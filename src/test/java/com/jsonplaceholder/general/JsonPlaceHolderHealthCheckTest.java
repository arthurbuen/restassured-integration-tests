package com.jsonplaceholder.general;

import com.jsonplaceholder.BaseAPI;
import org.junit.jupiter.api.*;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.HEALTH;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;

public class JsonPlaceHolderHealthCheckTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = "/health";
    }

    @Test
    @Tag(HEALTH)
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
