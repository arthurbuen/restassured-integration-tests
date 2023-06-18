package com.jsonplaceholder.posts;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.data.model.Posts;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static com.jsonplaceholder.data.factory.PostsDataFactory.getInvalidPost;
import static com.jsonplaceholder.data.factory.PostsDataFactory.getRandomPost;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostsFunctionalTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = "/posts";
    }

    @Test
    @DisplayName("Should query a valid post")
    void post() {

        Posts post = getRandomPost();

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
    @DisplayName("Should query a invalid post")
    void postNotFound() {

        when()
            .get("/" + getInvalidPost())
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND)
            .body(equalTo("{}"));
    }
}
