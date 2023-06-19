package com.jsonplaceholder.endpoints.posts;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Posts;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.FUNCTIONAL;
import static com.jsonplaceholder.data.factory.PostsDataFactory.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostsFunctionalTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = ConfigurationManager.getConfiguration().endpointPosts();
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should query a valid post")
    void getPost() {

        Posts post = getValidPost();

        Posts responsePost =
        when()
            .get("/" + post.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body().as(Posts.class);

        assertThat(responsePost, equalTo(post));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should query a invalid post")
    void getNotFoundPost() {

        when()
            .get("/" + getInvalidPost())
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .body(equalTo("{}"));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should create a valid post")
    void createPost() {

        Posts post = createRandomPost();

        Posts responsePost =
                given()
                        .contentType("application/json")
                        .body(post)
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .body().as(Posts.class);

        assertThat(responsePost, equalTo(post));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should edit a post")
    void editPost() {

        Posts post = getValidPost();
        post.setTitle("edit test");

        Posts responsePost =
                given()
                        .contentType("application/json")
                        .body(post)
                        .when()
                        .put("/" + post.getId())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .body().as(Posts.class);

        assertThat(responsePost, equalTo(post));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should delete a post")
    void deletePost() {

        Posts post = getValidPost();

        given()
                        .contentType("application/json")
                        .body(post)
                        .when()
                        .delete("/" + post.getId())
                        .then()
                        .statusCode(HttpStatus.SC_OK);

    }
}