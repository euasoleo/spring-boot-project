# Spring Boot Java Assignment


This assignment is created using:
* Spring Initializer (https://start.spring.io/), Maven project using Spring Boot 2.1.1 
  * Dependencies with Web, JPA, H2, DevTools 
* Spring Tool Suite
* H2 Database Engine (embedded in-memory database)
* Opencsv 3.9 (http://opencsv.sourceforge.net/)
* Postman (for API testing)

## Features
This is web application with one endpoint /users, that provides two RESTful APIs function. 

|S/N|Resource|Method|Description|
|---|---|---|---|
|1|http://localhost:8080/users|POST| Upload a user data CSV file (inside body, form-data with key 'file') and the function will insert all the users information into a database. After uploading successfully, the filename, filetype and size will be return in JSON. | 
|2|http://localhost:8080/users|GET| Getting list of users that have valid salary (0 <= salary <= 4000) in JSON.| 

## Database Schema
**Table**: User

**Attributes**: Id (BigInt(19)), Name (Varchar(255)), Salary(Double(17))

## CSV File Sample

Example: user-data.csv
```
name,salary
John,2500.06
Mary Posa,4000.00
Mike,4001.00
Pipe,0
Simon,100.20
Organ,3999.99
Pepper,-10.99
```

##  Setup and Test Project

1) Open Spring Tool Suite.
2) Go to File > Import... > Maven > Existing Maven Projects.
3) Right click the loaded project > Run As > Spring Boot App.
4) Use Postman and POST with user-data.csv in the form-data with 'file' as key.
5) Use Postman and GET the list of valid users with valid salary.