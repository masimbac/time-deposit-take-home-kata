# TimeDeposit Application

This Spring Boot application manages time-deposit accounts, calculates interest, and exposes two REST endpoints.

---

## Prerequisites

- **Java**: 17 or higher
- **Maven**: 3.6+
- **Database**: (Optional) H2 in-memory is used by default; you can configure any JDBC datasource in `application.properties`

---

## Build & Run

1. **Clone the repo**
   ```bash
   git clone https://github.com/masimbac/time-deposit-take-home-kata.git
   cd time-deposit-take-home-kata/java
   ```
   
2. **Build with Maven**
    ```bash
    mvn clean install
   ```
3. **Run the application**
    ```bash 
    java -jar target/*.jar
   ```
   The application will start on port 8080 by default
4. **API Endpoints**

4.1 ***GET All Time Deposits***
```bash 
curl -X GET http://localhost:8080/time-deposits

```
Response (HTTP 200):
```json
[
  {
    "id": 1,
    "planType": "basic",
    "balance": 1000.00,
    "days": 60,
    "withdrawals": [ ]
  }
]

```
4.2 ***Recalculate and persist balances***
Applies interest rules to every time-deposit record and persists the updated balances.
```bash
curl -X PUT http://localhost:8080/time-deposits/balance
```
Response (HTTP 204 No Content)