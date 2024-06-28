# Expense
This application has been developed to manage the user expenses during monthly intervals. Users can select their expense category from a list and save them by date. Also, they can set notification alarms to being notified when their total expense in each category exceeds the limit. Notification raising can be activated/deactivated and the limit is reconfigurable. 


## Technologies
- Java 17
- Spring Boot 3.2.7
- H2
- Maven
- Swagger 3


## Deployment
Docker technology has been used to deploy the current application. Please make sure that docker is installed on your machine and docker hub is accessable. Then, run the following commands:

``docker build --tag 'expense' .``

``docker run --detach 'expense'``


##API Documentation
There is a swagger documentation for all the APIs (http://localhost:8080/swagger-ui/index.html)
