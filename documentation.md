# Real Estate API Project Documentation

## Overview
This API is a project for apps focused on acquiring and selling real estates.
It provides functionalities to select, add, update and remove properties, as well as manage users and profiles.
It uses JWT authentication as the main authentication method.

## Additional Info about JWT and 500 Internal Server Error response
Application requires JWT Token as mentioned earlier, thus not providing such token will cause 401 Unauthorized response.

500 Initial Server Error (Server Error) response is a generic error message that is sent when the server is unable to process the request due to some internal server error.
With this in mind, we will not put them in 'Possible Http Status Codes' because it is a generic error and can happen anytime.

## Clarification of the endpoints
**GET** - Retrieves data from the server.
**POST** - Creates data on the server.
**PUT** - Updates data on the server.
**PATCH** - Partially updates data on the server (Some fields are not required and can be left empty).
**DELETE** - Deletes data from the server.

## Breakpoints
Here we are going to explain the endpoints and their possible http status codes.
Format used for this will contain:
- URL
- Method
- Body (optional)
- Response (optional)
- Validations (optional)
- Possible Http Status Codes (Highlighted ones are the best cases for each endpoint)

Format will look like this:

### 0. Example Endpoint
- **URL**: `/example/url`
- **Method**: `EXAMPLE_METHOD`
    - **Body**:
      ```json
      {
         "field1": "value1",
         "field2": "value2"
      }
  - **Response**:
    ```json
    {
       "id": "id",
       "found_field": "value"
       "createdAt": "2069-01-01T00:00:00Z",
       "updatedAt": "2069-01-01T00:00:00Z"
    }
- **Validations**:
    - field1 must be defined
    - field1 must be unique
    - field2 must be defined
    - field2 must be at least 50 characters long
- **Possible Http Status Codes**:
    - **200 - OK (Object returned successfully)**
    - 400 - Bad Request (for instance: Field1 is not valid)
    - 404 - Not Found (Object not found)
- **Additional Info**:
- Additional info that is very important.

- Endpoints from the same controller will be separated by a blank line.
- Endpoints from different controllers will be separated by three blank lines.



### 1. Create (Register) User
 - **URL**: `/api/v0/auth/register`
 - **Method**: `POST`
   - **Body**:
     ```json
     {
        "email": "example@mail.com",
        "password": "example123"
     }
   - **Response**:
     ```json
     {
        "success": true
     }
- **Validations**:
    - email must be valid
    - email must be unique
    - email must be defined
    - password must be defined
- **Possible Http Status Codes**:
    - **200 - Ok (User found)**
    - 400 - Bad Request (email is invalid)
    - 409 - Conflict (email already exists)

### 2. Login User
  - **URL**: `/api/v0/auth/login`
  - **Method**: `POST`
    - **Body**:
      ```json
      {
         "email": "example@mail.com",
         "password": "example123"
      }
    - **Response**:
      ```json
      {
         "token": "very-long-token"
      }
- **Validations**:
    - email must be valid
    - email must be unique
    - email must be defined
    - password must be defined
- **Possible Http Status Codes**:
    - **200 - Ok (User logged in)**
    - 400 - Bad Request (email is invalid)
    - 404 - Not Found (User not found)
    - 409 - Conflict (password is invalid)



### 3. Get User by Id
- **URL**: `/api/v0/user/{userId}`
- **Method**: `GET`
    - **Response**:
      ```json
      {
         "id": "id",
         "email": "example@mail.com",
         "userFlagId": "another-id",
         "userRoleId": "yet-another-id"
         "createdAt": "2023-01-01T00:00:00Z",
         "updatedAt": "2023-01-01T00:00:00Z"
      }
- **Possible Http Status Codes**:
    - **200 - Ok (User found)**
    - 404 - Not Found (User not found)

### 4. Get User by Email
- **URL**: `/api/v0/user/email/{email}`
- **Method**: `GET`
    - **Response**:
      ```json
      {
         "id": "id",
         "email": "example@mail.com", 
         "userFlagId": "another-id",
         "userRoleId": "yet-another-id"
         "createdAt": "2023-01-01T00:00:00Z",
         "updatedAt": "2023-01-01T00:00:00Z"
      }
- **Possible Http Status Codes**:
    - **200 - Ok (User found)**
    - 404 - Not Found (User not found)

### 5. Update User by Id
- **URL**: `/api/v0/user/{userId}`
- **Method**: `PUT`
    - **Body**:
      ```json
      {
         "email": "another-example@mail.com",
         "password": "another-example123"
      }
- **Validations**:
    - email must be valid
    - email must be unique
    - email must be defined
    - password must be defined
- **Possible Http Status Codes**:
    - **204 - No Content (User updated)**
    - 400 - Bad Request (for instance: email is invalid or not defined)
    - 404 - Not Found (User not found)
    - 409 - Conflict (email already exists)

### 6. Patch User by Id
- **URL**: `/api/v0/user/{userId}`
- **Method**: `PATCH`
    - **Body**:
      ```json
      {
         "email": "another-example@mail.com",
         "password": "another-example123"
      }
- **Validations**:
    - email must be valid
    - email must be unique
    - email must be defined
    - password must be defined
- **Possible Http Status Codes**:
    - **204 - No Content (User updated)**
    - 400 - Bad Request (for instance: email is invalid)
    - 404 - Not Found (User not found)
    - 409 - Conflict (email already exists)


### 7. Delete User by Id
- **URL**: `/api/v0/user/{userId}`
- **Method**: `DELETE`
    - **Body**:
      ```json
      {
         "email": "another-example@mail.com",
         "password": "another-example123"
      }
- **Possible Http Status Codes**:
    - **204 - No Content (User deleted)**
    - 404 - Not Found (User not found)
- **Additional Info**:
- It is a removal of everything associated with the user, such as properties, profiles, etc.



### 8. Get UserFlag by UserId
- **URL**: `/api/v0/user/flag/user/{userId}`
- **Method**: `GET`
    - **Response**:
    ```json
    {
       "id": "id",
       "is_verified": "true",
       "is_muted": "false",
       "is_banned": "false",
       "createdAt": "2023-01-01T00:00:00Z",
       "updatedAt": "2023-01-01T00:00:00Z"
    }
- **Possible Http Status Codes**:
    - **200 - OK (UserFlag found)**
    - 404 - Not Found (UserFlag not found)

### 9. Get UserFlag by Id
- **URL**: `/api/v0/user/flag/{userFlagId}`
- **Method**: `GET`
    - **Response**:
    ```json
    {
       "id": "id",
       "is_verified": "true",
       "is_muted": "false",
       "is_banned": "false",
       "createdAt": "2023-01-01T00:00:00Z",
       "updatedAt": "2023-01-01T00:00:00Z"
    }
- **Possible Http Status Codes**:
    - **200 - OK (UserFlag found)**
    - 404 - Not Found (UserFlag not found)

### 10. Update UserFlag by Id
- **URL**: `/api/v0/user/flag/{userFlagId}`
- **Method**: `PUT`
    - **Body**:
      ```json
      {
         "is_verified": "true",
         "is_muted": "true",
         "is_banned": "false"
      }
- **Validations**:
    - is_verified must be defined
    - is_muted must be defined
    - is_banned must be defined
- **Possible Http Status Codes**:
    - **204 - No Content (UserFlag updated)**
    - 400 - Bad Request (for instance: is_verified is not defined)
    - 404 - Not Found (UserFlag not found)

### 11. Update UserFlag by Id
- **URL**: `/api/v0/user/flag/{userFlagId}`
- **Method**: `PATCH`
    - **Body**:
      ```json
      {
         "is_muted": "true"
      }
- **Possible Http Status Codes**:
    - **204 - No Content (UserFlag updated)**
    - 404 - Not Found (UserFlag not found)

### 12. Delete UserFlag by Id
- **URL**: `/api/v0/user/flag/{userFlagId}`
- **Method**: `DELETE`
- **Possible Http Status Codes**:
    - **204 - No Content (UserFlag updated)**
    - 404 - Not Found (UserFlag not found)

    