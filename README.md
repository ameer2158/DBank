# DBank
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



to consume and print all messages from the bank.transactions.transfer topic starting from the beginning please run -->
kafka-console-consumer.sh --bootstrap-server localhost:9092 \
  --topic bank.transactions.transfer --from-beginning
