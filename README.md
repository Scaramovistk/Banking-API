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

2. **Bonuses**:
   - Modular services for Accounts and Transactions.
   - Provide a basic frontend for user interaction.
   - Implement CI/CD pipelines for deployment.

---

## **Getting Started**

### **Prerequisites**
- Docker and docker-compose installed on your system.
- Git for cloning the repository.
- A tool like `curl` for interacting with the API.

### **Setup Instructions**
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Scaramovistk/Banking-API.git && cd Banking-API/
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

   - The server will start on port `8443`.

---

## **Using the API**

You can interact with the API using a terminal or a browser.

### Browser

Open `https://localhost:8443/` to create your account or get information about other accounts.

### **1. Create a Current Account**
Send a `POST` request to create a new account:
```bash
curl -X POST -k https://localhost:8443/api/v1/accounts/current-accounts \
     -H "Content-Type: application/json" \
     -d '{
           "id": "${customerID}",
           "balance": "${initial_balance}"
         }'
```

Replace `${customerID}` and `${initial_balance}` with the real value.

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