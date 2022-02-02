# Nusan-api

<h1 align="center">
 <div>
  <img src="https://github.com/jorgenusan/nusan-electron/blob/master/src/img/LogoCompleto200.png">
 </div>
</h1>

 ## Table of Contents
 
 * [About](#about)
   * [Technologies](#technologies-computer)
 * [Project](#project-rocket)
 * [Prerequisites](#prerequisites-clipboard)
    * [Version control](#version-control)
    * [JDK](#jdk)
    * [MySQL Database](#mysql-database)
 * [Test](#test)
 * [Insomia workspace](#insomia-workspace)
 * [Postman workspace](#postman-workspace)
 * [Author](#author)

 ## About
 
 Nusan is a desktop application for small and medium businesses, aimed at the world of repair. 
 It is a storage and visualization tool for repair parts, customers and employees.
 
 It is made up of 2 repositories. One for the back-end and one for the front-end.
 
 * Front-end:
 
    https://github.com/jorgenusan/nusan-electron

 * Back-end:
  
    https://github.com/jorgenusan/nusanapi
    
 * Web:
 
    https://nusanweb.herokuapp.com/
    
    >**Note:**
    >From this website you can download the installer to use the application.
    
### Technologies :computer:

* [Spring Boot](https://spring.io/projects/spring-boot)
* [MySQL](https://www.mysql.com/)
* [Hibernate](https://hibernate.org/)
    
## Project :rocket:

* API REST
* MySQL Database Integration using JPA Hibernate
* BCryptPasswordEncoder password encryption
* Data validation with Spring Validator

## Prerequisites :clipboard:

### Version control

You'll need [Git](https://git-scm.com/)

```
git@2.31.0 or higher
```

### JDK

* Version: 11
* Vendor: AdoptOpenJDK (HotSpot) 11.0.10

### MySQL Database

The database is created through entity in the created models. If you want to modify the database, you have to modify the models and run the application.

The configuration file, resources/application.propieties, contains the database connection properties: path, user, and password. If you want to point to another database, you have to change these properties

## Test

### Insomia workspace

The json file, utils/Insomnia_2021-05-17, contains the calls to all controllers for testing.

[Download Insomnia](https://insomnia.rest/download)

### Postman workspace

The json file, utils/NUSAN.postman_collection.json, contains the calls to all controllers for testing.

[Download Postman](https://www.postman.com/downloads/)

## Author

* Jorge Núñez Santiago - https://github.com/jorgenusan

