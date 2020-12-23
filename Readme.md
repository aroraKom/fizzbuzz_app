FizzBuzz Application!
===================

This application is built using Java Spring boot Rest API

How to Run Application
-------------
There are two ways to run this application

> **As docker container:**

> - Create a docker image using docker file by running the following command in cmd in the root folder of the project.
>
		- docker build ./  --tag fizzbuzz:1.0
> - Run as a docker container by using the following command.
> 
		-docker run -p 8089:8080 -d fizzbuzz:1.0
> - check container is up using following command
> 
		-docker ps


> **Run as independent jar**

> - Create jar by typing following command in command prompt in the root folder of the project. 
> 
            - mvn clean package
> - Go to target folder. 
> 
           -cd target
> - Run the jar by typing following command: 
> 
            -java -jar fizzbuzz-0.0.1-SNAPSHOT.jar

How to Test the Application
-------------
Once the jar/container is running, you can use Postman to test the API

>- **Import the following as curl command in postman or run in your cmd prompt**
curl --location --request POST 'http://localhost:8089/fizzbuzz' \
--header 'Content-Type: application/json' \
--data-raw '{
    "start":59,
    "stop":15
}'
Change port if required.
>- **Import postman collection present in the root folder in postman**


API Documentation
-------------

>  **Request URL** : http://localhost:8080/fizzbuzz
>  
>  **Request Method** : POST 
>  
> **Request body** : {
    "start":59,
    "stop":15
} 

> **Request header** 'Content-Type: application/json'