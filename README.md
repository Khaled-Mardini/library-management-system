# Spring Boot Application README

## How to Run the Application using Maven

To run the application using Maven, use the following command:

```shell
mvn spring-boot:run
```

## How to Run the Application Docker Image

To run the application using a Docker image, first build the Dockerfile and then run the container:

```shell
docker build -t my-spring-app .
docker run -p 8080:8080 my-spring-app
```

## How to Interact with the API

You can interact with the API using Swagger UI. Access the Swagger URL at http://localhost:8080/swagger-ui.html after starting the application.

Or you can interact with the API using Postman, Access the postman collection in [docs/postman-collection.json](docs/postman-collection.json)

## How to Add Bearer JWT Token to Requests

To add a Bearer JWT token to the header of your requests, include the following header:

```
Authorization: Bearer <your_token_here>
```

Replace `<your_token_here>` with your JWT token.

## Adding Environment Variables

1. Create an `application-{yourprofile}.yaml` file for your profile-specific configurations.
2. Add the file to `.gitignore` to exclude it from version control.
3. Add the following secrets to your `application-{yourprofile}.yaml` file:

```yaml
application:
  security:
    jwt:
      secret-key: <your_jwt_secret_key>
      activation-key:
        duration:
          min: <activation_key_duration>
spring:
  datasource:
    url: <database_url>
    driver-class-name: <database_driver>
    username: <database_username>
    password: <database_password>
mail:
  username: <mail_username>
  password: <mail_password>
```

Replace placeholders like `<your_jwt_secret_key>`, `<activation_key_duration>`, `<database_url>`, `<database_driver>`, `<database_username>`, `<database_password>`, `<mail_username>`, and `<mail_password>` with your actual values.

Remember to keep sensitive information like passwords and secret keys confidential.

## Running Redis

Before running the application, ensure that Redis is running on `localhost:6371`. You can start Redis using the following command:

```shell
redis-server --port 6371
```

## Running Tests with Maven

To run tests in your Spring Boot application using Maven, use the following command:

```shell
mvn test
```

This command will execute all the unit tests in your project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details.
