package com.jsonplaceholder.endpoints.posts;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.data.model.Posts;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.FUNCTIONAL;
import static com.jsonplaceholder.data.factory.PostsDataFactory.createRandomPost;
import static io.restassured.RestAssured.given;

public class PostsStructureTest extends BaseAPI {

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should receive 404 error sending create post without parameters")
    void notFoundPost() {

        Posts post = createRandomPost();
        post.setTitle(null);
        post.setBody(null);
        post.setUserId(null);

        given()
            .pathParam("postId", post.getId())
        .when()
            .get("/{postId}")
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should receive 404 error sending post without parameters")
    void post() {

        Posts post = createRandomPost();
        post.setTitle(null);
        post.setBody(null);
        post.setUserId(null);

        given()
                .pathParam("postId", post.getId())
                .when()
                .get("/{postId}")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }


}
