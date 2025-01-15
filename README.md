# restful-web-services
Restful Web Services

This project is a Spring Boot application that integrates with the OpenLibrary API to fetch author details and their works. It also integrates with a MySQL database to store the retrieved data.

Features

Search for authors using the OpenLibrary API.
Fetch works of an author from the API.
Store the fetched data into a MySQL database.
Developed according to RESTful API principles.
Requirements

Java 17 or higher
Spring Boot 3.4.1
MySQL 8.0 or higher
Maven 3.8 or higher
Setup

Clone the Repository git clone https://github.com/<your-username>/<repository-name>.git cd restful-web-services

Set Up the MySQL Database Execute the following SQL commands to create the database: CREATE DATABASE authors_db; CREATE USER 'user'@'localhost' IDENTIFIED BY 'password'; GRANT ALL PRIVILEGES ON authors_db.* TO 'user'@'localhost';

Configure application.properties Edit the src/main/resources/application.properties file: spring.datasource.url=jdbc:mysql://localhost:3306/authors_db spring.datasource.username=user spring.datasource.password=password spring.jpa.hibernate.ddl-auto=update

Build and Run the Project mvn clean install mvn spring-boot:run

API Endpoints

Search for Authors Endpoint: /api/authors/search Method: GET Query Parameter: name Example Request: curl "http://localhost:8080/api/authors/search?name=tolkien"

Fetch Works of an Author Endpoint: /api/authors/{authorKey}/works Method: GET Path Parameter: authorKey Example Request: curl "http://localhost:8080/api/authors/OL26320A/works"

Project Structure

src

main
java
com.furkanadar.rest.webservices
controller
AuthorController.java
service
AuthorService.java
dto
WorkDTO.java
entity
Author.java
repository
AuthorRepository.java
WorkRepository.java
resources
application.properties
data.sql
test
Technologies Used

Java 17: Programming language.
Spring Boot: Application framework.
Hibernate: ORM tool.
MySQL: Database management system.
Maven: Project management tool.
OpenLibrary API: External API.
Contributing Feel free to submit a pull request to contribute to the project.

License This project is licensed under the MIT License. See the LICENSE file for details.

![mysql](https://github.com/user-attachments/assets/24f4f700-ba20-495e-9cd8-bb0d29302bae)

![postman](https://github.com/user-attachments/assets/c4e7a668-31f8-4ee0-a136-5756782b0d1b)

![postman2](https://github.com/user-attachments/assets/f2da4deb-0354-4f4d-a8f1-2e1f437b790f)

![image](https://github.com/user-attachments/assets/7d4f8be2-834c-42f8-9d1a-0326bab5b8b3)

