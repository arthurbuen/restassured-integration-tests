package com.jsonplaceholder.endpoints.posts;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.data.model.Posts;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.LOAD_TEST;
import static com.jsonplaceholder.data.factory.PostsDataFactory.getValidPost;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class SimpleLoadTest extends BaseAPI {

    @Test
    @Tag(LOAD_TEST)
    @Disabled("JSONPlaceHolder API does not support multiple requests")
    @DisplayName("Load test: Sending multiple requests to the API")
    void multipleRequestsLoadTest() {
        int numRequests = 100;
        long responseTimeout = 5000L;

        Posts post;

        for (int i = 0; i < numRequests; i++) {
             post = getValidPost();

            given()
                    .get("/" + post.getId())
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .time(lessThan(responseTimeout));
        }
    }
}