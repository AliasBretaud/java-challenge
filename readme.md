### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.

### Changelog

1.	Architecture
	- Packages refactoring
2.	Fix bug, improvements
	- Decouple model / entities (mapping added via mapstruct)
	- Send created/updated objects on POST/PUT requests
	- Global exception treatment (Controller advise)
3.	Tests
	- Test data script (data.sql)
	- Test coverage report (Jacoco)
	- Unit & integration tests
4.	Documentation
	- Comments
	- Developer guide (doc folder)
	- Swagger documentation annotations on operations/models
5.	Security
	- Add API Key authentication
6.	Performance
	- Adding cache on service function retrieving employees data (spring cache)

#### My experience in Java

I have 5 years experience in Java and I started to use Spring Boot from 3 years

#### If you had more time, I would have

- Improve security by changing to OAuth2 authentication with client credential flow
- Implement HATEOAS
- Divide architecture into micro services
- Create docker container running the application
- Set up CI configuration (with GitHub actions)
- Create more search functions (with dynamic requests, JPA specifications...)
- Implement log configuration (levels, rolling...)
