# Inappropriate Word Detection API

This application is designed to detect and filter out inappropriate words from a given sentence. It provides two main endpoints: one for loading a list of inappropriate words into the system and another for checking a sentence against this list. The API is protected by an `apiKey` that must be included in the request headers.


## Endpoints

**Description:**  
This endpoint allows you to load a list of inappropriate words into the system. This should be done before using the `checksentence` endpoint.

### 1. Load Words
**Method:** POST  
**URL:** `http://localhost:8080/api/v1/loadwords`

**Headers:**
- `Key:` apiKey
- `Value:` 2banapi



### 2. Check Sentence
**Method:** POST  
**URL:** `http://localhost:8080/api/v1/checksentence`

**Headers:**
- `Key:` apiKey
- `Value:` 2banapi

**Body (JSON):**
```json
{
  "sentence": "Бомба Fuck dude Idiot BC Gandu"
}
```


## For Swagger File
**Go to Your browser and type** 
`http://localhost:8080/api-docs`  <br>
`http://localhost:8080/swagger-ui/index.html`
