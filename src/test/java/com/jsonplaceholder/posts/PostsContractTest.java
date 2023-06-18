package com.jsonplaceholder.posts;

import com.jsonplaceholder.BaseAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostsContractTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = "/posts";
    }

    @Test
    @DisplayName("Should validate the restrictions schema for /posts GET method")
    public void contract() {
        when()
            .get("/1")
        .then()
            .body(matchesJsonSchemaInClasspath("schemas/posts_schema.json"));
    }
}
