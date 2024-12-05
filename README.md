# **Financial-API**

The **Financial-API** is a backend service that enables the creation and management of current accounts for existing customers. The API supports account initialization with optional transactions and provides endpoints to retrieve detailed customer and account information.

---

## **Features**
- **Current Account Creation**: Open a new account for a customer using their ID, with an optional initial balance.
- **Transaction Handling**: Automatically logs a transaction if an initial balance is provided.
- **Account Information Retrieval**: Fetch customer details, including:
  - Name
  - Surname
  - Account Balance
  - Transaction History

---

## **Requirements**
1. **Endpoints**:
   - **Create Account**:
     - Accepts `customerID` and `initialCredit` as input.
     - Opens a new account linked to the provided `customerID`.
     - Adds a transaction if `initialCredit > 0`.
   - **Retrieve Account Information**:
     - Outputs:
       - Customer's `Name` and `Surname`.
       - Account `Balance`.
       - Associated `Transactions`.
---

## **Getting Started**

### **Prerequisites**
- Docker installed on your system.
- Git for cloning the repository.
- A tool like `curl` for interacting with the API.

### **Setup Instructions**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Scaramovistk/Banking-API.git
   cd Banking-API/
   ```

2. **Build and Run the Application**:
   - Build the Docker image and install dependencies:
     ```bash
     docker compose build banking-api
     ```
   - Start the service:
     ```bash
     docker compose up banking-api
     ```

   - The application will start a Tomcat server at `https://localhost:8443`.

---

## **Using the API**

You can interact with the API using a terminal or a browser.

### **1. Create a Current Account**
Send a `POST` request to create a new account:
```bash
curl -X POST https://localhost:8443/api/v1/accounts/current-accounts \
     -H "Content-Type: application/json" \
     -d '{
           "id": "customer-id",
           "balance": "initial-credit"
         }'
```

### **2. Retrieve Account Information**
Send a `GET` request to fetch details of an existing account:
```bash
curl -X GET -k https://localhost:8443/api/v1/accounts/current-accounts/${customerID}
```

Replace `${customerID}` with the actual customer ID.

---

## **Author**
👋 Created by **Gabriel Scaramal**

---
