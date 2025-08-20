# DBank
q: Describe what happens when Kafka or the mySql database goes down?

a: Kafka is down:
   When creating a transaction, we will have attempt to publish the event to Kafka in synchronous mode in TransactionService.java
   If Kafka is unavailable, it retries up to 1 min, After that, the transaction will still be processed ignoring publishing to Kafka (please refer to the try/ catch in KafkaTransactionProducer.java)

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
API's:

Customer:

create a new customer - 
POST /api/customers

list all customers - 
GET /api/customers

get customer by Id - 
GET /api/customers/{id}

-------------------------------------------
Account:

create a new account - 
POST /api/accounts

list all accounts - 
GET /api/accounts

get account by Id - 
GET /api/accounts/{id} 

-------------------------------------------
Transaction:

create a new Transaction -
POST /api/transactions

list all Transactions - 
GET /api/transactions

get Transaction by Id - 
GET /api/transactions/{id} 
