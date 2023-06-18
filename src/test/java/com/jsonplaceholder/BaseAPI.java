package com.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.config;
import static io.restassured.config.RestAssuredConfig.newConfig;

public abstract class BaseAPI {

    @BeforeAll
    public static void beforeAllTests(){
        baseURI = "https://jsonplaceholder.typicode.com";

        config = newConfig().
                sslConfig(new SSLConfig().allowAllHostnames());

        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
