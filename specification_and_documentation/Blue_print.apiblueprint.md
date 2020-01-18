FORMAT: 1A

HOST: http://localhost

# Student Residence Service
This project is about creating a REST API for the student residence service.

# Group Appliances
This section groups API appliance resources.

## Appliance [/api/v1/appliances/{appliance_id}]
Home Applaince is one of the centric utilized by the Student Residence API. The appliance
can be different types of applainces which could be rennt by the studnets and each appliance
has specifications and details. Appliances are closely tied to follow graph...

+ Parameters
    + appliance_id: `1` (string) - The id of the Appliance.

+ Model (application/json)

    ```
    {
        "appliance_id" : "1",
        "model_name": "stovish",
        "rent_price": "200",
        "status" : "rented",
        "type": "Kitchen Appliance",
        "serial_number" : "LA5345523",
        "pictures": [{image1}, {image2}, ...]
    }
    ```

### Retrieve an Appliance [GET]
Return a specific Appliance.

+ Response 200
    [Appliance][]

### Delete an Appliance [DELETE]
Delete an Applaince. The current user must be the admin who created the Appliance. It returns the deleted Applaince on success.

+ Response 202
    [Appliance][]

### Update an Appliance [PUT]
Update an Applaince. The current user must be the admin who created the Appliance. It returns the deleted Applaince on success.

+ Response 204


## Appliance Collection [/api/v1/appliances]
A collection of Appliances.

+ Model (application/json)
    
    ```
        [
            {
                "appliance_id": "1",
                "Modal_Name": "modal1",
                "rent_price" : "200",
                "type" : "kitchen",
                "status" : "rented",
                "serial_number" : "05120351230",
                "rent_date": "11/20/2019, 11:51:39 AM",
                "pictures" : ["images1", "url", "image2"]
            }
            {
                "appliance_id": "2",
                "Modal_Name": "modal2",
                "rent_price" : "300",
                "type" : "sport",
                "status" : "available",
                "serial_number" : "03634651230",
                "rent_date": "11/20/2019, 11:51:39 AM",
                "pictures" : [{"url" : "images1"}, {"url", "image2"}]
            },
            .....
        ]
    ```

### Create an Appliance [POST]
Create a new Appliance object. Mentions and hashtags wil be parse out of the Appliance text, as will bare URLs..

+ Request
    [Appliance][]

+ Response 201
    [Appliance][]

### Retrieve all Appliances [GET]
Retrieve all Appliances.

+ Response 200
    [Appliance Collection][]






## Search Appliance [/api/v1/appliances{?appliance_name}{?type}{?modal_name}{?status}]

+ Parameters
    + appliance_name(optional, string)  - Appliance name
    + type(optional, string)            - Type of appliance  
    + modal_name(optional, string)      - Modal name of appliance
    + status(optional, string)          - status of appliance

+ Model (application/json)
    
    ```
        [
            {
                "appliance_id": "1",
                "Modal_Name": "modal1",
                "rent_price" : "200",
                "type" : "kitchen",
                "status" : "rented",
                "serial_number" : "05120351230",
                "rent_date": "11/20/2019, 11:51:39 AM",
                "pictures" : ["images1", "url", "image2"]
            }
            {
                "appliance_id": "2",
                "Modal_Name": "modal2",
                "rent_price" : "300",
                "type" : "sport",
                "status" : "available",
                "serial_number" : "03634651230",
                "rent_date": "11/20/2019, 11:51:39 AM",
                "pictures" : [{"url" : "images1"}, {"url", "image2"}]
            },
            ............
        ]
    ```
    
### Search Appliance [GET]

+ Response 200
    [Search Appliance][]
    


## Appliance Rent [/api/v1/appliances/{appliance_id}/rent]

An applaince can be rented or if rented, the rent could be terminated by the authorized members or accounts. Students can rent or terminate a specific appliance and the appliance will be added or removed on the student's rent list and the appliance status will be changed.

+ Paramters
    + appliance_id(required, string, `1`) - The ID of the appliance
    

+ Model (application/json)

    ```
    {
        "appliance_id" : "1",
        "rent_id"   :"2",
        "student_name"   :"Abc",
        "appliance_name": "stovish",
        "start_date"    : "11/20/2019, 11:51:39 AM",
        "selected_end_date"      :  "15/20/2019, 11:51:39 AM",
        "actual_end_date"        :  "15/20/2019, 11:51:39 AM",
        "max_end_date"           :   15/20/2019, 11:51:39 AM
        "total_Price": "200",
        "status" : "rented" ...]
    }
    ```

### Rent an Appliance [POST]
Save the given Appliance to the current User's appliance list. This is just a "save" action, not a sharing action.

+ Response 200
    [Appliance Rent][]

    
### Terminate a rent [DELETE]
Remove the given appliance from the current User's appliance list. This is just a "delete" action, not a sharing action.

+ Response 202
    [Appliance Rent][]

### Update a rent [PUT]
Update the given appliance rent from the current User's appliance list. This is just a "delete" action, not a sharing action.

+ Response 204

## Terminate Rent [/api/v1/appliances/{appliance_id}/terminate]
An applaince can be rented or if rented, the rent could be terminated by the authorized members or accounts. Students can rent or terminate a specific appliance and the appliance will be added or removed on the student's rent list and the appliance status will be changed.

+ Paramters
    + appliance_id(required, string, `1`) - The ID of the appliance

### Terminate a rent [POST]
Rent can be terminated as user requests for it.

+ Response 200
    [Appliance Rent][]


## Rent Collection [/api/v1/appliances/rents{?studentName}{?appliance_name}{?date}]
Collection of rented appliances.

+ Paramters
    + studentName(optional, string)    - Name of Student
    + appliancenName(optional, string)  - Name of Appliance
    + date(optional, string)           - Date of rent

+ Model (application/json)
    
    ```
        [
            {"appliance_id" : "1",
        "rent_id"   :"2",
        "student_name"   :"Abc",
        "appliance_name": "stovish",
        "start_date"    : "11/20/2019, 11:51:39 AM",
        "selected_end_date"      :  "15/20/2019, 11:51:39 AM",
        "actual_end_date"        :  "15/20/2019, 11:51:39 AM",
        "max_end_date"           :   15/20/2019, 11:51:39 AM
        "total_Price": "200",
        "status" : "rented" 
            },
            {
                "appliance_id" : "2",
        "rent_id"   :"4",
        "student_name"   :"Ac",
        "appliance_name": "stovish",
        "start_date"    : "11/20/2019, 11:51:39 AM",
        "selected_end_date"      :  "15/20/2019, 11:51:39 AM",
        "actual_end_date"        :  "15/20/2019, 11:51:39 AM",
        "max_end_date"           :   15/20/2019, 11:51:39 AM
        "total_Price": "200",
        "status" : "rented"
            },
            ........
        ]
    ```
   
### Search Appliances [GET]
Retrieve all list of rented appliances. Only admin can retrieve list of rented appliances.

+ Response 200
    [Rent Collection][]


## Announcement [/api/v1/announcements/{announcement_id}]
Announcment can be retrieved, updated, deleted through its specific id.

+ Parameters
    + announcement_id(required, string, `1`)  - ID of an announcement

+ Model (application/json)
    
    ```
         {
                "announcement_id": "1",
                "topic": "some topic",
                "message" : "some message",
                "creation_date" : "12/4/2019",
                "issuer" : "some issuer"
            }
    ```


### Get an announcement [GET]
An announcement can be retrieved by its specific ID and only the authorized person can retrieve specific announcement.

+ Response 200
    [Announcement][]
    

### Update an announcement [PUT]
An announcement can be updasted by its specific ID and the new updating information in form of JSON object.

+ Request Json Message (application/json)
    ```
        {
            "topic" : "New Topic Type",
            "message": "An updated accouncement about new students"
        }
    ```
    
+ Response 204
    

### Delete an announcement [DELETE]
An announcement can be deleted by its specific ID and only the authorized account can delete the announcmented.

+ Response 202
    [Announcement][]

## Announcements Collection [/api/v1/announcements{?category}{?issuer}{?date}]
To create new announcement or to retrieve all the list of announcements.

+ Parameters
    + category(optional, string)  - Category of Announcement
    + issuer(optional, string)    - Issuer of Announcement
    + date(optional, string)      - Date of Announcement

+ Model (application/json) 
    ```
      [
        {
            "announcement_id" : "1",
            "category" : "New Appliance"
            "topic" : "New Voccum Cleaner",
            "message": "A new voccum cleaner is available for the rent",
            "created_date": "12/04/2010 11:23:03 PM",
            "issuer" : {
                "name" : "Ahmad",
                "uri" : "/api/v1/accounts/54"
            }
        },
        {
            "announcement_id" : "325",
            "category" : "general information",
            "topic" : "About new students",
            "message": "A new accouncement about new students",
            "created_date": "12/04/2010 11:23:03 PM",
            "issuer" : {
                "name" : "Ahmad",
                "uri" : "/api/v1/accounts/54"
            }
        }
      ]
    ```
    
### Announcements Collection [GET]
Retrieve all the announcments.

+ Response 200
    [Announcements Collection][]

### Create an announcment [POST]
Create a new announcement.

+ Request Json Message (application/json)
    ```
    {
        "topic" : "Generate Information",
        "message": "A new accouncement about new students",
        "created_date": "12/04/2010 11:23:03 PM",
    }
    ```
    
+ Response 201
    [Announcement][]




## Reply [/api/v1/announcement/{annoucnement_id}/reply/{reply_id}]
Retrieve a specific replay.

+ Parameters
    + annoucnement_id(required, string, `1`)  - ID of a specific announcement
    + reply_id(required, string, `1`)         - ID of a specific replay

+ Model (application/json)
    ```
        {
            "replier_name" : "name",
            "message" : "Some message",
            "created_date": "12/12/2019"
        }
    ```

### Delete a Reply [DELETE]
Delete a specific reply by its ID.

+ Response 202
    [Reply][]
    
### Update a Reply [PUT]
Updating a specific replay by its ID and new updating information.

+ Request (application/json)
    ```
        {
            "message" : "new messgae"
        }
    ```
    
+ Response 204



## Reply Collection [/api/v1/announcements/{annoucnement_id}/reply]
List of replies on specific announcement.

+ Parameters
    + annoucnement_id(required, string, `1`)  - ID of a specific announcement

+ Model (application/json) 
    ```
        [
            {
            "replay_id" : "1",
            "message": "A new voccum cleaner is available for the rent",
            "created_date": "12/04/2010 11:23:03 PM",
            "replier" : {
                "name" : "Ahmad",
                "uri" : "/api/v1/accounts/54"
            },
            {
            "replay_id" : "2",
            "message": "Another replay",
            "created_date": "12/04/2010 11:23:03 PM",
            "replier" : {
                "name" : "Ahmad",
                "uri" : "/api/v1/accounts/54"
            }
        ]    
    ```
    
### Replies Collection [GET]
Retrieve replay collection of a specific announcement.

+ Response 200
    [Reply Collection][]