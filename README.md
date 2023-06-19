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
