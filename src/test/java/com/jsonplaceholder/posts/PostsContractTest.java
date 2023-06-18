package com.jsonplaceholder.posts;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.data.model.Posts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static com.jsonplaceholder.data.factory.PostsDataFactory.getRandomPost;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostsContractTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = "/posts";
    }

    @Test
    @DisplayName("Should validate the restrictions schema for /posts GET method")
    public void getContract() {
        when()
            .get("/" + getRandomPost().getId())
        .then()
            .body(matchesJsonSchemaInClasspath("schemas/posts_schema.json"));
    }

    @Test
    @DisplayName("Should validate the restrictions schema for /posts POST method")
    public void postContract() {

        Posts post = getRandomPost();

        given()
            .contentType("application/json")
            .body(post)
        .when()
            .post()
        .then()
            .body(matchesJsonSchemaInClasspath("schemas/posts_schema.json"));
    }
}
