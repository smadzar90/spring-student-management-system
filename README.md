# Student Management System

## Description

The Student Management System is a simple web-based application designed to facilitate the management of educational institutions. Built using Spring Boot and PostgreSQL, 
it provides a reliable framework for handling various administrative tasks related to students in educational settings. Structured under the Spring MVC architecture, the system ensures seamless request processing and maintains a well-organized application structure. This project aims to streamline the operations of educational institutions by offering a user-friendly interface and efficient system.

<img src="/images/main_page.png" alt="main page" width="700px" height="400px">

## Technologies

* Spring Boot
* PostgreSQL
* Spring Data JPA
* Thymeleaf
* HTML
* Bootstrap

## Database Structure

<img src="/images/db_setup.png" alt="database setup" width="700px" height="400px">

## Installation and Setup

1. **Clone the repository**
   ```
   git clone https://github.com/smadzar90/spring-student-management-system.git
   ```

2. **Open the project in IDE**
        
3. **Configure Database**
    - Open application.properties and configure PostgreSQL settings

        ```
        spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
        spring.datasource.username=your_database_username
        spring.datasource.password=your_database_password
        ```

4. **Build and Run the application**
    - Build project
    - Run main class annotated with @SpringBootApplication
  
   or use Maven:

   ```
   mvn spring-boot:run
   ```
   
5. **Access the Application:**
    - Open a web browser
    - Access the application at: [http://localhost:8080](http://localhost:8080)


## Author
- Stipan Madzar



