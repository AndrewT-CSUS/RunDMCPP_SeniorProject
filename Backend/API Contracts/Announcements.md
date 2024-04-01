<h1>Announcements</h1>

<h2>URL</h2>

/api/announcements

<h2>Announcement Object:</h2>

```
{
    "id": <String>,
    "title": <String>,
    "description": <String>,
    "ttl": <Number>
}
```

<br></br>

<h3>GET /get</h3>

---
*Get all Announcements*
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
    - Type: Announcement Array
```json
[
  {
    "id": "ec10de61-4702-4b96-96d6-be8b9638313b",
    "title": "Hello Everyone!",
    "description": "Thank you for coming!",
    "ttl": 1710126429
  },
  {
    "id": "090463e2-829e-4afa-b541-0327276257d4",
    "title": "Hello World",
    "description": "The world says hello back",
    "ttl": 1702253624
  }
]
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>GET /search/title/:title</h3>

---
*Searches for an Announcement with the given title, and returns all results*
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
    - Type: Announcement Array
```json
[
  {
    "id": "ec10de61-4702-4b96-96d6-be8b9638313b",
    "title": "Hello Everyone!",
    "description": "Thank you for coming!",
    "ttl": 1710126429
  },
  {
    "id": "090463e2-829e-4afa-b541-0327276257d4",
    "title": "Hello World",
    "description": "The world says hello back",
    "ttl": 1702253624
  }
]
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>GET /get/:id</h3>

---
*Gets the Announcement with the specified ID*
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
    - Type: Announcement
```json
{
  "id": "ec10de61-4702-4b96-96d6-be8b9638313b",
  "title": "Hello Everyone!",
  "description": "Thank you for coming!",
  "ttl": 1710126429
}
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>POST /create</h3>

---
*Creates an Announcement with the given data*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
    - None
- Query Parameters
    - None
- Request Body
    - `Object: Announcement` - Required
    - Note: TTL is not required, and is automatically assigned by the server. Providing one will do nothing
    - Sample:
    - ```json
      {
        "id": "ec10de61-4702-4b96-96d6-be8b9638313b",
        "title": "Hello Everyone!",
        "description": "Thank you for coming!"
      }
      ```

Responses:
- Success Response
    - Code: 201
    - Type: Announcement
```json
{
  "id": "ec10de61-4702-4b96-96d6-be8b9638313b",
  "title": "Hello Everyone!",
  "description": "Thank you for coming!",
  "ttl": 1710126429
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

<h3>PUT /edit</h3>

---
*Updates the Announcement with the specified ID with the given data*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
    - None
- Query Parameters
    - None
- Request Body
    - `Object: Announcement` - Required
  - Sample:
  - ```json
      {
        "id": "ec10de61-4702-4b96-96d6-be8b9638313b",
        "title": "Hello Everyone!",
        "description": "Thank you for coming!"
      }
      ```

Responses:
- Success Response
    - Code: 200
    - Type: Announcement
```json
{
  "id": "ec10de61-4702-4b96-96d6-be8b9638313b",
  "title": "Hello World",
  "description": "The world says hello back",
  "ttl": 1710126429
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

<h3>DELETE /delete</h3>

---
*Deletes the Announcement with the given ID, assuming the data matches*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
    - None
- Query Parameters
    - None
- Request Body
    - `Object: Announcement` - Required
    - Note: TTL is not required
    - Sample:
    - ```json
        {
          "id": "ec10de61-4702-4b96-96d6-be8b9638313b",
          "title": "Hello World",
          "description": "The world says hello back"
        }
        ```

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
