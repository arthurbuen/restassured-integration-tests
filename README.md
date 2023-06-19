![main](https://github.com/arthurbuen/restassured-integration-tests/actions/workflows/ci.yml/badge.svg)

# Integration tests project

Integration testing project of the [API {JSON} PlaceHolder](https://jsonplaceholder.typicode.com/)  using RESTAssured as the framework and Java as the programming language.

## Required software
* Java JDK 11+
* Maven installed and in your classpath

## How to execute the tests

You can open each test class on `src\test\java` and execute all of them, but I recommend you run it by the
command line.
If you run `mvn test` all the tests will execute because it's the regular Maven lifecycle to run all the tests.

To run different suites based on the groups defined for each test you must inform the property `-Dgroups` and the group names.
The example below shows how to run the test for each pipeline stage:

| pipeline stage     | command                          |
|--------------------|----------------------------------|
| health check tests | `mvn test -Dgroups="health"`     |
| contract tests     | `mvn test -Dgroups="contract"`   |
| functional tests   | `mvn test -Dgroups="functional"` |
| e2e tests          | `mvn test -Dgroups="e2e"`        |

## About the Project Structure

### src/main/java

#### client
class that performs actions on the endpoints

#### config
The class `Configuration` is the connections between the property file `api.properties` located in `src/test/resources/`.

The `@Config.Sources` load the properties file and match the attributes with the `@Key`, so you automatically have the value.
You can see two sources.
The first one will get the property values from the system (as environment variables or from the command line) in the case you want to change it, for example, in a pipeline.
The second will load the `api.properties` file from the classpath.
```java
@Config.Sources({
    "system:properties",
    "classpath:api.properties"})
```

The environment variable is read on the `ConfiguratorManager`.
This class reduces the amount of code necessary to get any information on the properties file.

This strategy uses [Owner](https://matteobaccan.github.io/owner/) library

#### data

##### suite
It contains a class having the data related to the test groups.

##### factory
Test Data Factory classes using [java-faker](https://github.com/DiUS/java-faker) to generate fake data and [Lombok](https://projectlombok.org/) to
create the objects using the Builder pattern.

##### model
Model and Builder class to
[mapping objects thought serialization and deserialization](https://github.com/rest-assured/rest-assured/wiki/Usage#object-mapping)
in use with Rest-Assured.

#### specs
Request and Response specifications used by the clients and e2e tests.
The class `InitialStepsSpec` set the basePath, baseURI, and port for the custom specs.
The classes `PostsSpecs` and `CommentsSpecs` contains the implementation of request and response specifications.

### src/test/java

#### e2e
End-to-End test using both endpoints to simulate the user journey thought the API.

#### general
Health check test to assure the endpoint is available.

#### endpoints
Contract and Functional tests to the endpoints.

### src/test/resources
It has a `schemas` folder with the JSON Schemas to enable Contract Testing using Rest-Assured. Also, the properties file to easily configure the API URI.


## Libraries
* [RestAssured](http://rest-assured.io/) library to test REST APIs
* [JUnit 5](https://junit.org/junit5/) to support the test creation
* [Owner](https://matteobaccan.github.io/owner/) to manage the property files
* [java-faker](https://github.com/DiUS/java-faker) to generate fake data

## Patterns applied
* Test Data Factory
* Data Provider
* Builder
* Request and Response Specification
* Base Test

## Pipeline

This project uses [GitHub Actions](https://github.com/features/actions) to run the all the tests in a pipeline.
You can find it at https://github.com/arthurbuen/restassured-integration-tests/actions/workflows/ci.yml

## Future Improvements

* Increase test coverage for functional tests to cover additional status codes and possibilities.
* Expand E2E test coverage for both positive and negative scenarios, addressing business rules, functional and non-functional requirements.
* Separate the CI process in GitHub Actions into stages: Health Check > Contract Tests > Structure Tests > Functional Tests > E2E Tests.
* Integration with a test management tool, such as Jira or Azure DevOps.
* Integration with a reporting tool, such as Allre.
* Configuration management: Add an environment for running tests (dev, uat, etc.) and refactor the current configuration management logic if needed to meet the requirements.
* If possible, have the automation project in the same repository as the API being tested. This allows for testing at the component level in addition to integration tests, enabling deeper application testing by simulating scenarios such as database unavailability or interactions with third-party APIs.