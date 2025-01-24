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
  - **Params**:
    - param1 (***String***) (optional)
    - param2 (***Integer***)
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

### 11. Patch UserFlag by Id
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
    - **204 - No Content (UserFlag deleted)**
    - 404 - Not Found (UserFlag not found)



### 13. Get UserRole by UserId
- **URL**: `/api/v0/user/role/user/{userId}`
- **Method**: `GET`
  - **Response**:
    ```json
    {
       "id": "id",
       "role": "USER",
       "createdAt": "2023-01-01T00:00:00Z",
       "updatedAt": "2023-01-01T00:00:00Z"
    }
- **Possible Http Status Codes**:
  - **200 - OK (UserRole found)**
  - 404 - Not Found (UserRole not found)
- **Additional Info**:
- 'role' field contains an enum value (OWNER, ADMIN, USER)


### 14. Get UserRole by Id
- **URL**: `/api/v0/user/role/{userRoleId}`
- **Method**: `GET`
  - **Response**:
    ```json
    {
       "id": "id",
       "role": "ADMIN",
       "createdAt": "2023-01-01T00:00:00Z",
       "updatedAt": "2023-01-01T00:00:00Z"
    }
- **Possible Http Status Codes**:
  - **200 - OK (UserRole found)**
  - 404 - Not Found (UserRole not found)

### 15. Update UserRole by Id
- **URL**: `/api/v0/user/role/{userRoleId}`
- **Method**: `PUT`
  - **Body**:
    ```json
    {
       "role": "ADMIN"
    }
- **Validations**:
  - role must be defined
  - role must be 'USER', 'ADMIN' or 'OWNER'
- **Possible Http Status Codes**:
  - **204 - No Content (UserRole updated)**
  - 400 - Bad Request (for instance: role is not defined or its value is invalid)
  - 404 - Not Found (UserRole not found)

### 16. Patch UserRole by Id
- **URL**: `/api/v0/user/role/{userRoleId}`
- **Method**: `PATCH`
  - **Body**:
    ```json
    {
       "role": "USER"
    }
- **Validations**:
  - role must be 'USER', 'ADMIN' or 'OWNER'
- **Possible Http Status Codes**:
  - **204 - No Content (UserFlag updated)**
  - 400 - Bad Request (for instance: role value is invalid)
  - 404 - Not Found (UserFlag not found)

### 17. Delete UserRole by Id
- **URL**: `/api/v0/user/role/{userRoleId}`
- **Method**: `DELETE`
- **Possible Http Status Codes**:
  - **204 - No Content (UserRole deleted)**
  - 404 - Not Found (UserRole not found)



### 18. Get Profiles
- **URL**: `/api/v0/profile`
- **Method**: `GET`
  - **Params**:
    - nickName (***String***)
    - country (***String***) (optional)
    - state (***String***) (optional)
    - city (***String***) (optional)
    - sortBy (***String***) (optional)
    - sortDirection (***String***) (optional)
    - page (***Integer***) (optional)
    - size (***Integer***) (optional)
  - **Response**:
    ```json
    {
       "content": [
         {
         "id": "id",
         "first_name": "John",
         "last_name": "Doe",
         "nick_name": "WisdomChasesAfterYouButYouAreFaster",
         "phone_number": "019091837"
         "avatar_url": "example_url.jpg",
         "bio": "My bio",
         "country": "Sweden",
         "state": "Skåne",
         "city": "Malmö",
         "profile_settings_id": "EX4MPL3-UU1D",
         "profile_statistics_id": "EX4MPL3-UU1D",
         "birth_date": "2025-01-23T15:45:30"
         "createdAt": "2069-01-01T00:00:00Z",
         "updatedAt": "2069-01-01T00:00:00Z"
         }
      ],
       "...": "..."
    }
- **Validations**:
  - nickName param must be defined
- **Possible Http Status Codes**:
  - **200 - Ok (Profiles found)**
  - 400 - Bad Request (for instance: wrong params or 'nickName' param not defined)
- **Additional Info**:
  - 'content' in response can be either empty or contain some Profiles.
  - the three dots symbolize the rest of response that's not as important as content itself 

### 20. Get Profile by Id
- **URL**: `/api/v0/profile/{profileId}`
- **Method**: `GET`
  - **Response**:
    ```json
    {
       "id": "id",
       "first_name": "John",
       "last_name": "Doe",
       "nick_name": "WisdomChasesAfterYouButYouAreFaster",
       "phone_number": "019091837"
       "avatar_url": "example_url.jpg",
       "bio": "My bio",
       "country": "Sweden",
       "state": "Skåne",
       "city": "Malmö",
       "profile_settings_id": "EX4MPL3-UU1D",
       "profile_statistics_id": "EX4MPL3-UU1D",
       "birth_date": "2025-01-23T15:45:30"
       "createdAt": "2069-01-01T00:00:00Z",
       "updatedAt": "2069-01-01T00:00:00Z"
    }
- **Possible Http Status Codes**:
  - **200 - OK (Profile found)**
  - 404 - Not Found (Profile not found)

### 21. Create Profile
- **URL**: `/api/v0/profile`
- **Method**: `POST`
  - **Body**:
    ```json
    {
       "first_name": "John",
       "last_name": "Doe",
       "nick_name": "WisdomChasesAfterYouButYouAreFaster",
       "phone_number": "019091837"
       "avatar_url": "example_url.jpg",
       "bio": "My bio",
       "country": "Sweden",
       "state": "Skåne",
       "city": "Malmö",
       "birth_date": "2025-01-23T15:45:30"
    }
  - **Response**:
    ```json
    {
       "id": "id",
       "first_name": "John",
       "last_name": "Doe",
       "nick_name": "WisdomChasesAfterYouButYouAreFaster",
       "phone_number": "019091837"
       "avatar_url": "example_url.jpg",
       "bio": "My bio",
       "country": "Sweden",
       "state": "Skåne",
       "city": "Malmö",
       "profile_settings_id": "EX4MPL3-UU1D",
       "profile_statistics_id": "EX4MPL3-UU1D",
       "birth_date": "2025-01-23T15:45:30"
       "createdAt": "2069-01-01T00:00:00Z",
       "updatedAt": "2069-01-01T00:00:00Z"
    }
- **Validations**:
  - first_name must be defined
  - first_name must contain between 2 and 100 characters
  - last_name must be defined
  - last_name must contain between 2 and 100 characters
  - nick_name must be defined
  - nick_name must contain between 3 and 50 characters
  - phone_number must be defined
  - phone_number must be 9 characters long
  - avatar_url must be defined
  - bio must be defined
  - bio must contain between 1 and 255 characters
  - country must be defined
  - name of a country must contain between 2 and 100 characters
  - state must be defined
  - name of a state must contain between 2 and 150 characters
  - city must be defined
  - name of a city must contain between 2 and 150 characters
  - birth_date must be defined
  - birth_date must be in the past
- **Possible Http Status Codes**:
  - **200 - OK (Profile created)**
  - 400 - Bad Request (for instance: country is not defined)
  - 409 - Conflict (nick_name already exists)

### 22. Update Profile
- **URL**: `/api/v0/profile/{profileId}`
- **Method**: `PUT`
  - **Body**:
    ```json
    {
       "first_name": "John",
       "last_name": "Doe",
       "nick_name": "WisdomChasesAfterYouButYouAreFaster",
       "phone_number": "019091837"
       "avatar_url": "example_url.jpg",
       "bio": "My bio",
       "country": "Sweden",
       "state": "Skåne",
       "city": "Malmö",
       "birth_date": "2025-01-23T15:45:30"
    }
- **Validations**:
  - first_name must be defined
  - first_name must contain between 2 and 100 characters
  - last_name must be defined
  - last_name must contain between 2 and 100 characters
  - nick_name must be defined
  - nick_name must contain between 3 and 50 characters
  - phone_number must be defined
  - phone_number must be 9 characters long
  - avatar_url must be defined
  - bio must be defined
  - bio must contain between 1 and 255 characters
  - country must be defined
  - name of a country must contain between 2 and 100 characters
  - state must be defined
  - name of a state must contain between 2 and 150 characters
  - city must be defined
  - name of a city must contain between 2 and 150 characters
  - birth_date must be defined
  - birth_date must be in the past
- **Possible Http Status Codes**:
  - **204 - No Content (Profile updated)**
  - 400 - Bad Request (for instance: country is not defined)
  - 404 - Not Found (Profile not found)
  - 409 - Conflict (nick_name already exists)

### 23. Patch Profile
- **URL**: `/api/v0/profile/{profileId}`
- **Method**: `PATCH`
  - **Body**:
    ```json
    {
       "first_name": "John",
       "last_name": "Doe",
       "nick_name": "WisdomChasesAfterYouButYouAreFaster",
       "phone_number": "019091837"
       "avatar_url": "example_url.jpg",
       "bio": "My bio",
       "country": "Sweden",
       "state": "Skåne",
       "city": "Malmö",
       "birth_date": "2025-01-23T15:45:30"
    }
- **Validations**:
  - first_name must contain between 2 and 100 characters
  - last_name must contain between 2 and 100 characters
  - nick_name must contain between 3 and 50 characters
  - phone_number must be 9 characters long
  - bio must contain between 1 and 255 characters
  - name of a country must contain between 2 and 100 characters
  - name of a state must contain between 2 and 150 characters
  - name of a city must contain between 2 and 150 characters
  - birth_date must be in the past
- **Possible Http Status Codes**:
  - **204 - No Content (Profile updated)**
  - 400 - Bad Request (for instance: birth_day is not in the past)
  - 404 - Not Found (Profile not found)
  - 409 - Conflict (nick_name already exists)

### 24. Delete Profile
- **URL**: `/api/v0/profile/{profileId}`
- **Method**: `DELETE`
- **Possible Http Status Codes**:
  - **204 - No Content (Profile deleted)**
  - 404 - Not Found (Profile not found)



### 25. Get ProfileActivity by ProfileId
- **URL**: `/api/v0/profile/activity/profile/{profileId}`
- **Method**: `GET`
  - **Params**:
    - page (***Integer***) (optional)
    - size (***Integer***) (optional)
  - **Response**:
    ```json
    {
       "content": [
         {
           "id": "id",
           "activity_title": "title",
           "activity_description": "description",
           "activity_date": "2059-01-01T00:00:00Z",
           "created_at": "2069-01-01T00:00:00Z",
           "updated_at": "2069-01-01T00:00:00Z"
         },
       ],
       "...": "..."
    }
- **Possible Http Status Codes**:
  - **200 - OK (PropertyActivities found)**
- **Additional Info**:
  - 'content' in response can be either empty or contain some Profiles

### 26. Get ProfileActivity by Id
- **URL**: `/api/v0/profile/activity/{profileActivityId}}`
- **Method**: `GET`
  - **Response**:
    ```json
    {
       "id": "id",
       "activity_title": "title",
       "activity_description": "description",
       "activity_date": "2059-01-01T00:00:00Z",
       "created_at": "2069-01-01T00:00:00Z",
       "updated_at": "2069-01-01T00:00:00Z"
    }
- **Possible Http Status Codes**:
  - **200 - OK (ProfileActivity found)**
  - 404 - Not Found (ProfileActivity not found)

### 27. Create ProfileActivity
- **URL**: `/api/v0/profile/activity`
- **Method**: `POST`
  - **Body**:
    ```json
    {
       "activity_title": "title",
       "activity_description": "description",
       "activity_date": "2059-01-01T00:00:00Z",
    }
  - **Params**:
    - profileId (***String***)
  - **Response**:
    ```json
    {
       "id": "id",
       "activity_title": "title",
       "activity_description": "description",
       "activity_date": "2059-01-01T00:00:00Z",
       "created_at": "2069-01-01T00:00:00Z",
       "updated_at": "2069-01-01T00:00:00Z"
    }
- **Validations**:
  - activity_title must be defined
  - activity_title must contain between 2 and 50 character
  - activity_description must be defined
  - activity_description must contain between 1 and 255 characters
  - activity_date must be defined
  - activity_date must be in the past
- **Possible Http Status Codes**:
  - **200 - OK (ProfileActivity created)**
  - 400 - Bad Request (for instance: profileId is not valid or not defined)
  - 404 - Not Found (Profile not found)

### 28. Update ProfileActivity
- **URL**: `/api/v0/profile/activity/{profileActivityId}`
- **Method**: `PUT`
  - **Body**:
    ```json
    {
       "activity_title": "title",
       "activity_description": "description",
       "activity_date": "2059-01-01T00:00:00Z",
    }
- **Validations**:
  - activity_title must be defined
  - activity_title must contain between 2 and 50 character
  - activity_description must be defined
  - activity_description must contain between 1 and 255 characters
  - activity_date must be defined
  - activity_date must be in the past
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileActivity updated)**
  - 400 - Bad Request (for instance: activity_description exceeds 255 characters)
  - 404 - Not Found (ProfileActivity not found)

### 29. Patch ProfileActivity
- **URL**: `/api/v0/profile/activity/{profileActivityId}`
- **Method**: `PATCH`
  - **Body**:
    ```json
    {
       "activity_title": "title",
       "activity_description": "description",
       "activity_date": "2059-01-01T00:00:00Z",
    }
- **Validations**:
  - activity_title must contain between 2 and 50 character
  - activity_description must contain between 1 and 255 characters
  - activity_date must be in the past
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileActivity updated)**
  - 400 - Bad Request (for instance: activity_description exceeds 255 characters)
  - 404 - Not Found (ProfileActivity not found)

### 30. Delete ProfileActivity
- **URL**: `/api/v0/profile/activity/{profileActivityId}`
- **Method**: `DELETE`
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileActivity deleted)**
  - 404 - Not Found (ProfileActivity not found)



### 31. Get ProfileAsks by ProfileId
- **URL**: `/api/v0/profile/ask/profile/{profileId}`
- **Method**: `GET`
  - **Params**:
    - page (***Integer***) (optional)
    - size (***Integer***) (optional)
  - **Response**:
    ```json
    {
       "content": [
         {
           "id": "id",
           "profile_id": "EX4MPL3_UUID",
           "ask_title": "title",
           "ask_description": "description",
           "ask_answer": "answer",
           "created_at": "2069-01-01T00:00:00Z",
           "updated_at": "2069-01-01T00:00:00Z"
         },
       ],
       "...": "..."
    }
- **Possible Http Status Codes**:
  - **200 - OK (ProfileAsks found)**
- **Additional Info**:
  - 'content' in response can be either empty or contain some Profiles

### 31. Get ProfileAsk by Id
- **URL**: `/api/v0/profile/ask/{profileAskId}`
- **Method**: `GET`
  - **Response**:
    ```json
    {
       "id": "id",
       "profile_id": "EX4MPL3_UUID",
       "ask_title": "title",
       "ask_description": "description",
       "ask_answer": "answer",
       "created_at": "2069-01-01T00:00:00Z",
       "updated_at": "2069-01-01T00:00:00Z"
    }
- **Possible Http Status Codes**:
  - **200 - OK (ProfileAsk found)**
  - 404 - Not Found (ProfileAsk not found)

### 32. Create ProfileAsk
- **URL**: `/api/v0/profile/ask`
- **Method**: `POST`
  - **Body**:
    ```json
    {
       "ask_title": "title",
       "ask_description": "description",
       "ask_answer": "answer",
    }
  - **Params**:
    - profileId (***String***)
  - **Response**:
    ```json
    {
       "id": "id",
       "profile_id": "EX4MPL3_UUID",
       "ask_title": "title",
       "ask_description": "description",
       "ask_answer": "answer",
       "created_at": "2069-01-01T00:00:00Z",
       "updated_at": "2069-01-01T00:00:00Z"
    }
- **Validations**:
  - ask_title must be defined
  - ask_title must contain between 2 and 50 characters
  - ask_description must be defined
  - ask_description must contain between 2 and 150 characters
  - ask_answer must be defined
  - ask_answer must contain between 2 and 200 characters
- **Possible Http Status Codes**:
  - **200 - OK (ProfileActivity created)**
  - 400 - Bad Request (for instance: profileId is not valid or not defined)
  - 404 - Not Found (ProfileActivity not found)

### 33. Update ProfileAsk
- **URL**: `/api/v0/profile/ask/{profileAskId}`
- **Method**: `PUT`
  - **Body**:
    ```json
    {
       "ask_title": "title",
       "ask_description": "description",
       "ask_answer": "answer",
    }
- **Validations**:
  - ask_title must be defined
  - ask_title must contain between 2 and 50 characters
  - ask_description must be defined
  - ask_description must contain between 2 and 150 characters
  - ask_answer must be defined
  - ask_answer must contain between 2 and 200 characters
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileAsk updated)**
  - 400 - Bad Request (for instance: ask_title must be defined)
  - 404 - Not Found (ProfileAsk not found)

### 34. Patch ProfileAsk
- **URL**: `/api/v0/profile/ask/{profileAskId}`
- **Method**: `PATCH`
  - **Body**:
    ```json
    {
       "ask_title": "title",
       "ask_description": "description",
       "ask_answer": "answer",
    }
- **Validations**:
  - ask_title must contain between 2 and 50 characters
  - ask_description must contain between 2 and 150 characters
  - ask_answer must contain between 2 and 200 characters
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileAsk updated)**
  - 400 - Bad Request (for instance: ask_answer must be at least 2 characters long)
  - 404 - Not Found (ProfileAsk not found)

### 35. Delete ProfileAsk
- **URL**: `/api/v0/profile/ask/{profileAskId}`
- **Method**: `PATCH`
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileAsk deleted)**
  - 404 - Not Found (ProfileAsk not found)



### 36. Get ProfileFollows by FollowerId
- **URL**: `/api/v0/profile/follow/follower/{followerId}`
- **Method**: `GET`
  - **Params**: 
    - page (***Integer***) (optional)
    - size (***Integer***) (optional)
  - **Response**:
    ```json
    {
       "content": [
         {
           "id": "id",
           "follower_id": "EX4MPL3_UUID",
           "following_id": "EX4MPL3_UUID",
           "created_at": "2069-01-01T00:00:00Z",
           "updated_at": "2069-01-01T00:00:00Z"
         },
       ],
       "...": "..."
    }
- **Possible Http Status Codes**:
  - **200 - OK (ProfileFollows found)**
  - 404 - Not Found (Profile not found)
- **Additional Info**:
  - 'content' in response can be either empty or contain some Profiles

### 37. Get ProfileFollows by FollowingId
- **URL**: `/api/v0/profile/follow/following/{followingId}`
- **Method**: `GET`
  - **Params**:
    - page (***Integer***) (optional)
    - size (***Integer***) (optional)
  - **Response**:
    ```json
    {
       "content": [
         {
           "id": "id",
           "follower_id": "EX4MPL3_UUID",
           "following_id": "EX4MPL3_UUID",
           "created_at": "2069-01-01T00:00:00Z",
           "updated_at": "2069-01-01T00:00:00Z"
         },
       ],
       "...": "..."
    }
- **Possible Http Status Codes**:
  - **200 - OK (ProfileFollows found)**
  - 404 - Not Found (Profile not found)
- **Additional Info**:
  - 'content' in response can be either empty or contain some Profiles

### 38. Create ProfileFollow
- **URL**: `/api/v0/profile/follow`
- **Method**: `POST`
  - **Body**:
    ```json
    {
       "follower_id": "EX4MPL3_UUID",
       "following_id": "EX4MPL3_UUID",
    }
  - **Response**:
    ```json
    {
       "id": "id",
       "follower_id": "EX4MPL3_UUID",
       "following_id": "EX4MPL3_UUID",
       "created_at": "2069-01-01T00:00:00Z",
       "updated_at": "2069-01-01T00:00:00Z"
    }
- **Validations**:
  - follower_id must be defined
  - following_id must be defined
- **Possible Http Status Codes**:
  - **200 - OK (ProfileFollow created)**
  - 400 - Bad Request (for instance: following_id is not defined)
  - 404 - Not Found (Profile not found)

### 39. Delete ProfileFollow
- **URL**: `/api/v0/profile/follow`
- **Method**: `DELETE`
  - **Params**:
    - followerId (***String***)
    - followingId (***String***)
- **Possible Http Status Codes**:
  - **200 - OK (ProfileFollow deleted)**
  - 400 - Bad Request (for instance: followerId is not defined)
  - 404 - Not Found (Profile not found)



### 40. Get ProfileRates by ProfileId
- **URL**: `/api/v0/profile/rate/profile/{profileId}`
- **Method**: `GET`
  - **Params**:
    - page (***Integer***) (optional)
    - size (***Integer***) (optional)
  - **Response**:
    ```json
    {
       "content": [
         {
           "id": "id",
           "evaluator_id": "EX4MPL3_UUID",
           "title": "title",
           "description": "description",
           "rate": 10,
           "created_at": "2069-01-01T00:00:00Z",
           "updated_at": "2069-01-01T00:00:00Z"
         },
       ],
       "...": "..."
    }
- **Possible Http Status Codes**:
  - **200 - OK (ProfileRates found)**
  - 404 - Not Found (Profile not found)
- **Additional Info**:
  - 'content' in response can be either empty or contain some Profiles

### 41. Get ProfileRate by Id
- **URL**: `/api/v0/profile/rate/{profileRateId}`
- **Method**: `GET`
  - **Response**:
    ```json
    {
      "id": "id",
      "evaluator_id": "EX4MPL3_UUID",
      "title": "title",
      "description": "description",
      "rate": 10,
      "created_at": "2069-01-01T00:00:00Z",
      "updated_at": "2069-01-01T00:00:00Z"
    }
- **Possible Http Status Codes**:
  - **200 - OK (ProfileRates found)**
  - 404 - Not Found (Profile not found)

### 42. Create ProfileRate
- **URL**: `/api/v0/profile/rate`
- **Method**: `POST`
  - **Params**:
    - profileId (***String***)
  - **Body**:
    ```json
    {
       "evaluator_id": "EX4MPL3_UUID",
       "title": "title",
       "description": "description",
       "rate": 10,
    }
  - **Response**:
    ```json
    {
      "id": "id",
      "evaluator_id": "EX4MPL3_UUID",
      "title": "title",
      "description": "description",
      "rate": 10,
      "created_at": "2069-01-01T00:00:00Z",
      "updated_at": "2069-01-01T00:00:00Z"
    }
- **Validations**:
  - evaluator_id must be defined
  - title must be defined
  - title of a rate must contain between 2 and 50 characters
  - description must be defined
  - description of a rate must contain between 2 and 100 characters
  - rate must be defined
  - rate must value be between 0 and 10
- **Possible Http Status Codes**:
  - **200 - OK (ProfileRate created)**
  - 400 - Bad Request (for instance: profileId is not defined)
  - 404 - Not Found (Profile not found)

### 43. Update ProfileRate
- **URL**: `/api/v0/profile/rate/{profileRateId}`
- **Method**: `PUT`
  - **Body**:
    ```json
    {
       "title": "title",
       "description": "description",
       "rate": 10,
    }
- **Validations**:
  - title must be defined
  - title of a rate must contain between 2 and 50 characters
  - description must be defined
  - description of a rate must contain between 2 and 100 characters
  - rate must be defined
  - rate must value be between 0 and 10
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileRate updated)**
  - 400 - Bad Request (for instance: description must be defined)
  - 404 - Not Found (ProfileRate not found)

### 44. Patch ProfileRate
- **URL**: `/api/v0/profile/rate/{profileRateId}`
- **Method**: `PATCH`
  - **Body**:
    ```json
    {
       "evaluator_id": "EX4MPL3_UUID",
       "title": "title",
       "description": "description",
       "rate": 10,
    }
- **Validations**:
  - title of a rate must contain between 2 and 50 characters
  - description of a rate must contain between 2 and 100 characters
  - rate must value be between 0 and 10
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileRate updated)**
  - 400 - Bad Request (for instance: description of a rate must contain between 2 and 100 characters)
  - 404 - Not Found (ProfileRate not found)

### 45. Delete ProfileRate
- **URL**: `/api/v0/profile/rate/{profileRateId}`
- **Method**: `Delete`
- **Possible Http Status Codes**:
  - **204 - No Content (ProfileRate deleted)**
  - 404 - Not Found (ProfileRate not found)