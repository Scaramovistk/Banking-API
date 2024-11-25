# Financial-API
This project consists of an API to be used for opening a new “current account” of already existing
customers.

### Requirements
• The API will expose an endpoint which accepts the user information (customerID, initialCredit).
• Once the endpoint is called, a new account will be opened connected to the user whose ID is
customerID.
• Also, if initialCredit is not 0, a transaction will be sent to the new account.
• Another Endpoint will output the user information showing Name, Surname,
balance, and transactions of the accounts.

### Bonuses
• Accounts and Transactions are different services.
• Frontend (simple one is OK).
• Attention to CI/CD

## Getting started
**Follow the steps below**

```bash
# Clone the project and access the folder
git clone https://github.com/Scaramovistk/Banking-API.git && cd Banking-API/

# Run docker so you can build the image and install dependencies
docker compose build banking-api
docker compose up --detach banking-api

```

---

Made by:
Gabriel Scaramal 👋