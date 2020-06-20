# About Project
This project is about creating a REST API for the student residence service. 

[See all the description of project](https://gitlab.rz.uni-bamberg.de/dsg/projects/rest-apis/option-a2/blob/master/PROJECT_DESCRIPTION.md)

# Implementation
All the project is based on Spring boot 2.1.7. Since Java 8 is the stable version of Java, we are going to use Java 8. If you are computer are installed Java version > 8, you need to downgrade it to version 8.

# Configuration
To run the project, you need to import it as MAVEN project in your IDE (Intellij or Eclipse) 
 - [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
 - Intellij | Eclipse
 - PostgresSQL (run docker image and create a database by name of StudentResidenceDB)

Command to make all submodules ready to run : `mvn clean package` and then run each services individually

# Architecture  used in the project 
 - Microservices

# Authorisation  used in the project 
 - OAUTH 2.0

# Web Client/UI 
 - React JS

# Development Framework
 - Spring boot Framework

 
Note : the project is a maven build.
  
  


# Database Setup  for the project 
-Create a data base in postgresql "StudentResidenceDB"  with role "tester" and password "tester"

# Redis Server Setup
Create a redis server for  Appliance-service and Bullentinboard-service and update in the application.properties file  in respective services

# Run the project
 Over Project is built based  on Microservices  Architecture , So each of the follwing is  a individual service
    -Api-Gateway
    -Authorization Server
    -Appliance-Service
    -Bullentin-Board Service
    
   Build the above services as maven build for all the services, it will generate executable jar files, excecute the jar files. 
    
   Note : Each of this service has to be imported as seperate Maven Project  while importing in Eclipse|Intelliij
    
    


# To access the rest services use the below URL
 - localhost:9852/api/v1/
 - localhost:9852/api/v1/bulletinboard
 - localhost:9852/api/v1/appliances

# Running with the help of postman
  # Getting the access token [JWT TOKEN]
   - localhost:9852/oauth/token [Post].
   - in the authorization , Type =BasicAuth  username: browser passowrd: browser
   - in the header section, enter the following key and the value 
   - key = Content-Type and value= application/x-www-form-urlencoded
   - in the body enter the  following key and value 
   -  key = "username" value =  client username 
   -  key = "password" value = Client password 
   -  key = "grant_type"   value = "password"
 
   
 # Appliance services
     -localhost:9852/api/v1/appliances
     - in the header add the following  key and the value 
     - key ="Authorization" value = " Bearer Acesstoken"  where acesstoken is the token genrated  by  localhost:9852/oauth/token
    

 # BulentinBoard  services
     -localhost:9852/api/v1/bulletinboard
     - in the header add the following  key and the value 
     - key ="Authorization" value = " Bearer Acesstoken"  where acesstoken is the token genrated  by  localhost:9852/oauth/token
   
  # UI of the Student Residence
     To see the user interface of the application, any of the following method can be used 
  
     method to run user interface on port 8080
     
    - cd client-ui
    - docker build . -t client-ui
    - docker run -p 8080:80 client-ui

     url : http://localhost:8080
     
     method to run user interface on port 3000
     - cd client-ui
     - run npm i
     - npm start
     
     This way the app will start at http://localhost:3000
     
  # Using Docker-Compose
    
    We can use `docker-compose` to run both the `client-ui` and the `redis` container. In order to do this in the application,
    
   `docker-compose up` 
   
   