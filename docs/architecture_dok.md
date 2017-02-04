# Architecture Documentation
## The Recruiting Application
### Team
Adrian Gortzak <br/>
AlAlbin Friedner <br/>
Alexander Nikalayeu nikal@kth.se

### Introduction
### Functionality View
### Design View
### Security View
### Data View
### Non-functional View
### Deployment View
### Implementation View
### Problems



### Collection of information that has to be put under right part of the documentation later:

#### Architecture ![overall architecture](https://www.safaribooksonline.com/library/view/software-architecture-patterns/9781491971437/assets/sapr_0402.png)

This application is build using Microservices Architecture Pattern. More specifically API REST-based topography.
There are several reasons for choosing this architecture:

- High cohesion and low coupling - all parts of the system are decoupled in microsystems that can exist on their own
- High scalability and ease of deployment - since all parts of the system are decoupled they can be deployed in any numbers and easily in a container, for example Docker container
- Easy to maintain and continue independent development - decoupled parts are easy to change and as long as same API is used system will not be broken and different teams can independently change implementations of different parts
- Good testability - decoupled parts are simple to test since every test can be targeted for a specific code without any dependencies

#### Microservice nodes:
 - Eurika-server - discovery-service, a way for services to find other services
 - Configuration service - to have configuration in one place for all services
 - Edge service (probably API Gateway, not Micro-proxy) - controller that handles all requests to the service, authentication, circuit breaker, load balancer. Responses with JSON or HTML
 - OAuth 2 Server - authentication server
 - View service - service that delivers HTML pages
 - User service - handles registration and login
 - User DB - database for users
 - JobApplication service - handles registering and viewing job applications
 - JobApplication DB - database for jobapplications
 - Logging service - handles logging all over the system or every service should have it's own???
 - Redis Message Broker Service - message broker for messages between services

#### Tools:
- Spring Framework with Spring Boot, Spring Cloud, Spring Security and more Spring projects - main development framework
- Maven - for dependency managing, test running, building docker images 
- JUnit 4/5 - testing framework
- Mockito - testing framework
- Netflix Eureka - discovery service so that services can find each other
- Netflix Hystrix - implementation circuit breaker design patternr that handles situation when service is unavailable
- Netflix Ribbon - client side load balancer that knows which servers are up and available
- Redis - used as message broker between services to make sure that no messages dissapear due to down server
- Docker - packeting and deployment tool


#### Design Decision
##### Write to and read from services
Writing to services is performed by Redis Message Broker to prevent any loss of POST/PUT requests
Reading performed by using RESTapi.

##### Authentication
Authentication is done by OAuth2 SSO and OAuth2 Server with tokens.
When client authenticates on Edge Service, it gets a token from OAuth2 Server. 
This token then send by client in the header of a request, service that will handle
the request will check the token with OAuth2 Server to check if token is valid.

#### Old SSN to new dateOfBirth
In old system SSN were entered by applicants and in the new system date of birth will be used
instead. New database will be redesigned but old data cannot be lost. Solution is to create a new 
db table where persons id is a foreign key to the person and SSN store in SSN column. 