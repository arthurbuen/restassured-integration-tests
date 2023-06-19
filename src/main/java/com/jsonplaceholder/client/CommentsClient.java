package com.jsonplaceholder.client;

import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Comments;
import com.jsonplaceholder.specs.CommentsSpec;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

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
