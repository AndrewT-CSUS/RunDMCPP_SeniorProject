<h1>Sermons</h1>

<h2>URL</h2>

/api/sermons

<h2>Sermon Object:</h2>

```
{
    "id": <String>,
    "name": <String>,
    "description": <String>,
    "youtubeLink": <String>,
    "dateTime": <LocalDateTime>
}
```

<br></br>

<h3>**GET** /getDefault</h3>

---
*Get up to 10 of the most recent Sermons*
- Headers
    - None
- URL Params
    - None
- Query Parameters
    - None
- Request Body
    - None

Responses:
- Success Response
    - Code: 200
    - Type: Sermon Array
```json
[
  {
    "id": "1fc1a6de-e279-4055-ad34-3da074c29c20",
    "name": "Jubilife City",
    "description": "Jubilife City. Very pleasant.",
    "youtubeLink": "https://www.youtube.com/watch?v=7IQliJHVLoo",
    "dateTime": "2024-02-10T19:00"
  },
  {
    "id": "ff45d470-f3de-492a-bb15-4e2a65980583",
    "name": "Driftveil City",
    "description": "Driftveil City. Wonderful!",
    "youtubeLink": "https://www.youtube.com/watch?v=xc_0wfIuuzw",
    "dateTime": "2024-02-10T20:44"
  },
  < More... >
]
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>**GET** /search/title/:title</h3>

---
*Searches for a Sermon with the given title, and returns all results*
- Headers
    - None
- URL Params
    - `title: <String>` - Required
- Query Parameters
  - None
- Request Body
    - None

Responses:
- Success Response
    - Code: 200
    - Type: Sermon Array
```json
[
  {
    "id": "1fc1a6de-e279-4055-ad34-3da074c29c20",
    "name": "Jubilife City",
    "description": "Jubilife City. Very pleasant.",
    "youtubeLink": "https://www.youtube.com/watch?v=7IQliJHVLoo",
    "dateTime": "2024-02-10T19:00"
  },
  {
    "id": "ff45d470-f3de-492a-bb15-4e2a65980583",
    "name": "Driftveil City",
    "description": "Driftveil City. Wonderful!",
    "youtubeLink": "https://www.youtube.com/watch?v=xc_0wfIuuzw",
    "dateTime": "2024-02-10T20:44"
  }
]
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>**GET** /search/date</h3>

---
*Searches for a Sermon between the given dates, and returns all results*
- Headers
    - None
- URL Params
    - None
- Query Parameters
  - `startDate: <LocalDateTime>`
  - `endDate: <LocalDateTime>`
- Request Body
    - None

Responses:
- Success Response
    - Code: 200
    - Type: Sermon Array
```json
[
  {
    "id": "1fc1a6de-e279-4055-ad34-3da074c29c20",
    "name": "Jubilife City",
    "description": "Jubilife City. Very pleasant.",
    "youtubeLink": "https://www.youtube.com/watch?v=7IQliJHVLoo",
    "dateTime": "2024-02-10T19:00"
  },
  {
    "id": "ff45d470-f3de-492a-bb15-4e2a65980583",
    "name": "Driftveil City",
    "description": "Driftveil City. Wonderful!",
    "youtubeLink": "https://www.youtube.com/watch?v=xc_0wfIuuzw",
    "dateTime": "2024-02-10T20:44"
  }
]
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>**GET** /get/:id</h3>

---
*Gets the Sermon with the specified ID*
- Headers
    - None
- URL Params
    - `id: <String>` - Required
- Query Parameters
    - None
- Request Body
    - None

Responses:
- Success Response
    - Code: 200
    - Type: Sermon
```json
{
    "id": "1fc1a6de-e279-4055-ad34-3da074c29c20",
    "name": "Jubilife City",
    "description": "Jubilife City. Very pleasant.",
    "youtubeLink": "https://www.youtube.com/watch?v=7IQliJHVLoo",
    "dateTime": "2024-02-10T19:00"
}
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>**POST** /create</h3>

---
*Creates a Sermon with the given data*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
  - None
- Query Parameters
  - None
    - Request Body
      - `Object: sermon` - Required 
      - Sample: 
      - ```json
        {
          "id": "1fc1a6de-e279-4055-ad34-3da074c29c20",
          "name": "Jubilife City",
          "description": "Jubilife City. Very pleasant.",
          "youtubeLink": "https://www.youtube.com/watch?v=7IQliJHVLoo",
          "dateTime": "2024-02-10T19:00"
        } 
        ```

Responses:
- Success Response
    - Code: 201
    - Type: Sermon
```json
{
    "id": "1fc1a6de-e279-4055-ad34-3da074c29c20",
    "name": "Jubilife City",
    "description": "Jubilife City. Very pleasant.",
    "youtubeLink": "https://www.youtube.com/watch?v=7IQliJHVLoo",
    "dateTime": "2024-02-10T19:00"
}
```
- Failure Response
    - Invalid Input
        - Status: 500
        - Code: 3
    - Transaction Fail
        - Status: 500
        - Code: 4
<br></br>

<h3>**PUT** /edit</h3>

---
*Updates the Sermon with the specified ID with the given data*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
    - None
- Query Parameters
    - None
        - Request Body
            - `Object: sermon` - Required
            - Sample:
            - ```json
              {
                "id": "1fc1a6de-e279-4055-ad34-3da074c29c20",
                "name": "Driftveil City",
                "description": "Driftveil City. Wonderful!",
                "youtubeLink": "https://www.youtube.com/watch?v=xc_0wfIuuzw",
                "dateTime": "2024-02-10T20:44"
              } 
      
Responses:
- Success Response
    - Code: 200
    - Type: Sermon
```json
{
    "id": "1fc1a6de-e279-4055-ad34-3da074c29c20", 
    "name": "Driftveil City",
    "description": "Driftveil City. Wonderful!",
    "youtubeLink": "https://www.youtube.com/watch?v=xc_0wfIuuzw",
    "dateTime": "2024-02-10T20:44"
}
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1
    - Data mismatch
        - Status: 500
        - Code: 2
    - Invalid Input
        - Status: 500
        - Code: 3
    - Transaction Fail
      - Status: 500
      - Code: 4

<br></br>

<h3>**DELETE** /delete</h3>

---
*Deletes the Sermon with the given ID, assuming the data matches*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
    - None
- Query Parameters
    - None
        - Request Body
            - `Object: sermon` - Required
            - Sample:
            - ```json
              {
                "id": "1fc1a6de-e279-4055-ad34-3da074c29c20",
                "name": "Driftveil City",
                "description": "Driftveil City. Wonderful!",
                "youtubeLink": "https://www.youtube.com/watch?v=xc_0wfIuuzw",
                "dateTime": "2024-02-10T20:44"
              } 

Responses:
- Success Response
    - Code: 200
    - Type: `No Return Body!`
- Failure Response
    - No Results
        - Status: 404
        - Code: 1
    - Data mismatch
        - Status: 500
        - Code: 2
    - Invalid Input
        - Status: 500
        - Code: 3
    - Transaction Fail
        - Status: 500
        - Code: 4

<br></br>
