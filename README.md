# Nusan-api

Main microservice of the Nusan Software project.

## Project

* API
* MySQL Database Integration using JPA Hibernate

## Prerequisites

### JDK

* Version: 11
* Vendor: AdoptOpenJDK (HotSpot) 11.0.10

## Development

### MySQL Database

The database is created through entity in the created models. If you want to modify the database, you have to modify the models and run the application.

The configuration file, resources/application.propieties, contains the database connection properties: path, user, and password. If you want to point to another database, you have to change these properties

### Insomia workspace

The json file, utils/Insomnia_2021-05-17, contains the calls to all controllers for testing.

### Postman workspace

The json file, utils/NUSAN.postman_collection.json, contains the calls to all controllers for testing.


