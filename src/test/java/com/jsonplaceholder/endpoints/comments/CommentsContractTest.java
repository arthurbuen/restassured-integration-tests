package com.jsonplaceholder.endpoints.comments;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Comments;
import com.jsonplaceholder.data.model.Posts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.CONTRACT;
import static com.jsonplaceholder.data.factory.CommentsDataFactory.createRandomComment;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CommentsContractTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = ConfigurationManager.getConfiguration().endpointComments();
    }

    @Test
    @Tag(CONTRACT)
    @DisplayName("Should validate the restrictions schema for /comments GET method")
    public void getContract() {
        when()
                .get("/1")
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/comments_schema.json"));
    }

    @Test
    @Tag(CONTRACT)
    @DisplayName("Should validate the restrictions schema for /comments POST method")
    public void postContract() {

        Comments comment = createRandomComment();

        given()
                .contentType("application/json")
                .body(comment)
                .when()
                .post()
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/comments_schema.json"));
    }
}
