# Weather Service

## List references

* [admin](#admin-methods)
* [user](#user-methods)

### Admin methods
#### Get User List
```
    METHOD  GET
    URL     api/v1/admin/get-user-list
    HEADER  Authorization: Bearer {token}
    PARAMS  ?page=0&size=10
    
    Response Body
    {
        "code": 200,
        "message": "success",
        "data": {
            "content": [],
            "totalElements": 10,
            "totalPages": 1,
            "size": 1,
            "numberOfElements": 10
        }
    }
```
#### Get City List
```
    METHOD  GET
    URL     api/v1/user/get-city-list
    HEADER  Authorization: Bearer {token}
    
    Response Body
    {
        "code": 200,
        "message": "success",
        "data": [
            {
                "id": 1,
                "name": "Tashkent",
                "country": "Uzbekistan"
            },
            {
                "id": 2,
                "name": "London",
                "country": "Great Britain"
            },
            ...
        ]
    }
```
#### Add City
```
    METHOD  GET
    URL     api/v1/admin/add-city
    HEADER  Authorization: Bearer {token}
    Request Body 
    {
        "name": "Tashkent",
        "latitude": "", (optional)
        "longitude": "", (optional)
        "country": "Uzbekistan"
    }
    
    Response Body
    {
        "code": 200,
        "message": "success",
        "data": {
            "id": 1,
            "name": "Tashkent",
            "country": "Uzbekistan"
        }
    }
```
#### Get User Details
```
    METHOD  GET
    URL     api/v1/admin/get-user-details/{userId}
    HEADER  Authorization: Bearer {token}
    Response Body
    {
        "code": 200,
        "message": "success",
        "data": {
            "id": 1,
            "username": "dostonjon",
            "firstname": "Doston",
            "lastname": "Rakhimov"
        }
    }
```
#### Update User Details
```
    METHOD  GET
    URL     api/v1/admin/update
    HEADER  Authorization: Bearer {token}
    
    Request body
    {
        "id": 1,
        "username": "doston",
        "firstname": "Doston",
        "lastname": "Rakhimov"
    }
     
    Response Body
    {
        "code": 200,
        "message": "success",
        "data": "User data has been updated"
    }
```
### User methods

#### Subscribe To City
```
    METHOD  GET
    URL     api/v1/user/subscribe-to-city
    HEADER  Authorization: Bearer {token}
    
    Request body
    {
        "city_id": 1
    }
     
    Response Body
    {
        "code": 200,
        "message": "success",
        "data": "City added successfully."
    }
```

#### Get City List
```
    METHOD  GET
    URL     api/v1/user/get-city-list
    HEADER  Authorization: Bearer {token}
    
    Response Body
    {
        "code": 200,
        "message": "success",
        "data": [
            {
                "id": 1,
                "name": "Tashkent",
                "country": "Uzbekistan"
            },
            {
                "id": 2,
                "name": "London",
                "country": "Great Britain"
            },
            ...
        ]
    }
```
#### Get City Subscription
```
    METHOD  GET
    URL     api/v1/user/get-subscriptions
    HEADER  Authorization: Bearer {token}
    
    Response Body
    {
        "code": 200,
        "message": "success",
        "data": [
            {
                "id": 1,
                "name": "Tashkent",
                "country": "Uzbekistan"
            },
            {
                "id": 2,
                "name": "London",
                "country": "Great Britain"
            },
            ...
        ]
    }
```
