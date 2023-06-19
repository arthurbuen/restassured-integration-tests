package com.jsonplaceholder.client;

import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.specs.PostsSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
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
