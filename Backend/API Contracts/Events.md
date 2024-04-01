<h1>Events</h1>

<h2>URL</h2>

/api/events

<h2>Event Object:</h2>

```
{
    "name": <String>,
    "eventDescription": <String>,
    "eventLocation": <String>,
    "ttl": <Number>,
    "dateTime": <LocalDateTime>
}
```

<br></br>

<h3>GET /get</h3>

---
*Get all Events*
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
    - Type: Event Array
```json
[
  {
    "id": "ec8c90cb-ab1b-4a5c-b716-a58672d809be",
    "name": "Flower Dance",
    "eventDescription": "Come enjoy the Spring!",
    "eventLocation": "Grassy Field",
    "ttl": 1714369713,
    "dateTime": "2024-04-01T05:48:33.319Z"
  },
  {
    "id": "4212269f-089c-4a6c-a49e-5d7e6ac4a71e",
    "name": "Harvest Festival",
    "eventDescription": "Come and celebrate a bountiful harvest!",
    "eventLocation": "Town Square",
    "ttl": 1714369712,
    "dateTime": "2024-04-01T05:48:31.983Z"
  },
  {
    "id": "c13ce60d-eab9-4855-b58c-600763319aa5",
    "name": "Jellyfish Festival",
    "eventDescription": "Come and celebrate the arrival of the jellyfish swarm!",
    "eventLocation": "The Docks",
    "ttl": 1714370084,
    "dateTime": "2024-04-01T05:48:31.983Z"
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
*Searches for an Event with the given title, and returns all results*
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
    - Type: Event Array
```json
[
  {
    "id": "4212269f-089c-4a6c-a49e-5d7e6ac4a71e",
    "name": "Harvest Festival",
    "eventDescription": "Come and celebrate a bountiful harvest!",
    "eventLocation": "Town Square",
    "ttl": 1714369712,
    "dateTime": "2024-04-01T05:48:31.983Z"
  },
  {
    "id": "c13ce60d-eab9-4855-b58c-600763319aa5",
    "name": "Jellyfish Festival",
    "eventDescription": "Come and celebrate the arrival of the jellyfish swarm!",
    "eventLocation": "The Docks",
    "ttl": 1714370084,
    "dateTime": "2024-04-01T05:48:31.983Z"
  }
]
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>GET /search/date</h3>

---
*Searches for an Event between the given dates, and returns all results*
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
    - Type: Event Array
```json
[
  {
    "id": "4212269f-089c-4a6c-a49e-5d7e6ac4a71e",
    "name": "Harvest Festival",
    "eventDescription": "Come and celebrate a bountiful harvest!",
    "eventLocation": "Town Square",
    "ttl": 1714369712,
    "dateTime": "2024-04-01T05:48:31.983Z"
  },
  {
    "id": "c13ce60d-eab9-4855-b58c-600763319aa5",
    "name": "Jellyfish Festival",
    "eventDescription": "Come and celebrate the arrival of the jellyfish swarm!",
    "eventLocation": "The Docks",
    "ttl": 1714370084,
    "dateTime": "2024-04-01T05:48:31.983Z"
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
*Gets the Event with the specified ID*
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
    - Type: Event
```json
{
  "id": "4212269f-089c-4a6c-a49e-5d7e6ac4a71e",
  "name": "Harvest Festival",
  "eventDescription": "Come and celebrate a bountiful harvest!",
  "eventLocation": "Town Square",
  "ttl": 1714369712,
  "dateTime": "2024-04-01T05:48:31.983Z"
}
```
- Failure Response
    - No Results
        - Status: 404
        - Code: 1

<br></br>

<h3>POST /create</h3>

---
*Creates an Event with the given data*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
    - None
- Query Parameters
    - None
- Request Body
    - `Object: Event` - Required
    - Note: TTL is not required, and is automatically assigned by the server. Providing one will do nothing
    - Sample:
    - ```json
      {
        "name": "Jellyfish Festival",
        "eventDescription": "Come and celebrate the arrival of the jellyfish swarm!",
        "eventLocation": "The Docks",
        "dateTime": "2024-04-01T05:48:31.983Z"
      }
      ```

Responses:
- Success Response
    - Code: 201
    - Type: Event
```json
{
  "id": "c13ce60d-eab9-4855-b58c-600763319aa5",
  "name": "Jellyfish Festival",
  "eventDescription": "Come and celebrate the arrival of the jellyfish swarm!",
  "eventLocation": "The Docks",
  "ttl": 1714370084,
  "dateTime": "2024-04-01T05:48:31.983Z"
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
*Updates the Event with the specified ID with the given data*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
    - None
- Query Parameters
    - None
- Request Body
    - `Object: Event` - Required
    - Sample:
    - ```json
      {
        "id": "c13ce60d-eab9-4855-b58c-600763319aa5",
        "name": "Harvest Festival",
        "eventDescription": "Come and celebrate a bountiful harvest!",
        "eventLocation": "Town Square",
        "dateTime": "2024-04-01T05:48:31.983Z"
      }
      ```

Responses:
- Success Response
    - Code: 200
    - Type: Event
```json
{
  "id": "c13ce60d-eab9-4855-b58c-600763319aa5",
  "name": "Harvest Festival",
  "eventDescription": "Come and celebrate a bountiful harvest!",
  "eventLocation": "Town Square",
  "ttl": 1714369712,
  "dateTime": "2024-04-01T05:48:31.983Z"
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
*Deletes the Event with the given ID, assuming the data matches*
- Headers
    - Auth0 Bearer Token
    - `Content-Type: applications/json`
- URL Params
    - None
- Query Parameters
    - None
- Request Body
    - `Object: Event` - Required
  - Note: TTL is not required
  - Sample:
  - ```json
      {
        "id": "c13ce60d-eab9-4855-b58c-600763319aa5",
        "name": "Harvest Festival",
        "eventDescription": "Come and celebrate a bountiful harvest!",
        "eventLocation": "Town Square",
        "dateTime": "2024-04-01T05:48:31.983Z"
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
