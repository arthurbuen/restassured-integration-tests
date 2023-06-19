package com.jsonplaceholder.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties"})
public interface Configuration extends Config {

    @Key("api.base.uri")
    String baseURI();

    @Key("api.endpoint.posts")
    String endpointPosts();

    @Key("api.endpoint.comments")
    String endpointComments();

    @Key("api.endpoint.users")
    String endpointUsers();

}
