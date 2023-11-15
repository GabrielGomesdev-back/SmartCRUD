### Technologies used:

This is a Rest Api that uses

   - [SpringBoot](https://spring.io/projects/spring-boot): This is a Java open-source framework used in order to build rest api's.

   - [Lombok](https://www.baeldung.com/intro-to-project-lombok): This is a Java Library that look for repeat less code in order to enhance the produtivity.(At this project are 
                    used for make the code more clean and less verbose)

   - [AssertJ](https://assertj.github.io/doc/): Library that used for make Asserts. (At this project are used to manipulate objects)

   - [H2 Database](https://www.baeldung.com/spring-boot-h2-database): Is a realtional database wrote in Java. (At this project used to persist the data created)

   - [Spring Data JPA](https://www.treinaweb.com.br/blog/iniciando-com-spring-data-jpa): Is a framework that make part of spring boot eco system, used to make interactions with database.  

   - [Spring Doc](https://springdoc.org/): Java library that helps to automate the generation of API documentation.

### How to run the project

  To run the project you only need follow the forward steps:

   - You need verify if you have the Java 19 JDK installed in your machine, if you don't have download here: [Java 19 JDK](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html)  

   - Clone the repository of this project:  

   ```bash
     git clone ${projectUrl}
   ```

   - Open the directory at Intellij and run the project. This will init the application at the localhost 8084

### Requests created for the challenge
  (OBS: That Requests can be tested at Postman)

  - Url's to create an Incident:

      - [POST] http://localhost:8084/api/v1/crud/create-incident  
         - Response Body: { "name": "Incident 1", "description": "That happens..." }  
            - Description: This request will create a new Incident and set automatically the dates.  

      - [POST] http://localhost:8084/api/v1/crud/create-full-incident  
         - Response Body: { "name": "Incident 1", "description": "That happens...", "createdAt": "13/11/2023", "updatedAt": "13/11/2023", "closedAt": "13/11/2023" }
            - Description: This request will create a new Incident with the information what are passed in the request and the unique value what are genereated is the idIncident.  


  - Url's to read Incidents:

    - [GET] http://localhost:8084/api/v1/crud/search-incident?idIncident=
        - Description: This request will return a unique register like a describe Incident.

    - [GET] http://localhost:8084/api/v1/crud/list-all-incidents
        - Description: This request will return all the incidents that have the closedAt date empty.

    - [GET] http://localhost:8084/api/v1/crud/list-recent-incidents
        - Description: This request will return the last 20 incidents that have the closedAt date empty.  


  - Url's to update an Incident:

    - [PUT] http://localhost:8084/api/v1/crud/update-incident
        - Response Body: { "idIncident": 1, "name": "Incident 2", "description": "That happens..."}
            - Description: This request will update the Incident and set automatically the dates.  

    - [PUT] http://localhost:8084/api/v1/crud/update-full-incident
        - Response Body: { "idIncident": 1, "name": "Incident 1", "description": "That happens...", "createdAt": "13/11/2023", "updatedAt": "13/11/2023", "closedAt": "13/11/2023" }
            - Description: This request will update the Incident with the information what are passed in the request.


  - Url's to delete an Incident:

    - [PUT] http://localhost:8084/api/v1/crud/soft-delete-incident?idIncident=
        - Description: This request will make the logic exclusion of the register, only add the closedAt date.

    - [DELETE] http://localhost:8084/api/v1/crud/hard-delete-incident?idIncident=
        - Description: This request will make the real delete of the register in database.

### Database Connection

  You can connect in the database that project uses at link (You need start the project)  

   - [Database link](http://localhost:8084/h2-console)
   - User: DiazeroTest
   - Password: x9(90^pmjrzorxr

### Annotations

  It was a cool experience doing this project, and the only thing I have a little difficulty with is the list of requests,
  it was a little ambiguous and I tried to make the requests to get the greatest number of use cases.
  I tried to make a class (Utils) with methods that I think can be used in a real Api to make the development more easy and efficient

