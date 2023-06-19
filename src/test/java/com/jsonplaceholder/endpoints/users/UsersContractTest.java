package com.jsonplaceholder.endpoints.users;

import com.jsonplaceholder.BaseAPI;
import com.jsonplaceholder.config.ConfigurationManager;
import com.jsonplaceholder.data.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.jsonplaceholder.data.changeless.TestSuiteTags.CONTRACT;
import static com.jsonplaceholder.data.factory.UsersDataFactory.getValidUser;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UsersContractTest extends BaseAPI {

    @BeforeEach
    public void setup() {
        basePath = ConfigurationManager.getConfiguration().endpointUsers();
    }

    @Test
    @Tag(CONTRACT)
    @DisplayName("Should validate the restrictions schema for /users GET method")
    public void getUser() {
        when()
                .get("/" + getValidUser().getId())
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/users_schema.json"));
    }

    @Test
    @Tag(CONTRACT)
    @DisplayName("Should validate the restrictions schema for /users POST method")
    public void postUser() {

        Users user = getValidUser();

        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post()
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/users_schema.json"));
    }
}
