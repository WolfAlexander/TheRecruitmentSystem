# The Recruitment System
[![Build Status](https://travis-ci.org/WolfAlexander/TheRecruitmentSystem.svg?branch=master)](https://travis-ci.org/WolfAlexander/TheRecruitmentSystem)

Project in IV1201 Architecture and design of global applications

## General
A recruitment system implemented using **Microservices architecture**. This project is part of the course in global system architecture, but unfortunately the course only covers building a monolithic application using Java EE. We decided that we want to do something new for us and more useful in 2017. So, we implemented the project with Microservices architecture using Spring Framework and other tools mentioned below. 

Details:
* **Project task** - docs/project-task.pdf
* **Architectural documentation** - docs/architecture_doc.pdf

## Tools
* **Spring Framework with Spring Boot, Spring Cloud, Spring Security and more Spring projects** - main development framework 
* **Project Lombok** - runtime generator for constructors, getters, setters and toString methods
* **Nimbus Jose JWT** - for Json Web Tokens
* **Minidev Json Smart** - for Json manipulation
* **Maven** - for dependency managing, test running, building docker images 
* **JUnit 4 / JUnit 5** - unit testing framework 
* **Spring Test** - spring integration testing framework 
* **Mockito**  - framework for mock tests in Java 
* **Netflix Eureka**  - discovery service so that services can find each other 
* **Netflix Hystrix** - implementation circuit breaker design pattern that handles situation when service is unavailable 
* **Netflix Ribbon** - client side load balancer that knows which servers are up and available 
* **Redis** - used as message broker between services to make sure that no messages disappear due to down server 
* **Docker** - packaging and deployment tool 
* **AngularJS** - client side model 
* **Bootstrap** - web client layout

## Authors
* **Adrian Gortzak**  - JobApplication Service, packaging, docs, deployment, Gitlab CI, UI, and DB
* **Albin Friedner**   - Registration service, docs and DB
* **Alexander Nikalayeu** - AuthService, ConfigService, DiscoveryService, EdgeService, scripts, UI, security, overall architecture, deployment, packaging, docs, Travis CI
