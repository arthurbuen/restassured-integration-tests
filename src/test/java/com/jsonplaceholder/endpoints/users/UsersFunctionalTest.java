package com.jsonplaceholder.endpoints.users;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Users;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.FUNCTIONAL;
import static com.jsonplaceholder.data.factory.UsersDataFactory.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UsersFunctionalTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = ConfigurationManager.getConfiguration().endpointUsers();
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should query a valid user")
    void getUser() {

        Users user = getValidUser();

        Users responseUser =
                when()
                        .get("/" + user.getId())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .body().as(Users.class);

        assertThat(responseUser, equalTo(user));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should query a invalid user")
    void getNotFoundUser() {

        when()
                .get("/" + getInvalidUser())
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo("{}"));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should create a valid user")
    void createUser() {

        Users user = createRandomUser();

        Users responseUser =
                given()
                        .contentType("application/json")
                        .body(user)
                        .when()
                        .post()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .body().as(Users.class);

        assertThat(responseUser, equalTo(user));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should edit a user")
    void editUser() {

        Users user = getValidUser();
        user.setName("edit test");

        Users responseUser =
                given()
                        .contentType("application/json")
                        .body(user)
                        .when()
                        .put("/" + user.getId())
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .body().as(Users.class);

        assertThat(responseUser, equalTo(user));
    }

    @Test
    @Tag(FUNCTIONAL)
    @DisplayName("Should delete a user")
    void deleteUser() {

        Users user = getValidUser();

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .delete("/" + user.getId())
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
