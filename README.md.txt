# Library Management System

## Overview

The Library Management System is a Spring Boot application designed to manage books within a library. This system allows users to perform CRUD (Create, Read, Update, Delete) operations on book records and provides basic authentication for security.

## Features

- Manage book records (Add, View, Update, Delete)
- Basic HTTP Authentication
- API endpoints for book management
- In-memory user authentication

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Security
- MySQL
- Maven

## Installation

### Prerequisites

- Java 17 or later
- Maven
- MySQL Database
- MySQL Connector/J

### Steps to Install

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/username/library-management-system.git
   cd library-management-system
Configure MySQL Database:


2.Create a new MySQL database for the application.

    Update the src/main/resources/application.yml file with your MySQL database credentials:

    yaml

    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/library_db
        username: your_mysql_username
        password: your_mysql_password
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true

Add MySQL Connector/J Dependency:

Ensure the MySQL Connector/J dependency is included in your pom.xml:

xml

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>

Build the Project:

bash

./mvnw clean install

Run the Application:

bash

    ./mvnw spring-boot:run

    The application will start and listen on http://localhost:8080.



API Endpoints
Books

    Get All Books

    http

GET /api/books

Returns a list of all books in the library.

Get Book by ID

http

GET /api/books/{id}

Retrieves a book by its ID.

Add a New Book

http

POST /api/books

Request Body:

json

{
  "title": "Book Title",
  "author": "Book Author",
  "publicationYear": 2024,
  "isbn": "ISBN1234567890"
}

Update a Book

http

PUT /api/books/{id}

Request Body:

json

{
  "title": "Updated Title",
  "author": "Updated Author",
  "publicationYear": 2024,
  "isbn": "UpdatedISBN1234567890"
}

Delete a Book

http

    DELETE /api/books/{id}

    Deletes a book by its ID.

Authentication

The application uses Basic HTTP Authentication.

    Username: user
    Password: password

Running Tests

To run tests, use the following command:

bash

./mvnw test

Contributing

Contributions are welcome! Please follow these steps to contribute:

    Fork the repository.
    Create a new branch (git checkout -b feature/YourFeature).
    Commit your changes (git commit -am 'Add new feature').
    Push the branch (git push origin feature/YourFeature).
    Open a pull request.


For any inquiries or support, please contact:

    Name: Alaa Salahelden
    Email: alaasalahelden06@gmail.com

markdown



