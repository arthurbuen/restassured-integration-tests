package com.jsonplaceholder.e2e;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.client.CommentsClient;
import com.jsonplaceholder.client.PostsClient;
import com.jsonplaceholder.data.model.Posts;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.E2E;
import static com.jsonplaceholder.data.factory.CommentsDataFactory.createNewComment;
import static io.restassured.RestAssured.basePath;

class SocialMediaE2ETest {

    private final PostsClient postsClient = new PostsClient();
    private final CommentsClient commentsClient = new CommentsClient();
    private final Faker faker = new Faker();

    @Test
    @Tag(E2E)
    @DisplayName("Should add a comment to a new post")
    void postCommentToANewPost(){

        Response response = postsClient.submitSuccessfulPost();
        Posts post = response.as(Posts.class);

        commentsClient.submitSuccessfulComment(createNewComment(
                post.getId(),
                String.join(" ", faker.lorem().words(4)),
                "email@test.com",
                faker.lorem().paragraph()));

    }
}
