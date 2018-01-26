#ABC BANKING GROUP - COURSEWORK
##By Ahmed Al-Adaileh

###Introduction
This Java application is developed as part of the coursework to implement a basic software for the 
ABC Banking Group. It is designed to illustrate the Service-Oriented-Architectural Style.

###Important note:
This application is runnign in the cloud. The free Host company Heroku is used for this purpose. 
Services can be reached via the link: https://warm-harbor-89034.herokuapp.com/

Here are some example cUrl calls:

- `curl --request POST \
     --url https://warm-harbor-89034.herokuapp.com/api/main-service/login \
     --header 'authorization: Basic YXBpdXNlcjpwYXNz' \
     --header 'content-type: application/json' \
     --cookie JSESSIONID=5E262F41A56AC6BAC50A89DD10919DC5 \
     --data '{  
   	 "username":"aadaileh",
      "password":"pass"
   }'`

- `curl --request GET \
     --url https://warm-harbor-89034.herokuapp.com/api/account-service/account/1 \
     --header 'authorization: Basic YXBpdXNlcjpwYXNz' \
     --cookie JSESSIONID=5E262F41A56AC6BAC50A89DD10919DC5`

This server may return the following message:
`{
 	"timestamp": 1517007867178,
 	"status": 400,
 	"error": "Bad Request",
 	"exception": "com.zaxxer.hikari.pool.HikariPool$PoolInitializationException",
 	"message": "Failed to initialize pool: User 'b9579c6ae9cba0' has exceeded the 'max_user_connections' resource (current value: 10)",
 	"path": "/api/account-service/account/1"
 }`
 
 Please wait for couple of minutes and try again. The reason for this error is the free-profile i am using. Heroku offers only ONE MySql connection for free accounts :(

### Frontend (Client applications)
Frontend website is also hosted in a different place and can be reached via http://abc-banking-group-coursework.epizy.com/home.php
###Technical Background
This application is developed using the following components:
1. Java SDK 1.8
2. Maven
3. Spring Boot
4. GitHub as a repository
5. MySql Database using jdbc
6. Netflix Feign Client
7. Swagger 
8. Consul (disabled for cloud solution)

### Database
MySql is used for this application. The database dump file (includes all tables) is part of the 
submitted zip file. It is can be found under the resources folder `database-dump.sql`

###Authentication
#### Basic Authentication:
- Username: apiuser
- Password: pass

#### Online-Banking - DESKTOP Credentials:
- Username: aadaileh
- Password: pass

#### ATM Credentials:
- Username: 12345
- Password: 123

### Swagger Documentation
After running the application, swagger can be found under `localhost:8080/swagger-ui.html#/`

### How to?
#### How to run the application locally?
1. Install MySql locally
2. Open application.properties and comment out the cloud configurations (lines: 8-12) then add the 
database credentials and database name to the `LOCAL` section
3. Run `AbcBankingGroupApplication`
4. In the browser call http://localhost:8080/api/main-service/[ANY-RESTFUL-VERB]

#### How to run the associated website (online-banking, ATM)?
There is a complete website designed to talk to the developed service farm. Please refer to the 
section `How to install xamp?` to setup the website. After Xamp is installed unzip the frontend 
folder and call http://localhost/home.php.

### JavaDoc
There is a complete JavaDoc generated for the application. It can be found inside the submitted zip file

### Xamp and Website
After downloading and installing the XAMP application which installs Apache/MySql/PHP locally, copy the 
frontend folder to the Apache's Docroot and start voth Apache and MySql from the consule

### GitHub Address and some commands
The whole application code can be found under https://github.com/aadaileh/abc-banking-group-new

### UnitTest Coverage
The complete statistics of the UnitText coverage can be found in the submitted zip file, under `unite-testing-report`

### Example Insomnia calls
Here are some examples of the Curl calls to communicate with the services:
- `curl --request POST \
   --url http://localhost:8080/api/main-service/login \
   --header 'authorization: Basic YXBpdXNlcjpwYXNz' \
   --header 'content-type: application/json' \
   --cookie JSESSIONID=8E345D562942FBAA611D4E8247CDF84B \
   --data '{  
 	 "username":"aadaileh",
    "password":"pass"
 }'`
 
- `curl --request POST \
    --url http://localhost:8080/api/main-service/login \
    --header 'authorization: Basic YXBpdXNlcjpwYXNz' \
    --header 'content-type: application/json' \
    --cookie JSESSIONID=8E345D562942FBAA611D4E8247CDF84B \
    --data '{  
  	 "username":"aadaileh",
     "password":"pass"
  }'`
  
- `curl --request PUT \
     --url http://localhost:8080/api/main-service/transfer-fund \
     --header 'authorization: Basic YXBpdXNlcjpwYXNz' \
     --header 'content-type: application/json' \
     --cookie JSESSIONID=8E345D562942FBAA611D4E8247CDF84B \
     --data '{  
   	"clientId":1,
     "iban":"UK1234567890tzuj67",
   	"swift": "AHGJDGSDIU",
   	"beneficiaryFullName": "Yazeed Smith",
   	"beneficiaryAddress": "Hohenhaganer Str. 4, 42855 Remscheid Germany",
   	"country": "uk",
   	"city": "LONDON",
   	"bankName": "CBC Bank",
   	"branch": "Kingston",
   	"amount": 1200,
   	"transferPurpose": "Accomodation rent",
   	"transferOn": "ASAP",
   	"notes": "Please transfer it asap"
   }'`