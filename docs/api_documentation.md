# API Documentation - EvoGameSuite

## Authentication

### **Login**
**Endpoint:** `POST /api/auth/login`

**Request Body:**
```json
{
  "username": "user",
  "password": "password"
}
```

**Response:**
```json
{
  "token": "<JWT-TOKEN>"
}
```

**Description:** Authenticates a user and returns a JWT token.

---

## Test Results API

### **Get All Test Results**
**Endpoint:** `GET /api/results`

**Headers:**
```
Authorization: Bearer <JWT-TOKEN>
```

**Response:**
```json
[
  {
    "id": 1,
    "username": "user1",
    "killedMutants": 50,
    "survivedMutants": 10,
    "mutationScore": 83.3,
    "testDate": "2024-02-06T10:00:00"
  }
]
```

**Description:** Returns all test results stored in the database.

---

### **Get Test Results by Username**
**Endpoint:** `GET /api/results/{username}`

**Headers:**
```
Authorization: Bearer <JWT-TOKEN>
```

**Response:**
```json
[
  {
    "id": 1,
    "username": "user1",
    "killedMutants": 50,
    "survivedMutants": 10,
    "mutationScore": 83.3,
    "testDate": "2024-02-06T10:00:00"
  }
]
```

**Description:** Returns test results filtered by username.

---

### **Save a New Test Result**
**Endpoint:** `POST /api/results`

**Headers:**
```
Authorization: Bearer <JWT-TOKEN>
Content-Type: application/json
```

**Request Body:**
```json
{
  "username": "user1",
  "killedMutants": 50,
  "survivedMutants": 10,
  "mutationScore": 83.3,
  "testDate": "2024-02-06T10:00:00"
}
```

**Response:**
```json
{
  "id": 2,
  "username": "user1",
  "killedMutants": 50,
  "survivedMutants": 10,
  "mutationScore": 83.3,
  "testDate": "2024-02-06T10:00:00"
}
```

**Description:** Stores a new test result in the database.
