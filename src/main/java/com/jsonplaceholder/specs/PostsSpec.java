package com.jsonplaceholder.specs;

import com.jsonplaceholder.data.model.Posts;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import static com.jsonplaceholder.data.factory.PostsDataFactory.createRandomPost;
import static io.restassured.RestAssured.basePath;

public class PostsSpec {

    public static RequestSpecification postValidPost() {


        Posts post = createRandomPost();

        return new RequestSpecBuilder().
                addRequestSpecification(InitialStateSpecs.set()).
                setContentType(ContentType.JSON).
                setBody(post).
                build();
    }

    public static ResponseSpecification createdPost() {
        return new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_CREATED).
                build();
    }
}
