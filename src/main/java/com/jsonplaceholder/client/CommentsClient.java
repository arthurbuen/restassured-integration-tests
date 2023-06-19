package com.jsonplaceholder.client;

import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Comments;
import com.jsonplaceholder.data.model.Posts;
import com.jsonplaceholder.specs.CommentsSpec;
import com.jsonplaceholder.specs.PostsSpec;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

import static com.jsonplaceholder.data.factory.CommentsDataFactory.createNewComment;
import static com.jsonplaceholder.data.factory.PostsDataFactory.createRandomPost;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.config;
import static io.restassured.config.RestAssuredConfig.newConfig;

public class CommentsClient {

    public Response submitSuccessfulComment(Comments comment){

        basePath = ConfigurationManager.getConfiguration().endpointComments();

        return
                given()
                        .spec(CommentsSpec.postValidComment(comment))
                        .when()
                        .post()
                        .then()
                        .spec(CommentsSpec.createdComment())
                        .extract()
                        .response();
    }
}
