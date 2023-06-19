package com.jsonplaceholder.specs;

import com.jsonplaceholder.data.model.Comments;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class CommentsSpec {

    public static RequestSpecification postValidComment(Comments comment) {

        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.set()).
                setContentType(ContentType.JSON).
                setBody(comment).
                build();
    }

    public static ResponseSpecification createdComment() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_CREATED).
                build();
    }
}
