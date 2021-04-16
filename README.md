# AppcentTodo

## Stack
* Java8
* Couchbase
* Spring Boot
* Maven
* Swagger
* Docker
* JUnit and Mockito

## Couchbase

Application is database dependent and configured to use couchbase, therefore couchbase is required to get full functionality. Application uses configurations below:
``` 
* Connection String : couchbase
* username : productUser
* password : productPassword
* BucketName : TodoApp
```
If configurations in code is not changed, then set database settings accordingly.

## How to build

It is a Spring Boot application, so calling ```mvn install``` in directory pom.xml exists is enough to build. Maven tool is required.

## Running test suites

Same as build, it is required to be in same directory with pom.xml. By calling ```mvn test```, test suite can be run.

## Running app

After building the app, a ```.jar``` file will be created in a target directory. To run the app, you need to go to ```/target``` directory  and run ```java -jar toDoApp-0.0.1-SNAPSHOT.jar ``` command. Couchbase server should be running before app, otherwise service will be unavailable.

## Swagger UI

When application is run, API documentation can be found in ```http://localhost:8081/swagger-ui.html``` and endpoints can be tested via this tool.


