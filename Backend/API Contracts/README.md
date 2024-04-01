<h1>API Contracts</h1>
<h2>Universal Objects:</h2>
Error Message: 

The error message object is sent when something is wrong with the input or output of the API. Different errors will return different codes, along with a message for the user's convince.
```
{
        "httpStatus": <String>,
        "code": <Number>,
        "message": <String>
}
```
The httpStatus field will match the API return object's HTTP status

| Error Code | Message                                              | Common Cause                                                                                                                                                       |
|:----------:|------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|     1      | Error: Data can not be found in database             | The database does not contain matching information. This could be due to an incorrect input, but is also returned when there are no results from a search endpoint |
|     2      | Error: Data in database does not match provided data | When updating an item and the IDs do not match, also when deleting an item and the provided item does not match                                                    |
|     3      | Error: Input is not valid                            | The submitted data is in the correct format, but something is wrong with the values, or it is missing a field                                                      |
|     4      | Error: Could not complete transaction.               | The database has encountered an error                                                                                                                              |


<h2>API Contract Format:</h2>


<h3>HTTP Method | Endpoint URL</h3>

---
*Description of the endpoint*
- Headers
- URL Params
- Query Parameters
- Request Body 

Responses:
- Success Response
  - Code: ###
  - Type: (Data Type)
```
Sample output of the response object
```
- Failure Response
  - *Failure Type*
    - Code: ###
---