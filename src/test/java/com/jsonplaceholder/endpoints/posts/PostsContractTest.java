package com.jsonplaceholder.endpoints.posts;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Posts;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.CONTRACT;
import static com.jsonplaceholder.data.factory.PostsDataFactory.getValidPost;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostsContractTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = ConfigurationManager.getConfiguration().endpointPosts();
    }

    @Test
    @Tag(CONTRACT)
    @DisplayName("Should validate the restrictions schema for /posts GET method")
    public void getContract() {
        when()
            .get("/" + getValidPost().getId())
        .then()
            .body(matchesJsonSchemaInClasspath("schemas/posts_schema.json"));
    }

    @Test
    @Tag(CONTRACT)
    @DisplayName("Should validate the restrictions schema for /posts POST method")
    public void postContract() {

        Posts post = getValidPost();

        given()
            .contentType("application/json")
            .body(post)
        .when()
            .post()
        .then()
            .body(matchesJsonSchemaInClasspath("schemas/posts_schema.json"));
    }
}
