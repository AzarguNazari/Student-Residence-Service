# About Project
This project is about creating a REST API for the student residence service. 

# Configuration
To run the project you need: 
- maven
- Java 8
- Intellij
- PostgresSQL (run docker image and create a database by name of StudentResidenceDB)

# Run by docker compose
- `./deploy.sh`

# Architecture used in the project 
 - Microservices

# Authorisation  used in the project 
 - OAUTH 2.0

# Web Client/UI 
 - React JS

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
