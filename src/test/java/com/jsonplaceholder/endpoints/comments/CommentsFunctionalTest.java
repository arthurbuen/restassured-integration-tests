package com.jsonplaceholder.endpoints.comments;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Comments;
import com.jsonplaceholder.data.model.Posts;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.FUNCTIONAL;
import static com.jsonplaceholder.data.factory.CommentsDataFactory.getValidComment;
import static com.jsonplaceholder.data.factory.PostsDataFactory.getValidPost;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommentsFunctionalTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = ConfigurationManager.getConfiguration().endpointComments();
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should query a valid comment")
    void getComment() {

        Comments comment = getValidComment();

        Comments responseComment =
                when()
                        .get("/" +  + comment.getId())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .body().as(Comments.class);

        assertThat(responseComment, equalTo(comment));
    }
}
