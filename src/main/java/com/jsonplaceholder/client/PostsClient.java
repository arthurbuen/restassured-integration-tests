package com.jsonplaceholder.client;

import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Posts;
import com.jsonplaceholder.specs.PostsSpec;
import io.restassured.response.Response;

import static com.jsonplaceholder.data.factory.PostsDataFactory.createRandomPost;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.config;

public class PostsClient {

    public Response submitSuccessfulPost(){

        basePath = ConfigurationManager.getConfiguration().endpointPosts();

        return
                given()
                        .spec(PostsSpec.postValidPost())
                        .when()
                        .post()
                        .then()
                        .spec(PostsSpec.createdPost())
                        .extract()
        .response();
    }
}
