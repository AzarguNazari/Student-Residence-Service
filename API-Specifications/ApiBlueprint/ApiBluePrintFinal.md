FORMAT: 1A

HOST: http://localhost

# Student Residence Service
This project is about creating a REST API for the student residence application

## Student Residence Service API Root [/]

This resource does not have any attributes. Instead it offers the initial API affordances.
### Retrieve the Entry Point [GET]

+ Response 200 (application/json)

        {
            "links": [
                {
                    "rel": [ "appliances" ],
                    "href": "/v1/appliances"
                }
                {
                    "rel": ["announcements"],
                    "href": "/v1/bulletinboard"
                }
                     ]
        }
# OAuth 2
This API Blueprint showcases OAuth 2.

## OAuth token exchange [/oauth/token]
Exchange credentials for `access_token`. Supported types:

+ `client_credentials`
+ [`urn:ietf:params:oauth:grant-type:jwt-bearer`](https://tools.ietf.org/html/draft-jones-oauth-jwt-bearer-03#section-6)

### Exchange credentials for token [POST]
+ Request
    + Attributes (OAuth grant request)
    + Headers

            Authorization: Basic ABCDEF

+ Response 200 (application/json)
    + Attributes (OAuth valid response)

+ Request
    + Attributes (OAuth jwt-bearer grant request)

+ Response 200 (application/json)
    + Attributes (OAuth valid response)

## OAuth 2 Bearer Token protected resource [/protected]
### Status [GET]
+ Request
    + Headers

            Authentication: Bearer JWT

+ Response 200 (application/json)
    + Attributes (Server response)

# Data Structures
## OAuth grant request (object)
+ `grant_type`: `client_credentials` (string, required)

## OAuth jwt-bearer grant request (object)
+ `grant_type`: `urn:ietf:params:oauth:grant-type:jwt-bearer` (string, required)
+ assertion: `eyJhbGciOiJIUzI1NiJ9.e30.XmNK3GpH3Ys_7wsYBfq4C3M6goz71I7dTgUkuIa5lyQ` (string, required)

## OAuth valid response (object)
+ `access_token`: `eyJhbGciOiJIUzI1NiJ9.e30.XmNK3GpH3Ys_7wsYBfq4C3M6goz71I7dTgUkuIa5lyQ` (string, required) - valid JWT
+ scope: all (string, required) - scopes of current token
+ `expires_in`: 300 (number, required)
+ `token_type`: Bearer (string, required)

## Server response (object)
+ status: ok (string, required)

# Group Appliances
This section groups API appliance resources.

## Appliance [/v1/appliances/{appliance-id}]


+ Parameters
    + appliance-id : `1` (string) - The id of the Appliance.

+ Model (application/json)

    ```
    {
                "id": "1",
                "model-name": "Model Name of the HA",
                "Appliance_type" : "Type of the HA",
                "state" : "available",
                "price_per_day" : "10,
                "max_time" : "5",
                "available_appliances" : "10",
                "creation_date" : "11/20/2019, 11:51:39 AM",
                "deleted_on" : "11/20/2019, 11:51:39 AM
    }
    ```

### getAppliance [GET]
returns a specific house appliance
+ Parameters
    + appliance-id : `1` (string) - The id of the Appliance.


+ Response 200
    [Appliance][]
+ Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource
    

### Delete an Appliance [DELETE]
Successfully deletion of the house appliances
+ Parameters
    + appliance-id : `1` (string) - The id of the Appliance.
+ Response 204
    deletion of the house appliances
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource
    
### Update an Appliance [PUT]
Updates a specific House appliance
+ Parameters
    + appliance-id : `1` (string) - The id of the Appliance.

+ Response 200
    Successfully updated the house appliance
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource


## Appliance Collection [/api/v1/appliances]
A collection of Appliances.

+ Model (application/json)
    
    ```
        [
            {
                "id": "1",
                "model-name": "Model Name of the HA",
                "Appliance_type" : "Type of the HA",
                "state" : "available",
                "price_per_day" : "10,
                "max_time" : "5",
                "available_appliances" : "10",
                "creation_date" : "11/20/2019, 11:51:39 AM",
                "deleted_on" : "11/20/2019, 11:51:39 AM
    }
           {
                "id": "2",
                "model-name": "Model Name of the HA",
                "Appliance_type" : "Type of the HA",
                "state" : "available",
                "price_per_day" : "10,
                "max_time" : "5",
                "available_appliances" : "10",
                "creation_date" : "11/20/2019, 11:51:39 AM",
                "deleted_on" : "11/20/2019, 11:51:39 AM
    },
            .....
        ]
    ```

### Create an Appliance [POST]
Adds a new House appliance

+ Request
    [Appliance][]

+ Response 201
    Successfully created a new appliance 
+ Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource
    

### getAppliances [GET]
Retrieve all Appliances.
+ Parameters
    + page 1 : (optional, number) - The page of questions to return
    + model_name : (optional, string)      - Model Name of the HA
    + type : (optional, string)      - Type of the HA
    + status : (optional, string)      - Status of the HA

+ Response 200
    [Appliance Collection][]
+ Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource


## Appliance Types [/v1/appliances/types]
+ Model (application/json)

    ```
    {
                "id": "1",
                "name": "Type of the Appliance",
                
    }
    ```

### getAppliances[GET]
Returns a list of all appliance types


+ Response 200
[Appliance Types][]
+ Response 401
    description: Unauthorized. Please check your credentials
+ Response 403
    description: Forbidden. Sorry, you can not access this resource.




    


## Appliance Rent [/v1/appliances/{appliance-id}/rent]

Adds rent to a specific house appliance

+ Parameters
    + appliance-id(required, string, `1`) - The ID of the appliance
    

+ Model (application/json)

    ```
    {
                "id": "1",
                "serial_number": "Serial number ",
                "student" : "ABC",
                "appliance" : "available",
                "creation_date" : "10,
                "selected_end_date" : "11/20/2019, 11:51:39 AM",
                "actual_end_date" : "11/20/2019, 11:51:39 AM",
                "rent_amount" : "15",
                "number_of_appliances" : "1",
                "status" : "Rented"
    }
    ```

### addRenttoAppliance [POST]
Adds rent to a specific house appliance
+ Parameters
    + appliance-id(required, string, `1`) - The ID of the appliance

+ Response 201
    Successfully addition of rent to the house appliance 
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource
    

## Rent [/v1/appliances/{appliance-id}/rent/{rent-id}]


+ Parameters
    + appliance-id(required, string, `1`) - The ID of the appliance
    + rent-id(required, string, `1`) - The ID of the rent
    

+ Model (application/json)

    ```
    {
                "id": "1",
                "serial_number": "Serial number ",
                "student" : "ABC",
                "appliance" : "available",
                "creation_date" : "10,
                "selected_end_date" : "11/20/2019, 11:51:39 AM",
                "actual_end_date" : "11/20/2019, 11:51:39 AM",
                "rent_amount" : "15",
                "number_of_appliances" : "1",
                "status" : "Rented"
    }
    ```

### getSpecificRent [GET]
Retrieves a specific rent
+ Parameters
    + appliance-id(required, string, `1`) - The ID of the appliance
    + rent-id(required, string, `1`) - The ID of the rent

+ Response 200
    [Rent][]
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource
    



    
### Terminate a rent [PUT]
Terminates a rent on an House appliance
+ Parameters
     + appliance-id (required, string, `1`) - The ID of the appliance
     + rent-id (required, string, `1`) - The ID of the rent


+ Response 200
    Successful deletion of rent on the house appliance 
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource



## Rent Collection [/v1/appliances/rent]
 Retrieves rents of all HAs

+ Parameters
    + status(optional, string)    - Status of the Rent
    + name(optional, string)      - Name of Student
   

+ Model (application/json)
    
    ```
        [
         {
                "id": "1",
                "serial_number": "Serial number ",
                "student" : "ABC",
                "appliance" : "available",
                "creation_date" : "10,
                "selected_end_date" : "11/20/2019, 11:51:39 AM",
                "actual_end_date" : "11/20/2019, 11:51:39 AM",
                "rent_amount" : "15",
                "number_of_appliances" : "1",
                "status" : "Rented"
    }
            ,
            {
                "id": "2",
                "serial_number": "Serial number ",
                "student" : "ABC",
                "appliance" : "available",
                "creation_date" : "10,
                "selected_end_date" : "11/20/2019, 11:51:39 AM",
                "actual_end_date" : "11/20/2019, 11:51:39 AM",
                "rent_amount" : "15",
                "number_of_appliances" : "1",
                "status" : "Rented"
    },
            ........
        ]
    ```
   
### allRents [GET]
Retrieves rents of all HAs

+ Response 200
    [Rent Collection][]
    + Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource



## Announcement [/v1/bulletinboard/{announcement-id}]
Announcment can be retrieved, updated, deleted through its specific id.

+ Parameters
    + announcement_id(required, string, `1`)  - ID of an announcement

+ Model (application/json)
    
    ```
         {
                "id": "1",
                "external_id" : "external id ",
                "user" : "name of the user",
                "announcement_type": "Model Name of the HA",
                "description" : "description",
                "priority" : "priority",
                "creation_date" : "11/20/2019, 11:51:39 AM",
                "appliance_serial_number" : "appliance serial number"
    }
    ```


### Get an announcement [GET]
An announcement can be retrieved by its specific ID and only the authorized person can retrieve specific announcement.

+ Response 200
    [Announcement][]
+ Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource



## Announcements Collection [/v1/bulletinboard]

+ Parameters
    + page: 1 (optional, number) - The page of questions to return
    + id(optional, string)      - id of the announcemnet
    + issuer(optional, string)      - issuer of the announcement
    + creation_date(optional, string)      - cretaed date of the announcement
    

+ Model (application/json) 
    ```
      [
        {
                "id": "1",
                "external_id" : "external id ",
                "user" : "name of the user",
                "announcement_type": "Model Name of the HA",
                "description" : "description",
                "priority" : "priority",
                "creation_date" : "11/20/2019, 11:51:39 AM",
                "appliance_serial_number" : "appliance serial number"
            }
        },
        {
                "id": "1",
                "external_id" : "external id ",
                "user" : "name of the user",
                "announcement_type": "Model Name of the HA",
                "description" : "description",
                "priority" : "priority",
                "creation_date" : "11/20/2019, 11:51:39 AM",
                "appliance_serial_number" : "appliance serial number"
            }
        }
      ]
    ```
    
### getAllAnnouncement [GET]
Returns a list of all announcements as per filter.
+ Parameters
    + page: 1 (optional, number) - The page of questions to return
    + id(optional, string)      - id of the announcemnet
    + issuer(optional, string)      - issuer of the announcement
    + creation_date(optional, string)      - cretaed date of the announcement

+ Response 200
    [Announcements Collection][]
+ Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource
    

### addannouncement [POST]
Create a new announcement.

+ Request 
[Announcement][]

+ Response 201
    Successfully created a new announcement
+ Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource




## Reply [/v1/bulletinboard/{announcement-id}/reply]
Retrieve a specific replay.

+ Parameters
    + annoucnement_id(required, string, `1`)  - ID of a specific announcement
   

+ Model (application/json)
    ```
        {
            "id" : "name",
            "user" : "Some message",
            "announcement": "announcement id",
            "creation_date": "12/12/2019",
            "message_text" : " message text"
        }
    ```


    
### addReply [PUT]
Updating a specific replay by its ID and new updating information.

+ Request
[Reply][]

    
+ Response 204
 Successfully crated a new reply.
 + Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource



## Reply Collection [/api/v1/announcements/{annoucnement_id}/reply]
List of replies on specific announcement.

+ Parameters
    + annoucnement_id(required, string, `1`)  - ID of a specific announcement

+ Model (application/json) 
    ```
        [
            {
            "id" : "name",
            "user" : "Some message",
            "announcement": "announcement id",
            "creation_date": "12/12/2019",
            "message_text" : " message text"
        },
            {
            "id" : "name",
            "user" : "Some message",
            "announcement": "announcement id",
            "creation_date": "12/12/2019",
            "message_text" : " message text"
        }
        ]    
    ```
    
### getReplies[GET]
Retrieve replay collection of a specific announcement.

+ Response 200
    [Reply Collection][]
+ Response 400
    Bad Request. Please check the parameters
+ Response 401
    Unauthorized. Please check your credentials
+ Response 403
    Forbidden. Sorry, you can not access this resource