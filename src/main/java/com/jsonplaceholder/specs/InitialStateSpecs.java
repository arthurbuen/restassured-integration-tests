package com.jsonplaceholder.specs;

import com.jsonplaceholder.config.ConfigurationManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class InitialStateSpecs {

    private InitialStateSpecs() {
    }

    public static RequestSpecification set() {
        var configuration = ConfigurationManager.getConfiguration();

        return new RequestSpecBuilder().
                setBaseUri(configuration.baseURI()).
                build();
    }
}
